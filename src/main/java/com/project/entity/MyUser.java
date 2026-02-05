package com.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class MyUser {
    @Id
     @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    @ElementCollection(fetch=FetchType.EAGER)
    private List<String> role;


}


