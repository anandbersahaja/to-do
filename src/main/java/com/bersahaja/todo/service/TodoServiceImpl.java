package com.bersahaja.todo.service;

import com.bersahaja.todo.entity.Status;
import com.bersahaja.todo.entity.Todo;
import com.bersahaja.todo.repository.TodoRepository;

import java.util.List;

public class TodoServiceImpl implements TodoService{
  private final TodoRepository repository;

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
  public List<Todo> selectByStatus(Status status) {
    try {
      return repository.selectByStatus(status);

    }catch (RuntimeException e){
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

      if(repository.selectById(id)){
        return repository.delete(id);
      }

    }catch (RuntimeException e){
      throw new RuntimeException(e.getMessage());
    }
    return 0;
  }

  @Override
  public Integer editStatus(Integer id, Status status) {
    try{
      if(repository.selectById(id)){
        return repository.edit(id,status);
      }

    }catch (RuntimeException e){
      throw new RuntimeException(e.getMessage());
    }
    return 0;
  }
}
