package com.example.backend.tasks.task.repositories;

import com.example.backend.tasks.task.entities.Task;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProjectIdAndParentTaskIsNull(Long projectId);

    Optional<Task> findByIdAndParentTaskIsNull(Long taskId);

    void deleteById(@NonNull Long id);

    boolean existsByParentTaskId(Long parentTaskId);

    List<Task> findByParentTaskIsNull();

    List<Task> findBySprintIdAndParentTaskIsNull(Long sprintId);

    List<Task> findByParentTaskId(Long parentTaskId);
}
