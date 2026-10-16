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
import com.example.backend.tasks.task.exceptions.InvalidHierarchyException;
import com.example.backend.tasks.task.exceptions.TaskContainsSubtaskException;
import com.example.backend.tasks.task.exceptions.TaskNotFoundException;
import com.example.backend.tasks.task.repositories.TaskRepository;
import com.example.backend.tasks.task_comment.entities.TaskComment;
import com.example.backend.tasks.task_comment.repositories.TaskCommentRepository;
import com.example.backend.users.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SprintRepository sprintRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;

    public TaskService(
            TaskRepository taskRepository,
            SprintRepository sprintRepository,
            ProjectMemberRepository projectMemberRepository,
            ProjectRepository projectRepository
    ) {
        this.taskRepository = taskRepository;
        this.sprintRepository = sprintRepository;
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
                throw new InvalidHierarchyException(
                        "A subtask cannot have a subtask"
                );
            }

            if (!parentTask.getProject().getId().equals(request.getProjectId())) {
                throw new InvalidHierarchyException("Subtask project must be the same with parent task project");
            }

            if (!parentTask.getSprint().getId().equals(request.getSprintId())) {
                throw new InvalidHierarchyException("Subtask sprint must be the same with parent task sprint");
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

        ProjectMember member = projectMemberRepository.findByUserIdAndProjectId(request.getCreatedBy(), project.getId())
                .orElseThrow(() ->new ProjectMemberNotFoundException(request.getCreatedBy()));

        User createdBy = member.getUser();


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
        return taskRepository.findByParentTaskIsNull()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //GET ALL TASK BY SPRINT
    public List<TaskResponse> getTaskBySprint(Long sprintId){
        return taskRepository.findBySprintIdAndParentTaskIsNull(sprintId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //GET ALL TASK BY PROJECT
    public List<TaskResponse> getTaskByProject(Long projectId){
        return taskRepository.findByProjectIdAndParentTaskIsNull(projectId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //GET TASK BY ID
    public TaskResponse getTaskById(Long taskId){
        return toResponse(taskRepository.findByIdAndParentTaskIsNull(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId)));
    }

    //GET ALL SUBTASK
    public List<TaskResponse> getSubtaskByParentTask(Long taskId){
        return taskRepository.findByParentTaskId(taskId)
                .stream()
                .map(this::toResponse)
                .toList();
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

        if (request.getAssigneeId() == null) {
            task.setAssignee(null);
        } else {
            ProjectMember projectMember = projectMemberRepository
                    .findByUserIdAndProjectId(
                            request.getAssigneeId(),
                            task.getProject().getId()
                    )
                    .orElseThrow(() ->
                            new ProjectMemberNotFoundException(request.getAssigneeId())
                    );

            task.setAssignee(projectMember.getUser());
        }

        Task savedTask = taskRepository.save(task);
        return toResponse(savedTask);
    }

    // DELETE TASK
    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new TaskNotFoundException(taskId);
        }

        if (taskRepository.existsByParentTaskId(taskId)) {
            throw new TaskContainsSubtaskException(taskId);
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
        taskResponse.setSprintId(
                task.getSprint() != null ? task.getSprint().getId() : null
        );
        taskResponse.setCreatedBy(task.getCreatedBy().getId());
        taskResponse.setAssigneeId(
                task.getAssignee() != null ? task.getAssignee().getId() : null
        );
        taskResponse.setParentId(
                task.getParentTask() != null ? task.getParentTask().getId() : null
        );

        return taskResponse;
    }

}
