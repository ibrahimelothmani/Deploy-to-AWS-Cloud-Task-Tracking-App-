package com.ibrahim.tasks.mapper.impl;

import com.ibrahim.tasks.dto.TaskListDto;
import com.ibrahim.tasks.entities.Task;
import com.ibrahim.tasks.entities.TaskList;
import com.ibrahim.tasks.entities.TaskStatus;
import com.ibrahim.tasks.mapper.TaskListMapper;
import com.ibrahim.tasks.mapper.TaskMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                LocalDateTime.now(), // created
                LocalDateTime.now(), // updated
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList())
                        .orElse(null)
        );

    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                                .map(List::size)
                        .orElse(0),
        calculateProgress(taskList.getTasks()),
        Optional.ofNullable(taskList.getTasks())
                .map(tasks -> tasks.stream()
                        .map(taskMapper::toDto)
                        .toList()
                ).orElse(null)
        );

    }

    private Double calculateProgress(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            return null;
        }
        long completedTasks = tasks.stream()
                .filter(task -> TaskStatus.CLOSED==task.getStatus())
                .count();
        return (double) completedTasks / tasks.size();
    }
}
