package com.project;

import com.project.entity.MyUser;
import com.project.repository.MyUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private MyUserRepo myUserRepo;
@Autowired
    public MyUserDetailsService(MyUserRepo myUserRepo) {
        this.myUserRepo = myUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       MyUser myUser= this.myUserRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));

       return User.builder()
               .username(myUser.getUsername())
               .password(myUser.getPassword())
               .roles(myUser.getRole().toArray(new String[0])).build();
    }
}
