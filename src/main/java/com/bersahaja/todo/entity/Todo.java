package com.bersahaja.todo.entity;

import java.time.LocalDateTime;

public class Todo {
  private Integer id;
  private String task;
  private LocalDateTime dateTime;
  private Status status;

  public Todo(Integer id, String task) {
    this.id = id;
    this.task = task;
  }

  public Todo(Integer id, String task, LocalDateTime dateTime) {
    this.id = id;
    this.task = task;
    this.dateTime = dateTime;
  }

  public Todo(Integer id, String task, Status status) {
    this.id = id;
    this.task = task;
    this.status = status;
  }

  public Todo(Integer id, String task, LocalDateTime dateTime, Status status) {
    this.id = id;
    this.task = task;
    this.dateTime = dateTime;
    this.status = status;
  }

  public Todo() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
