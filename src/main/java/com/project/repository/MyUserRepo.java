package com.project.repository;

import com.project.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepo extends JpaRepository<MyUser,Long> {
   Optional<MyUser> findByUsername(String username);

    int countByRole(String admin);
}
