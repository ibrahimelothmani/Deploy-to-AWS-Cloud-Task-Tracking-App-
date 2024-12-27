package com.ibrahim.tasks.mapper;

import com.ibrahim.tasks.dto.TaskListDto;
import com.ibrahim.tasks.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
