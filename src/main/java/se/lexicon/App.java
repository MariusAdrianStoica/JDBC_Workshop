package se.lexicon;

import se.lexicon.dao.CityDaoJDBC;
import se.lexicon.exception.DBConnectionException;
import se.lexicon.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {

    System.out.println("JDBC Workshop");
    System.out.println("*******************************");


    CityDaoJDBC cityDaoJDBC = new CityDaoJDBC();


      try{
          System.out.println(cityDaoJDBC.findByID(4081).toString()+"\n");
      }catch (NullPointerException e){
          System.out.println(e.getMessage());
      };

    cityDaoJDBC.findByCode("xxx").forEach(System.out::println);


    cityDaoJDBC.findByName("Ros%").forEach(System.out::println);


    cityDaoJDBC.findAll().forEach(System.out::println);

    cityDaoJDBC.add(new City("TEST22", "ROM", "testSON22", 22)); // -> 4093

    cityDaoJDBC.update(new City(4088, "ALVESTA", "SWE", "KRONOBERGS LÄN", 10000));

    cityDaoJDBC.delete(new City(4089));


    System.out.println("\n*******************************");
    System.out.println("Code by : Marius Stoica");
    System.out.println("Have a good day!");
  }
}




    /*public static int getInputNumber () {
      Scanner scanner = new Scanner(System.in);
      int number = 0;
      try {
        number = scanner.nextInt();
      } catch (InputMismatchException e) {
        System.out.println("\t\t Enter a valid number between 0-6");
      }

      return number;
    }*/