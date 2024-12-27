package com.ibrahim.tasks.services.Impl;

import com.ibrahim.tasks.entities.TaskList;
import com.ibrahim.tasks.repositories.TaskListRepository;
import com.ibrahim.tasks.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (taskList.getId() != null) {
            throw new IllegalArgumentException("TaskList already exists");
        }
        if (null == taskList.getTitle() && taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title is required");
        }
        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                        null,
                        taskList.getTitle(),
                        taskList.getDescription(),
                now,
                now,
                null
                ));
    }
}
