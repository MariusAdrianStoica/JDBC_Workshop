package se.lexicon;

import se.lexicon.exception.DBConnectionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App{
public static void main( String[] args ) {

  System.out.println( "JDBC Workshop");
  System.out.println("-------------------------------");

  String query = "SELECT * FROM CITY WHERE ID =?";
  try(
          Connection connection = MySQLConnection.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(query);
          ){

          preparedStatement.setInt(1,1 );
          try(ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){

              System.out.print(resultSet.getInt("ID") + "\t");
              System.out.print(resultSet.getString("Name") + "\t");
              System.out.print(resultSet.getString("CountryCode") + "\t");
              System.out.print(resultSet.getString("District") + "\t");
              System.out.print(resultSet.getInt("Population") + "\n");
              System.out.println("------------------");
            }
          }


  }catch(DBConnectionException | SQLException e){
    System.out.println(e.getMessage());
    e.printStackTrace();

  }





  System.out.println("Code by : Marius Stoica");
  System.out.println("Have a good day!");
    }
}
