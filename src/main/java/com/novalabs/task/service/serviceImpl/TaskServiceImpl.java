package com.novalabs.task.service.serviceImpl;

import com.novalabs.task.Exception.CustomParseException;
import com.novalabs.task.Exception.UserNotFoundException;
import com.novalabs.task.model.Tasks;
import com.novalabs.task.model.request.NewTaskRequest;
import com.novalabs.task.repository.TaskRepository;
import com.novalabs.task.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Object createNewTask(NewTaskRequest newTaskRequest) {
        Tasks task = new Tasks();
        return this.saveTask(task,newTaskRequest);

    }

    @Override
    public Object retrieveAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Object updateTask(Long taskId, NewTaskRequest newTaskRequest) {
   if(taskRepository.existsById(taskId)){
       Tasks tasks=taskRepository.findById(taskId).get();
       return this.saveTask(tasks,newTaskRequest);
   }else{
       throw new UserNotFoundException("User not found");
   }
    }

    //helper method for save and update task
    private Tasks saveTask(Tasks tasks,NewTaskRequest newTaskRequest) {
        tasks.setTitle(newTaskRequest.getTitle());
        tasks.setDescription(newTaskRequest.getDescription());

        //handled DateTimeParseException Exception
        try {
            tasks.setDueDate(LocalDate.parse(newTaskRequest.getDueDate()));
        } catch (DateTimeParseException e) {
            throw new CustomParseException("Incorrect Date Format");
        }
        return taskRepository.save(tasks);
    }
}
