package com.project.repository;

import com.project.entity.MyUser;
import com.project.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface TodoRepo extends JpaRepository<Todo,Long> {

   Optional<Todo> findTodoByIdAndMyUser(Long id, MyUser myUser);

    List<Todo> findTodosByMyUser(MyUser myUser);

    Todo getTodoById(Long id);

    long countDistinctUserByDate(LocalDate now);

    long countByMyUser(MyUser myUser);

    long countByMyUserAndDate(MyUser myUser, LocalDate now);

 void deleteBymyUser(MyUser myUser);
}
