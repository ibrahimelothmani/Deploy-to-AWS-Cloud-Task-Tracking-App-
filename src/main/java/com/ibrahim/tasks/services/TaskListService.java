package com.ibrahim.tasks.services;

import com.ibrahim.tasks.entities.TaskList;

import java.util.List;

public interface TaskListService {

    List<TaskList> listTaskLists();
    TaskList createTaskList(TaskList taskList);
}
