package com.ibrahim.tasks.services.Impl;

import com.ibrahim.tasks.entities.Task;
import com.ibrahim.tasks.entities.TaskList;
import com.ibrahim.tasks.entities.TaskPriority;
import com.ibrahim.tasks.entities.TaskStatus;
import com.ibrahim.tasks.repositories.TaskListRepository;
import com.ibrahim.tasks.repositories.TaskRepository;
import com.ibrahim.tasks.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;
    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Transactional
    @Override
    public Task createTask(UUID taskListId, Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("Task already exists");
        }
        if (null == task.getTitle() || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title is required");
        }
        TaskPriority priority = Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);
        TaskStatus status = Optional.ofNullable(task.getStatus())
                .orElse(TaskStatus.OPEN);
        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(()-> new IllegalArgumentException("TaskList does not exist"));
        LocalDateTime now = LocalDateTime.now();
        return taskRepository.save(new Task(
                        null,
                        task.getTitle(),
                        task.getDescription(),
                        task.getDueDate(),
                status,
                priority,
                        now,
                        now,
                taskList
                ));
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(task.getId() == null) {
            throw new IllegalArgumentException("Task must have an ID");
        }
        if (null == task.getTitle() || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title is required");
        }
        Optional<Task> optionalTask = taskRepository.findByTaskListIdAndId(taskListId, taskId);
        if (optionalTask.isEmpty()) {
            throw new IllegalArgumentException("Task does not exist");
        }
        Task existingTask = optionalTask.get();
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setStatus(Optional.ofNullable(task.getStatus())
                .orElse(existingTask.getStatus()));
        existingTask.setPriority(Optional.ofNullable(task.getPriority())
                .orElse(existingTask.getPriority()));
        existingTask.setUpdated(LocalDateTime.now());
        return taskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
    }
}
