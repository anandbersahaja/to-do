package com.bersahaja.todo.service;

import com.bersahaja.todo.entity.Todo;

import java.util.List;

public interface TodoService {

  List<Todo> getAll();

  Integer addTodo(Todo todo);

  Integer deleteTodo(Integer id);

  Integer editStatus(Integer id, String status);
}
