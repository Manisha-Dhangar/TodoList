package com.project.service;

import com.project.DTOs.MyUserDTO;
import com.project.entity.MyUser;
import com.project.repository.MyUserRepo;
import com.project.repository.TodoRepo;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@Transactional
@AllArgsConstructor
public class MyUserServiceImpl implements MyUserService{
    private final MyUserRepo myUserRepo;
    private final PasswordEncoder passwordEncoder;
    private final MyUserDTO myUserDTO;
    private final TodoRepo todoRepo;
    private final PasswordEncoder passwordEncod;



    public MyUser saveNewUser(MyUser myUser){
    myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
    myUser.setRole(List.of("USER"));
    return this.myUserRepo.save(myUser);

}

    public MyUserDTO getProfile(String username) {
        MyUser myUser = this.myUserRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
        myUserDTO.setId(myUser.getUserId());
        myUserDTO.setUsername(myUser.getUsername());
        long todayTodo =this.todoRepo.countByMyUser(myUser);
        myUserDTO.setTodayTodo(todayTodo);

        myUserDTO.setTodayTodo(this.todoRepo.countByMyUserAndDate(myUser, LocalDate.now()));
        myUserDTO.setActiveStatus(todayTodo >= 1?true:false);
        return myUserDTO;

    }

    public MyUser editPassword(MyUser myUser, @Nonnull UserDetails userDetails) {
        MyUser existUser = this.myUserRepo.findByUsername(userDetails.getUsername()).orElseThrow(()-> new UsernameNotFoundException("username"));
        existUser.setUsername(myUser.getUsername());
        existUser.setPassword(this.passwordEncod.encode(myUser.getPassword()));
        this.myUserRepo.save(existUser);
        return existUser;
    }

    public void deleteUserProfile(Long id,String username) {
       MyUser myUser= this.myUserRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("not found "));
       if(this.myUserRepo.countByRole("ADMIN")<=1){
           throw new UsernameNotFoundException("not found");     }
       this.todoRepo.deleteBymyUser(myUser);
       this.myUserRepo.deleteById(myUser.getUserId());


    }
}
