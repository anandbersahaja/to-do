package com.bersahaja.todo.repository;

import com.bersahaja.todo.entity.Status;
import com.bersahaja.todo.entity.Todo;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoRepositoryImpl implements TodoRepository{
  private final HikariDataSource dataSource;

  public TodoRepositoryImpl(HikariDataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public List<Todo> getAll() {
    String sql = "SELECT * FROM todolist";
    try(Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement()){
      try (ResultSet resultSet = statement.executeQuery(sql)) {
        List<Todo> todos = new ArrayList<>();

        while(resultSet.next()){
          todos.add(new Todo(
                  resultSet.getInt("id"),
                  resultSet.getString("task"),
                  resultSet.getDate("deadline").toLocalDate()
                          .atTime(resultSet.getTime("deadline")
                                  .toLocalTime()),
                  Status.convert(resultSet.getString("status"))
          ));
        }
        return todos;
      }
    }catch (SQLException e){
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public Integer add(Todo todo) {
    String sql = "INSERT INTO todolist (task, deadline, status) values (?, ?, ?)";
    try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){

      statement.setString(1,todo.getTask());
      statement.setString(2, todo.getDateTime().toString());
      statement.setString(3, todo.getStatus().toString());

      return statement.executeUpdate();

    }catch (SQLException e){
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public Integer delete(Integer id) {
    String sql = "DELETE FROM todolist WHERE id = ?";
    try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

      statement.setInt(1, id);

      return statement.executeUpdate();

    }catch (SQLException e){
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public Integer edit(Integer id, Status status) {
    String sql = "UPDATE todolist SET status = ? WHERE id = ?";
    try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

      statement.setString(1, status.toString());
      statement.setInt(2, id);

      return statement.executeUpdate();

    }catch (SQLException e){
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public List<Todo> selectByStatus(Status status) {
    String sql = "SELECT * FROM todolist WHERE status = ?";
    try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql))
    {
      statement.setString(1, status.toString());

      try (ResultSet resultSet = statement.executeQuery()) {
        List<Todo> todos = new ArrayList<>();

        while(resultSet.next()){
          todos.add(new Todo(
                  resultSet.getInt("id"),
                  resultSet.getString("task"),
                  resultSet.getDate("deadline").toLocalDate()
                          .atTime(resultSet.getTime("deadline")
                                  .toLocalTime()),
                  Status.convert(resultSet.getString("status"))
          ));
        }
        return todos;
      }
    }catch (SQLException e){
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public Boolean selectById(Integer id) {
    String sql = "SELECT * FROM todolist WHERE id = ?";
    try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)
    ){
      statement.setInt(1,id);

      try(ResultSet resultSet =  statement.executeQuery()){
        return resultSet.next();
      }

    }catch (SQLException e){
      throw new RuntimeException(e.getMessage());
    }

  }

  @Override
  public Todo getById(Integer id) {
    String sql = "SELECT * FROM todolist WHERE id = ?";
    try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)
    ){
      statement.setInt(1,id);

      try(ResultSet resultSet =  statement.executeQuery()){
//        Todo todo = new Todo();
//
//        if(resultSet.next()){
//          todo.setId(resultSet.getInt("id"));
//          todo.setTask(resultSet.getString("task"));
//          todo.setDateTime(resultSet.getDate("deadline").toLocalDate()
//                  .atTime(resultSet.getTime("deadline")
//                          .toLocalTime()));
//          todo.setStatus(Status.convert(resultSet.getString("status")));
//        }
//        return todo;

        if(resultSet.next()){
          return new Todo(
                  resultSet.getInt("id"),
                  resultSet.getString("task"),
                  resultSet.getDate("deadline").toLocalDate()
                          .atTime(resultSet.getTime("deadline").toLocalTime()),
                  Status.convert(resultSet.getString("status")));
        }

        return new Todo();
      }
    }catch (SQLException e){
      throw new RuntimeException(e.getMessage());
    }
  }
}
