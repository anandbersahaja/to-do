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

import java.sql.SQLException;
import java.util.List;

public class TodoView {
  private final TodoService service;

  public TodoView(TodoService service) {
    this.service = service;
  }

  public static void main(String[] args) {
    HikariDataSource dataSource = DatabaseUtil.getDataSource();
    TodoRepository repository = new TodoRepositoryImpl( dataSource);
    TodoService service = new TodoServiceImpl(repository);
    TodoView view = new TodoView(service);

    view.show();
    view.add();
    view.editStatus();
    view.showByStatus();
    view.delete();

  }

  public void menu(){
    System.out.println("MENU");
    System.out.println("1] SHOW TODOLIST");
    System.out.println("2] ADD TODO");
    System.out.println("3] EDIT TODO");
    System.out.println("4] SHOW TODO BY STATUS");
    System.out.println("5] DELETE TASK");
    System.out.println("x] EXIT");
    var menu = Scan.getString("Pilih Menu");

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
      System.out.println("PILIHAN TIDAK DIMENGERTI\n");
    }


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
      var dueDate = Scan.getDate("Due Date (yyyy-mm-dd)");
      var dueTime = Scan.getTime("Due Time (hh:mm:ss)");
      var status = Scan.getStatus("Status");

      var localDateTime = dueDate.atTime(dueTime);
      Todo todo = new Todo(task, localDateTime, status);

      Integer add = service.addTodo(todo);

      if(add > 0){
        System.out.println("SUCCESS ADD TASK INTO TODOLIST...");
      }
      else{
        System.out.println("FALED ADD TASK INTO TODOLIST...");
      }
    }catch (RuntimeException | SQLException e){
      System.out.println("Error: "+e.getMessage());
    }
  }

  public void delete(){
    try{
      Integer id = Scan.getInteger("Id");
      Integer delete = service.deleteTodo(id);

      if(delete > 0){
        System.out.printf("SUCCESS REMOVING TASK WITH ID %d...\n",id);
      }
      else{
        System.out.printf("FALED REMOVING TASK WITH ID %d...\n",id);
      }
    }catch (RuntimeException e){
      System.out.println("Error: "+e.getMessage());
    }

  }

  public void editStatus(){
    try{
      Integer id = Scan.getInteger("Id");
      Status status = Scan.getStatus("Status");

      Integer edit = service.editStatus(id, status);

      if(edit > 0){
        System.out.println("SUCCESS EDITING STATUS...");
      }
      else{
        System.out.println("FALED EDITING STATUS...");
      }

    }catch (RuntimeException | SQLException e){
      System.out.println("Error: "+ e.getMessage());
    }

  }
}
