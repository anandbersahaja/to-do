package com.bersahaja.todo.service;

import com.bersahaja.todo.entity.Status;
import com.bersahaja.todo.entity.Todo;

import java.util.List;

public interface TodoService {

  List<Todo> getAll();

  List<Todo> selectByStatus(Status status);

  Integer addTodo(Todo todo);

  Integer deleteTodo(Integer id);

  Integer editStatus(Integer id, Status status);

  Boolean selectById(Integer id);

  Todo getById(Integer id);

}
