package com.bersahaja.todo.repository;

import com.bersahaja.todo.entity.Status;
import com.bersahaja.todo.entity.Todo;
import com.bersahaja.todo.util.DatabaseUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class TodoRepositoryTest {

  private HikariDataSource dataSource = DatabaseUtil.getDataSource();
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
  void testAdd() {
    Todo todo = new Todo();
    todo.setTask("Menyapu lantai");
    todo.setStatus(Status.SELESAI);
    todo.setDateTime(LocalDateTime.now());

    try {
      repository.add(todo);
    }catch (RuntimeException e){
      System.out.println(e.getMessage());
    }


  }
}
