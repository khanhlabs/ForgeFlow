package com.example.backend.tasks.task.repositories;

import com.example.backend.tasks.task.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
