package com.example.backend.tasks.task_comment.repositories;

import com.example.backend.tasks.task_comment.entities.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskCommentRepository extends JpaRepository<TaskComment, Long> {
    List<TaskComment> findByTaskId(Long taskId);

    List<TaskComment> findByUserId(Long userId);
}
