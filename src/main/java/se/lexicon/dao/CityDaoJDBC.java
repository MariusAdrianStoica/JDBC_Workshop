package se.lexicon.dao;

import se.lexicon.MySQLConnection;
import se.lexicon.exception.DBConnectionException;
import se.lexicon.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC implements CityDao{

    private List<City> cityStorage;

    private static String query;


    public CityDaoJDBC(){


    }

    @Override
    public  City findByID(int id) {

        System.out.println("Find City By ID: "+id);
        System.out.println("******************************");
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

                        System.out.println("Id\t\t\t:" + resultSet.getInt("ID"));
                        System.out.println("Name \t\t:" + resultSet.getString("Name"));
                        System.out.println("CountryCode :" + resultSet.getString("CountryCode"));
                        System.out.println("District \t:" + resultSet.getString("District"));
                        System.out.println("Population  :" + resultSet.getInt("Population"));
                        System.out.println("-------------------------------");
                    }
                }

            } catch (DBConnectionException | SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();

            }

            return null;

        }
    @Override
    public List<City> findByCode(String code) {
        System.out.println("Find Cities By CountryCode: "+code);
        System.out.println("******************************");

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
        cityStorage.forEach(System.out::println);

        System.out.println("-------------------------------");
        return null;
    }

    @Override
    public List<City> findByName(String name) {
        System.out.println("Find Cities By Name: "+name);
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
        cityStorage.forEach(System.out::println);
        System.out.println("-------------------------------");
        return null;
    }

    @Override
    public List<City> findAll() {
        System.out.println("Find all cities: ");
        System.out.println("******************************");

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
        cityStorage.forEach(System.out::println);
        System.out.println("-------------------------------");

        return null;
    }

    @Override
    public City add(City city) {
        return null;
    }

    @Override
    public City update(City city) {
        return null;
    }

    @Override
    public int delete(City city) {
        return 0;
    }
}
