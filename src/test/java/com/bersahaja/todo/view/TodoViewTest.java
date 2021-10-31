package com.bersahaja.todo.view;

import com.bersahaja.todo.repository.TodoRepository;
import com.bersahaja.todo.repository.TodoRepositoryImpl;
import com.bersahaja.todo.service.TodoService;
import com.bersahaja.todo.service.TodoServiceImpl;
import com.bersahaja.todo.util.DatabaseUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodoViewTest {

  private HikariDataSource dataSource = DatabaseUtil.getDataSource();
  private TodoRepository repository = new TodoRepositoryImpl( dataSource);
  private TodoService service = new TodoServiceImpl(repository);
  private TodoView view = new TodoView(service);

  @Test
  void clearScreen() {
    String os = System.getenv("os");
    System.out.println(os);

    Assertions.assertTrue(os.contains("Window"));

  }
}
