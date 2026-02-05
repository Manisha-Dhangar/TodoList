package com.project.DTOs;

import com.project.entity.MyUser;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class AdminDTO {
    private long totalTodos;
    private long totalUsers;
    private long activeUsers;
    private List<MyUser> users;
}
