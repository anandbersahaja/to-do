package com.bersahaja.todo.util;

import java.util.Scanner;

public class Scan {
  public static void main(String[] args) {
    try {
      int a = getInteger("angka");
      System.out.println(a);
    }catch (IllegalArgumentException e){
      System.out.println(e.getMessage());
    }
  }

  public static String getString(String info){
    Scanner input = new Scanner(System.in);
    System.out.print(info +"\t: ");
    return input.nextLine();
  }

  public static Integer getInteger(String info) throws IllegalArgumentException{
      return Integer.valueOf(getString(info));
  }
}
