package com.bersahaja.todo.repository;

import com.bersahaja.todo.entity.Status;
import com.bersahaja.todo.entity.Todo;
import com.bersahaja.todo.util.DatabaseUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
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
  void testEdit() {
    try{
      Integer sudah = repository.edit(3, "SUDAH");
      System.out.println(sudah);
    }catch (RuntimeException e){
      System.out.println(e.getMessage());
    }
  }
}
