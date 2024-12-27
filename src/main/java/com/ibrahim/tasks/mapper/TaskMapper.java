package com.ibrahim.tasks.mapper;

import com.ibrahim.tasks.dto.TaskDto;
import com.ibrahim.tasks.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
