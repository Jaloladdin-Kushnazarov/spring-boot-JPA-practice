package org.example.jpaspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.example.jpaspringboot.dto.TodoUpdateDto;
import org.example.jpaspringboot.entity.Todo;
import org.example.jpaspringboot.repositry.TodoRepository;
import org.example.jpaspringboot.servise.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoRepository todoRepository;
    private final TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos(){
        List<Todo> todos = todoRepository.findAll();
        return todos;
    }

    @PostMapping
    public Todo saveTodo(@RequestBody Todo todo){
        return todoService.createTodo(todo);
    }

    @PutMapping
    public Todo updateTodo(@RequestBody TodoUpdateDto todoUpdateDto) {
        Todo todo = todoRepository.findById(todoUpdateDto.id())
                .orElseThrow(() -> new IllegalArgumentException("Todo not found with id: " + todoUpdateDto.id()));

        todo.setTitle(todoUpdateDto.title());
        return todoService.updateTodo(todo);
    }

    @DeleteMapping
    public void deletAllTodp(){
        todoRepository.deleteAll();
    }

}
