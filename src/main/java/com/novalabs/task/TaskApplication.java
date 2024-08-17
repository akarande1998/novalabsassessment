package com.novalabs.task;

import com.novalabs.task.model.User;
import com.novalabs.task.repository.UserRepository;
import com.novalabs.task.service.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);

	}

}
