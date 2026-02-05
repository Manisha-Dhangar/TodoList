package com.project.service;


import com.project.entity.MyUser;
import com.project.entity.Todo;
import com.project.repository.MyUserRepo;
import com.project.repository.TodoRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TodoService {
    private TodoRepo todoRepo;
    private MyUserRepo myUserRepo;

    public Todo createtodo(Todo todo,String username){
        MyUser getloggedUser =this.myUserRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
        LocalDate ld = LocalDate.now();
        todo.setDate(ld);
        todo.setMyUser(getloggedUser);
       return this.todoRepo.save(todo);
    }
    public List<Todo> getAllTodos(String username){
      MyUser myUser = this.myUserRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username)) ;
      List<Todo> todoList = this.todoRepo.findTodosByMyUser(myUser);
        return todoList;
    }
    public Todo updateTodo(Todo todo, Long id,String username){

        MyUser myUser = this.myUserRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));

        Todo existtodo = this.todoRepo.findTodoByIdAndMyUser(id,myUser).orElseThrow(()->new EntityNotFoundException()) ;
        existtodo.setTitle(todo.getTitle());
        existtodo.setBody(todo.getBody());
        todo.setId(id);
        todo.setMyUser(myUser);
        todo.setDate(LocalDate.now());
        this.todoRepo.save(existtodo);
        return todo;

    }

    public void delete(Long id){
     Todo todo = this.todoRepo.getTodoById(id);
     this.todoRepo.delete(todo);

    }

}
