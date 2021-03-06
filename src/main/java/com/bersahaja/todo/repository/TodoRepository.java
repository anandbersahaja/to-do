package com.bersahaja.todo.repository;

import com.bersahaja.todo.entity.Status;
import com.bersahaja.todo.entity.Todo;

import java.util.List;

public interface TodoRepository {
  List<Todo> getAll();

  Integer add(Todo todo);

  Integer delete(Integer id);

  Integer edit(Integer id, Status status);

  List<Todo> selectByStatus(Status status);

  Boolean selectById(Integer id);

  Todo getById(Integer id);
}
