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
    System.out.println("-------------------------------");

        CityDaoJDBC cityDaoJDBC = new CityDaoJDBC();

        //cityDaoJDBC.findByID(34);
        //cityDaoJDBC.findByCode("ROM");
        //cityDaoJDBC.findByName("Ro%");
        //cityDaoJDBC.findAll();
        cityDaoJDBC.add(new City("Växjö", "SWE", "Kronobergs län", 72200));








        //System.out.println("-------------------------------");
        System.out.println("\nCode by : Marius Stoica");
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