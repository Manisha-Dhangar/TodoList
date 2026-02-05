package com.project.DTOs;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MyUserDTO {
    private long id;
    private String username;
    private long totalTodo;
    private long todayTodo;
    private boolean activeStatus;
}
