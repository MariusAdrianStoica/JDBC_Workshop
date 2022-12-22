package se.lexicon.dao;

import se.lexicon.MySQLConnection;
import se.lexicon.exception.DBConnectionException;
import se.lexicon.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC implements CityDao{

    private List<City> cityStorage;
    private City foundCity;

    private static String query;


    public CityDaoJDBC(){


    }

    @Override
    public  City findByID(int id) {

        System.out.println("First: Find City By ID: "+id);

        if (id <= 0) {
            throw new IllegalArgumentException("Id must be a positive number!");
        }
            query = "SELECT * FROM CITY WHERE ID = ?";

            try (
                    Connection connection = MySQLConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query)
            ) {

                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {

                        /*
                        System.out.println("Id\t\t\t:" + resultSet.getInt("ID"));
                        System.out.println("Name \t\t:" + resultSet.getString("Name"));
                        System.out.println("CountryCode :" + resultSet.getString("CountryCode"));
                        System.out.println("District \t:" + resultSet.getString("District"));
                        System.out.println("Population  :" + resultSet.getInt("Population"));
                        */
                            foundCity = new City(
                                    resultSet.getInt("ID"),
                                    resultSet.getString("Name"),
                                    resultSet.getString("CountryCode"),
                                    resultSet.getString("District"),
                                    resultSet.getInt("Population"));
                    }
                }

            } catch (DBConnectionException | SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();

            }
        if (foundCity!=null) System.out.println("-------------------------------");
            return foundCity;

        }
    @Override
    public List<City> findByCode(String code) {
        System.out.println("Find Cities By CountryCode: "+code);
        System.out.println("-------------------------------");

        query = "SELECT * FROM CITY WHERE CountryCode = ?";
        cityStorage = new ArrayList<>();

        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            preparedStatement.setString(1, code);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    cityStorage.add(
                            new City(
                                    resultSet.getInt("ID"),
                                    resultSet.getString("Name"),
                                    resultSet.getString("CountryCode"),
                                    resultSet.getString("District"),
                                    resultSet.getInt("Population")
                                ));
                }

            }

        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.println("There are " + cityStorage.size() +" cities in the table, with CountryCode " + code);
        //cityStorage.forEach(System.out::println);

        if (cityStorage.size()!=0) System.out.println("-------------------------------");
        return cityStorage;
    }

    @Override
    public List<City> findByName(String name) {
        System.out.println("Task: Find Cities By Name: "+name);
        System.out.println("******************************");

        query = "SELECT * FROM CITY WHERE Name like ?";
        cityStorage = new ArrayList<>();

        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    cityStorage.add(
                            new City(
                                    resultSet.getInt("ID"),
                                    resultSet.getString("Name"),
                                    resultSet.getString("CountryCode"),
                                    resultSet.getString("District"),
                                    resultSet.getInt("Population")
                            ));
                }

            }

        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.println("There are " + cityStorage.size() +" cities in the table, with Name: " + name);
        //cityStorage.forEach(System.out::println);
        if (cityStorage!=null) System.out.println("-------------------------------");
        return cityStorage;
    }

    @Override
    public List<City> findAll() {
        System.out.println("Find all cities: ");
        System.out.println("-------------------------------");

        query = "SELECT * FROM CITY";
        cityStorage = new ArrayList<>();

        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    cityStorage.add(
                            new City(
                                    resultSet.getInt("ID"),
                                    resultSet.getString("Name"),
                                    resultSet.getString("CountryCode"),
                                    resultSet.getString("District"),
                                    resultSet.getInt("Population")
                            ));
                }

            }

        } catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.println("There are " + cityStorage.size() +" cities in the table:");
        //cityStorage.forEach(System.out::println);
        System.out.println("-------------------------------");

        return cityStorage;
    }

    @Override
    public City add(City city) {
        System.out.println("Add city: "+city.getName() +" to the table");
        System.out.println("-------------------------------");


        query = "INSERT INTO CITY (Name, CountryCode, District, Population) VALUES (?,?,?,?)";

        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected+" city was added to the DB");


            try(
                    ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
                if (resultSet.next()) { // if the row exists
                    System.out.println("New added City ID is:" + resultSet.getInt(1));

                }
            }
        }catch (DBConnectionException|SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public City update(City city) {
        System.out.println("Task: Update city with ID: "+city.getId() +" in the table");
        City cityToUpdateById = findByID(city.getId());

        if (cityToUpdateById!=null){
            query = "UPDATE CITY SET NAME = ?, CountryCode= ?, District = ?, Population =? WHERE ID = ?";

        try (
             Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ) {

            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getId());

            long rowsAffected = preparedStatement.executeLargeUpdate();
            System.out.println(rowsAffected+" city was updated in the DB");
            try(
                    ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
                if (resultSet.next()) { // if the row exists
                    System.out.println("Updated City ID is:" + resultSet.getInt(1));

                }
            }
        }catch (DBConnectionException|SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        }
        return null;
    }

    @Override
    public int delete(City city) {
        System.out.println("Task: Delete city with ID: "+city.getId() +" from the table");

        City cityToDeleteById = findByID(city.getId());

        if (cityToDeleteById!=null){
            query = "DELETE FROM CITY WHERE ID = ?";

            try (
                    Connection connection = MySQLConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query)
            ) {

                preparedStatement.setInt(1, city.getId());

                int rowAffected = preparedStatement.executeUpdate();
                System.out.println(rowAffected+" city was deleted from the DB");

            } catch(DBConnectionException | SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();

            }
            }else System.out.println("City with ID "+city.getId()+" was not found in the DB");
        return 0;
    }
}
