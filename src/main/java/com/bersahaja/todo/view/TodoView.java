package com.bersahaja.todo.view;

import com.bersahaja.todo.entity.Status;
import com.bersahaja.todo.entity.Todo;
import com.bersahaja.todo.repository.TodoRepository;
import com.bersahaja.todo.repository.TodoRepositoryImpl;
import com.bersahaja.todo.service.TodoService;
import com.bersahaja.todo.service.TodoServiceImpl;
import com.bersahaja.todo.util.DatabaseUtil;
import com.bersahaja.todo.util.Scan;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TodoView {
  private final TodoService service;

  public TodoView(TodoService service) {
    this.service = service;
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    HikariDataSource dataSource = DatabaseUtil.getDataSource();
    TodoRepository repository = new TodoRepositoryImpl( dataSource);
    TodoService service = new TodoServiceImpl(repository);
    TodoView view = new TodoView(service);

    // view.show();
    // view.add();
    // view.editStatus();
    // view.showByStatus();
    // view.delete();
    while(true){
      view.menu();
    }

  }

  public void menu() throws IOException, InterruptedException {
    clearScreen();
    System.out.println("MENU");
    System.out.println("1] SHOW TODOLIST");
    System.out.println("2] ADD TODO");
    System.out.println("3] EDIT TODO");
    System.out.println("4] SHOW TODO BY STATUS");
    System.out.println("5] DELETE TASK");
    System.out.println("x] EXIT");
    var menu = Scan.getString("Pilih Menu");
    System.out.println();
    clearScreen();

    if(menu.equalsIgnoreCase("1")){
      show();
    }else if(menu.equalsIgnoreCase("2")){
      add();
    }else if(menu.equalsIgnoreCase("3")){
      editStatus();
    }else if(menu.equalsIgnoreCase("4")){
      showByStatus();
    }else if(menu.equalsIgnoreCase("5")){
      delete();
    }else if(menu.equalsIgnoreCase("x")){
      System.exit(0);
    }else{
      System.out.println("PILIHAN TIDAK DIMENGERTI");
    }

    Scan.getString("\nPress [ENTER]");
    System.out.println();
  }
  public void show(){
    try{
      List<Todo> todos = service.getAll();

      if(todos.isEmpty()){
        System.out.println("TODOLIST KOSONG!");
      }
      else{
        System.out.println("SHOW TODOLIST");
        todos.forEach(todo -> {
          System.out.print(todo.getTask()+" ==> ");
          System.out.println(todo.getStatus().toString());
        });
      }

    }catch (RuntimeException e){
      System.out.println("Error: "+ e.getMessage());
    }

  }

  public void showByStatus(){
    System.out.println("SHOW BY STATUS");
    try {
      Status status = Scan.getStatus("Status");

      List<Todo> todos = service.selectByStatus(status);

      if(todos.isEmpty()){
        System.out.println("TODOLIST KOSONG!");
      }
      else{
        todos.forEach(todo -> {
          System.out.println("SHOW TODOLIST BY STATUS");
          System.out.print(todo.getTask()+" ==> ");
          System.out.println(todo.getStatus().toString());
        });
      }

    }catch (RuntimeException | SQLException e){
      System.out.println("Error: "+ e.getMessage());
    }
  }

  public void add(){
    System.out.println("ADD TODO");
    try{
      String task = Scan.getString("Task");
      var localDateTime = Scan.getDL("Due Days");
      var status = Scan.getStatus("Status");

      Todo todo = new Todo(task, localDateTime, status);

      Integer add = service.addTodo(todo);

      if(add > 0){
        System.out.println("SUCCESS ADD TASK INTO TODOLIST...");
      }
      else{
        System.out.println("FAILED ADD TASK INTO TODOLIST...");
      }

    }catch (RuntimeException | SQLException e){
      System.out.println("Error: "+e.getMessage());
    }
  }

  public void delete(){
    System.out.println("DELETE TODO FROM LIST");
    try{
      Integer id = Scan.getInteger("Id");
      Integer delete = 0;

      // Jika datanya ada
      if(service.selectById(id)){
        Todo todo = service.getById(id);
        System.out.println(todo.getTask() + " ==> "+todo.getStatus());
        // tampilin

        String yakin = Scan.getString("Apakah yakin [y/n]");

        if(yakin.equalsIgnoreCase("y")){
          delete = service.deleteTodo(id);
        }

        if(delete > 0){
          System.out.println("SUCCESS REMOVING...");
        }
        else{
          System.out.println("FAILED REMOVING...");
        }
      }else{
        System.out.println("TASK WITH ID "+id+" NOT FOUND");
      }


    }catch (RuntimeException e){
      System.out.println("Error: "+e.getMessage());
    }

  }

  public void editStatus(){
    System.out.println("EDIT STATUS TASK");
    try{
      Integer id = Scan.getInteger("Id");

      if(service.selectById(id)){
        Todo todo = service.getById(id);
        System.out.println(todo.getTask() + " ==> "+todo.getStatus());

        Status status = Scan.getStatus("Set Status");

        String yakin = Scan.getString("Apakah yakin [y/n]");

        Integer edit = 0;
        if(yakin.equalsIgnoreCase("y")){
          edit = service.editStatus(id, status);
        }
        if(edit > 0){
          System.out.println("SUCCESS EDITING STATUS...");
        }
        else{
          System.out.println("FALED EDITING STATUS...");
        }
      }else{
        System.out.println("TASK WITH ID "+id+" NOT FOUND");
      }

    }catch (RuntimeException | SQLException e){
      System.out.println("Error: "+ e.getMessage());
    }

  }

  public void clearScreen() throws IOException, InterruptedException {
    String os = System.getenv("os");

    if(os.contains("Window")){
      // windows
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }else{
      Runtime.getRuntime().exec("clear");
      System.out.println("\033[H\033[2J");
      System.out.flush();
    }
  }
}
