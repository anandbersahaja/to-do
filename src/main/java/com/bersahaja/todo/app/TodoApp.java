package com.bersahaja.todo.app;

import com.bersahaja.todo.repository.TodoRepository;
import com.bersahaja.todo.repository.TodoRepositoryImpl;
import com.bersahaja.todo.service.TodoService;
import com.bersahaja.todo.service.TodoServiceImpl;
import com.bersahaja.todo.util.DatabaseUtil;
import com.bersahaja.todo.view.TodoView;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;

public class TodoApp {
  public static void main(String[] args) throws IOException, InterruptedException {
    // mengambil datasource dari class DatabaseUtil
    HikariDataSource dataSource = DatabaseUtil.getDataSource();

    TodoRepository repository = new TodoRepositoryImpl( dataSource);
    TodoService service = new TodoServiceImpl(repository);
    TodoView view = new TodoView(service);

    while(true){
      view.menu();
    }
  }
}
