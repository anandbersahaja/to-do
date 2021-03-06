package com.bersahaja.todo.repository;

import com.bersahaja.todo.entity.Status;
import com.bersahaja.todo.entity.Todo;
import com.bersahaja.todo.util.DatabaseUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class TodoRepositoryTest {

  private final HikariDataSource dataSource = DatabaseUtil.getDataSource();
  private TodoRepository repository = new TodoRepositoryImpl(dataSource);

  @Test
  void testGetAll() {
    try {
      List<Todo> todos = repository.getAll();
//      Assertions.assertNotNull(todos);

      todos.forEach(todo -> {
        System.out.println(todo.getId());
        System.out.println(todo.getTask());
        System.out.println(todo.getDateTime());
        System.out.println(todo.getStatus());
      });
    }catch (RuntimeException e){
      System.out.println("Error "+ e.getMessage());
    }
  }

  @Test
  void selectBystatus() {
    try {
      List<Todo> todos = repository.selectByStatus(Status.SUDAH);
//      Assertions.assertNotNull(todos);
//      Assertions.assertFalse(todos.isEmpty());

      todos.forEach(todo -> {
        System.out.println(todo.getId());
        System.out.println(todo.getTask());
        System.out.println(todo.getDateTime());
        System.out.println(todo.getStatus());
      });
    }catch (RuntimeException e){
      System.out.println("Error: "+ e.getMessage());
    }
  }

  @Test
  void testAdd() throws SQLException {
    Todo todo = new Todo();
    todo.setTask("UTS MPSI");
    todo.setStatus(Status.convert("belum"));
    todo.setDateTime(LocalDateTime.now());

    try {
      repository.add(todo);
    }catch (RuntimeException e){
      System.out.println(e.getMessage());
    }
  }

  @Test
  void testDelete() {
    try {
      int delete = repository.delete(2);
      System.out.println(delete);

//      testGetAll();
    }catch (RuntimeException e){
      System.out.println(e.getMessage());
    }
  }

  @Test
  void print() {
    System.out.println(Status.SUDAH.toString());
  }

  @Test
  void getById() {

    try{
      Todo todo = repository.getById(3);

      if(todo.getTask() != null){

        System.out.println(todo.getId());
        System.out.println(todo.getTask());
        System.out.println(todo.getStatus());
      }else{
        System.out.println("Kocong");
      }
    }catch (RuntimeException e){
      System.out.println(e.getMessage());
    }


  }
}
