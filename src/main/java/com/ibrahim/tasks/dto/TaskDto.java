package com.ibrahim.tasks.dto;

import com.ibrahim.tasks.entities.TaskPriority;
import com.ibrahim.tasks.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskStatus status,
        TaskPriority priority
) {

}
