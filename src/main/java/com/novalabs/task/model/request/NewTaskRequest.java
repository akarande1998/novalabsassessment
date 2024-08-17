package com.novalabs.task.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewTaskRequest {
    private String title;
    private String description;
    private String dueDate;
}
