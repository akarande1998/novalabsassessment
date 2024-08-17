package com.novalabs.task.controller;

import com.novalabs.task.model.request.NewTaskRequest;
import com.novalabs.task.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TaskController {

    @Autowired
    private ITaskService iTaskService;

    //create new task
    @PostMapping("/createTasks")
    public ResponseEntity<?> createNewTask(@RequestBody NewTaskRequest newTaskRequest){
        return new ResponseEntity<>(iTaskService.createNewTask(newTaskRequest), HttpStatus.CREATED);
    }

    //retrieve all task
    @GetMapping("/tasks")
    public ResponseEntity<?> retrieveAllTasks(){
        return new ResponseEntity<>(iTaskService.retrieveAllTasks(), HttpStatus.OK);
    }

    //update an existing task
    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable(name = "taskId") Long taskId,@RequestBody NewTaskRequest newTaskRequest){
        return new ResponseEntity<>(iTaskService.updateTask(taskId,newTaskRequest), HttpStatus.OK);
    }
}
