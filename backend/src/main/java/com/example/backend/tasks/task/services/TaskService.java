package com.example.backend.tasks.task.services;

import com.example.backend.projects.project.entities.Project;
import com.example.backend.projects.project.exceptions.ProjectNotFoundException;
import com.example.backend.projects.project.repositories.ProjectRepository;
import com.example.backend.projects.project_members.entities.ProjectMember;
import com.example.backend.projects.project_members.exceptions.ProjectMemberNotFoundException;
import com.example.backend.projects.project_members.repositories.ProjectMemberRepository;
import com.example.backend.sprints.entities.Sprint;
import com.example.backend.sprints.exceptions.SprintNotFoundException;
import com.example.backend.sprints.exceptions.SprintProjectMismatchException;
import com.example.backend.sprints.repositories.SprintRepository;
import com.example.backend.tasks.task.dto.CreateTaskRequest;
import com.example.backend.tasks.task.dto.TaskResponse;
import com.example.backend.tasks.task.dto.UpdateTaskRequest;
import com.example.backend.tasks.task.entities.Task;
import com.example.backend.tasks.task.exceptions.InvalidTaskHierarchyException;
import com.example.backend.tasks.task.exceptions.TaskContainsSubtaskException;
import com.example.backend.tasks.task.exceptions.TaskNotFoundException;
import com.example.backend.tasks.task.exceptions.TaskSprintMismatchException;
import com.example.backend.tasks.task.repositories.TaskRepository;
import com.example.backend.users.entities.User;
import com.example.backend.users.exceptions.UserNotFoundException;
import com.example.backend.users.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SprintRepository sprintRepository;
    private final UserRepository userRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;

    public TaskService(
            TaskRepository taskRepository,
            SprintRepository sprintRepository,
            UserRepository userRepository,
            ProjectMemberRepository projectMemberRepository,
            ProjectRepository projectRepository
    ) {
        this.taskRepository = taskRepository;
        this.sprintRepository = sprintRepository;
        this.userRepository = userRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.projectRepository = projectRepository;
    }

    //CREATE TASK
    public TaskResponse createTask(CreateTaskRequest request){

        Task parentTask = null;
        User assignee = null;
        Sprint sprint;
        Project project;

        if (request.getParentId() != null){
            parentTask = taskRepository.findById(request.getParentId())
                    .orElseThrow(() -> new TaskNotFoundException(request.getParentId()));

            if (parentTask.getParentTask() != null) {
                throw new InvalidTaskHierarchyException(
                        "A subtask cannot have a subtask"
                );
            }

            sprint = parentTask.getSprint();
            project = parentTask.getProject();

        }else {
            if (request.getSprintId() != null) {
                sprint = sprintRepository.findById(request.getSprintId())
                        .orElseThrow(() ->
                                new SprintNotFoundException(request.getSprintId()));
            } else {
                sprint = null;
            }

            project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new ProjectNotFoundException(request.getProjectId()));

            if (sprint != null
                    && !sprint.getProject().getId().equals(project.getId())) {
                throw new SprintProjectMismatchException(
                        sprint.getId(), project.getId()
                );
            }
        }

        if (request.getAssigneeId() != null){

          ProjectMember projectMember = projectMemberRepository
                    .findByUserIdAndProjectId(
                            request.getAssigneeId(),
                            project.getId()
                    )
                    .orElseThrow(() ->
                            new ProjectMemberNotFoundException(
                                    request.getAssigneeId()
                            ));

            assignee = projectMember.getUser();
        }

        User createdBy = userRepository.findById(request.getCreatedBy())
                .orElseThrow(() -> new UserNotFoundException(request.getCreatedBy()));


        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setType(request.getType());
        task.setSprint(sprint);
        task.setProject(project);
        task.setCreatedBy(createdBy);
        task.setAssignee(assignee);
        task.setParentTask(parentTask);

        taskRepository.save(task);

        return toResponse(task);
    }

    //GET ALL TASK
    public List<TaskResponse> getAllTasks(){
        return taskRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //GET ALL TASK BY SPRINT
    public List<TaskResponse> getTaskBySprint(Long sprintId){
        return taskRepository.findBySprintId(sprintId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //GET ALL TASK BY PROJECT
    public List<TaskResponse> getTaskByProjectId(Long projectId){
        return taskRepository.findByProjectId(projectId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //GET TASK BY ID
    public TaskResponse getTaskById(Long taskId){
        return toResponse(taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId)));
    }

    //UPDATE TASK
    public TaskResponse updateTask(
            UpdateTaskRequest request,
            Long taskId
    ){
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setType(request.getType());
        task.setSprint(task.getSprint());

        if (request.getAssigneeId() != null){
            User assignee = userRepository.findById(request.getAssigneeId())
                    .orElseThrow(() -> new UserNotFoundException(request.getAssigneeId()));

            task.setAssignee(assignee);
        }

        taskRepository.save(task);
        return toResponse(task);
    }

    //DELETE TASK
    public void deleteTask(Long taskId){
        if (taskRepository.findByParentTaskId(taskId) != null){
            throw new TaskContainsSubtaskException(taskId);
        }

        if (!taskRepository.existsById(taskId)){
            throw new TaskNotFoundException(taskId);
        }

        taskRepository.deleteById(taskId);
    }

    //TO RESPONSE
    public TaskResponse toResponse(Task task) {
        TaskResponse taskResponse = new TaskResponse();

        taskResponse.setId(task.getId());
        taskResponse.setTitle(task.getTitle());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setStatus(task.getStatus());
        taskResponse.setPriority(task.getPriority());
        taskResponse.setType(task.getType());
        taskResponse.setProjectId(task.getProject().getId());
        taskResponse.setSprintId(task.getSprint().getId());
        taskResponse.setCreatedBy(task.getCreatedBy().getId());
        taskResponse.setAssigneeId(task.getAssignee().getId());
        taskResponse.setParentId(task.getId());
        return taskResponse;
    }

}
