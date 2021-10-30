package com.bersahaja.todo.service;

import com.bersahaja.todo.entity.Status;
import com.bersahaja.todo.entity.Todo;
import com.bersahaja.todo.repository.TodoRepository;
import com.bersahaja.todo.util.Scan;

import java.sql.SQLException;
import java.util.List;

public class TodoServiceImpl implements TodoService{
  private TodoRepository repository;

  public TodoServiceImpl(TodoRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Todo> getAll() {

    try {
      return repository.getAll();

    }catch (RuntimeException e){
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public List<Todo> selectByStatus() {
    try {
      Status status = Scan.getStatus("Status");
      return repository.selectByStatus(status);

    }catch (RuntimeException | SQLException e){
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public Integer addTodo(Todo todo) {

    try{
//      String task = Scan.getString("Task");
//      var dueDate = Scan.getDate("Due Date (yyyy-mm-dd)");
//      var dueTime = Scan.getTime("Due Time (hh:mm:ss)");
//      var status = Scan.getStatus("Status");
//
//      var localDateTime = dueDate.atTime(dueTime);
//      Todo todo = new Todo(task, localDateTime, status);

      return repository.add(todo);
    }catch (RuntimeException e){
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public Integer deleteTodo(Integer id) {
    try{
      return repository.delete(id);

    }catch (RuntimeException e){
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public Integer editStatus(Integer id, String status) {
    try{

      return repository.edit(id,status);

    }catch (RuntimeException e){
      throw new RuntimeException(e.getMessage());
    }
  }
}
