package com.novalabs.task.service;

import com.novalabs.task.model.User;
import com.novalabs.task.model.request.NewUserRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {
    UserDetails loadUserByUsername(String email);

    User findByEmail(String username);

    Object createNewUser(NewUserRequest newUserRequest);
}
