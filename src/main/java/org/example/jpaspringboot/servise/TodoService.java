package org.example.jpaspringboot.servise;

import org.example.jpaspringboot.SessionUser;
import org.example.jpaspringboot.entity.Todo;
import org.example.jpaspringboot.repositry.TodoRepository;
import org.springframework.stereotype.Service;
@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final SessionUser sessionUser;

    public TodoService(TodoRepository todoRepository, SessionUser sessionUser) {
        this.todoRepository = todoRepository;
        this.sessionUser = sessionUser;
    }

    public Todo createTodo(Todo todo) {
        todo.setCreatedBy(sessionUser.getId());
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo) {
        todo.setUpdatedBy(sessionUser.getId());
        return todoRepository.save(todo);
    }
}