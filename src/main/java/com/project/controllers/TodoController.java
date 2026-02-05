package com.project.controllers;


import com.project.entity.MyUser;
import com.project.entity.Todo;
import com.project.service.TodoService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @PostMapping
    @PreAuthorize("hasRole(\"USER\")")
    public ResponseEntity<?> saveNewTodo(@RequestBody Todo todo , @AuthenticationPrincipal UserDetails userDetails){
        Todo newtodo= this.todoService.createtodo(todo,userDetails.getUsername());
    return ResponseEntity.ok(newtodo);
    }

    @GetMapping
    public ResponseEntity<?> getAllTodo(@AuthenticationPrincipal UserDetails userDetails){
        String username=userDetails.getUsername();
        List<Todo> list=this.todoService.getAllTodos(username);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatetodo(@PathVariable("id") Long id, @RequestBody Todo todo,  @AuthenticationPrincipal UserDetails userDetails){

        Todo todo2 = this.todoService.updateTodo(todo,id,userDetails.getUsername());

       return  ResponseEntity.ok(todo2);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletetodo(@PathVariable("id") Long id){
       this.todoService.delete(id);
       return ResponseEntity.ok("deleted");
    }




}
