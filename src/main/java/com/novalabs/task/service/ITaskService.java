package com.novalabs.task.service;

import com.novalabs.task.model.request.NewTaskRequest;

import java.nio.file.attribute.UserPrincipalNotFoundException;

public interface ITaskService {
    Object createNewTask(NewTaskRequest newTaskRequest);

    Object retrieveAllTasks();

    Object updateTask(Long taskId, NewTaskRequest newTaskRequest);
}
