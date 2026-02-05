package com.project.service;


import com.project.DTOs.AdminDTO;
import com.project.entity.MyUser;
import com.project.repository.MyUserRepo;
import com.project.repository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminService {
    private AdminDTO adminDTO;
    private TodoRepo todoRepo;
    private MyUserRepo myUserRepo;
    @Autowired
    public AdminService(AdminDTO adminDTO, MyUserRepo myUserRepo, TodoRepo todoRepo) {
        super();
        this.adminDTO = adminDTO;
        this.myUserRepo = myUserRepo;
        this.todoRepo = todoRepo;
    }
    public AdminDTO getData(){
        adminDTO.setUsers(this.myUserRepo.findAll());
        adminDTO.setTotalTodos(this.todoRepo.count());
        adminDTO.setTotalUsers(this.myUserRepo.count());
        adminDTO.setActiveUsers(this.todoRepo.countDistinctUserByDate(LocalDate.now()));
        return adminDTO;
    }
    public MyUser makeAdmin(Long id){
        MyUser myUser = this.myUserRepo.findById(id).orElseThrow(()->new UsernameNotFoundException("myuser not found"));
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        roles.add("ADMIN");
        myUser.setRole(roles);
        return myUser;
    }

    public MyUser removeAdmin(Long id) {
        MyUser existAdmin = this.myUserRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("user is not found"));

        if(this.myUserRepo.countByRole("ADMIN")<=1){
           throw new UsernameNotFoundException("not found ");
        }
        List<String> role = new ArrayList<>();
        role.add("USER");
        existAdmin.setRole(role);
        this.myUserRepo.save(existAdmin);
        return existAdmin;
    }


    }

