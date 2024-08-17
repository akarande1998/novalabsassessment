package com.novalabs.task.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserRequest {
    private String email;
    private String password;

}
