package com.project.controllers;

import com.project.DTOs.MyUserDTO;
import com.project.entity.MyUser;
import com.project.service.MyUserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class MyUserDetail {
    private MyUserServiceImpl myUserServiceImpl;

    @GetMapping
    @PreAuthorize("hasAnyRole(\"ADMIN\",\"USER\")")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal UserDetails userDetails){
        MyUserDTO myUserDTO= this.myUserServiceImpl.getProfile(userDetails.getUsername());
        return ResponseEntity.ok(myUserDTO);

    }
    @PutMapping("edit-profile")
    @PreAuthorize("hasRole(\"USER\")")   //these works without this line
    public ResponseEntity<MyUser> editPassword(@RequestBody MyUser myUser, @AuthenticationPrincipal UserDetails userDetails){
        MyUser editedUser= this.myUserServiceImpl.editPassword(myUser,userDetails);
        return ResponseEntity.ok(editedUser);
    }
    @DeleteMapping("/delete-user/{id}")
    @PreAuthorize("hasAnyRole(\"ADMIN\",\"USER\")")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id,@AuthenticationPrincipal UserDetails userDetails){

        this.myUserServiceImpl.deleteUserProfile(id,userDetails.getUsername());
        return ResponseEntity.ok("deleted");

    }




}
