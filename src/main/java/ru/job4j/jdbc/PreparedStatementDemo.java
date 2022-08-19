package ru.job4j.jdbc;

import java.util.List;

import java.sql.*;
import java.util.ArrayList;

public class PreparedStatementDemo {

    private Connection connection;

    public PreparedStatementDemo() throws SQLException, ClassNotFoundException {
        initConnection();
    }

    public void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";
        connection = DriverManager.getConnection(url, login, password);
    }

    public City insert(City city) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO cities(name, population) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE cities SET name = ?, population = ? WHERE id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM cities WHERE id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM cities")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /*
        City city = new City(1, "Budapest", 2_300_000);

         */
        PreparedStatementDemo preparedStatement = new PreparedStatementDemo();
        /*
        preparedStatement.insert(city);
        System.out.println(city);

         */
        preparedStatement.findAll().forEach(System.out::println);
    }
}
