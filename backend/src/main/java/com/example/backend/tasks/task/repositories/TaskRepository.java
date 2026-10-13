package com.example.backend.tasks.task.repositories;

import com.example.backend.tasks.task.entities.Task;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findBySprintId(Long sprintId);

    List<Task> findByProjectId(Long projectId);

    void deleteById(@NonNull Long id);

    boolean existsByParentTaskId(Long parentTaskId);
}
