package com.project.controllers;

import com.project.DTOs.AdminDTO;
import com.project.entity.MyUser;
import com.project.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    @PreAuthorize("hasRole(\"ADMIN\")")
    public ResponseEntity<AdminDTO> getDATA(){
        AdminDTO adminDTO =this.adminService.getData();
        return ResponseEntity.ok(adminDTO);
    }

    @GetMapping("/make-admin/{id}")
    @PreAuthorize("hasRole(\"ADMIN\")")
    public  ResponseEntity<?> makeadmin(@PathVariable("id")  Long id){
        MyUser  myUser = this.adminService.makeAdmin(id);
        return ResponseEntity.ok(myUser);
    }

    @DeleteMapping("/remove-admin/{id}")
    @PreAuthorize("hasRole(\"ADMIN\")")
    public  ResponseEntity<?> removeadmin(@PathVariable("id") Long id){
       MyUser myUser= this.adminService.removeAdmin(id);
        return ResponseEntity.ok("myUser");
    }




}
