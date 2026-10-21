package com.example.backend.users.services;

import com.example.backend.organizations.organization_member.repositories.OrganizationMemberRepository;
import com.example.backend.projects.project_members.repositories.ProjectMemberRepository;
import com.example.backend.tasks.task.repositories.TaskRepository;
import com.example.backend.users.dto.CreateUserRequest;
import com.example.backend.users.dto.UpdateUserRequest;
import com.example.backend.users.dto.UserResponse;
import com.example.backend.users.entities.User;
import com.example.backend.users.exceptions.EmailAlreadyExistException;
import com.example.backend.users.exceptions.CanNotDeleteUserException;
import com.example.backend.users.exceptions.UserNotFoundWithEmail;
import com.example.backend.users.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TaskRepository taskRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final OrganizationMemberRepository organizationMemberRepository;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TaskRepository taskRepository,
            ProjectMemberRepository projectMemberRepository,
            OrganizationMemberRepository organizationMemberRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.taskRepository = taskRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.organizationMemberRepository = organizationMemberRepository;
    }

    //CREATE USER
    public UserResponse createUser(CreateUserRequest request) {
        String hashedPassword = passwordEncoder
                .encode(request.getPassword());

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistException("Email already exists");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(hashedPassword);

        userRepository.save(user);

        return toResponse(user);
    }

    //GET ALL USER
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //GET USER BY EMAIL
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundWithEmail(email));

        return toResponse(user);
    }

    //UPDATE USER
    public UserResponse updateUser(
            String email,
            UpdateUserRequest request
    ) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundWithEmail(email));

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistException(request.getEmail());
        }

        String hashedPassword = passwordEncoder
                .encode(request.getPassword());

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(hashedPassword);

        userRepository.save(user);

        return toResponse(user);
    }

    //DELETE USER
    public void deleteUser(Long userId){
        if (taskRepository.existsByAssigneeId(userId)){
            throw new CanNotDeleteUserException("This user still assigned to task");
        }

        if (projectMemberRepository.existsByUserId(userId)){
            throw new CanNotDeleteUserException("This user still a project member");
        }

        if (organizationMemberRepository.existsByUserId(userId)){
            throw new CanNotDeleteUserException("This user still a organization member");
        }

        userRepository.deleteById(userId);
    }

    //TO RESPONSE
    public UserResponse toResponse(User user) {
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setFullName(user.getFullName());
        userResponse.setEmail(user.getEmail());

        return userResponse;
    }

}
