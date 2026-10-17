package com.example.backend.tasks.task_label.repositories;

import com.example.backend.tasks.task_label.dto.TaskLabelResponse;
import com.example.backend.tasks.task_label.entities.TaskLabel;
import com.example.backend.tasks.task_label.entities.TaskLabelId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskLabelRepository extends JpaRepository<TaskLabel, TaskLabelId> {

    boolean existsByLabelId(Long labelId);

    List<TaskLabel> findByTaskId(Long taskId);
}
