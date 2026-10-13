package com.example.backend.tasks.task_comment.repositories;

import com.example.backend.tasks.task_comment.entities.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCommentRepository extends JpaRepository<TaskComment, Long> {
}
