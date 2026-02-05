package com.project.controllers;


import com.project.entity.MyUser;
import com.project.service.MyUserService;
import com.project.service.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/public")
public class Controllers {
    private final MyUserService myUserService;

    @Autowired
    public Controllers(MyUserServiceImpl myUserService) {
        this.myUserService = myUserService;
    }



    @PostMapping                                    //register
    public ResponseEntity<?> save(@RequestBody MyUser myUser1) {
        MyUser m= this.myUserService.saveNewUser(myUser1);
       return ResponseEntity.ok(m);
    }
}
