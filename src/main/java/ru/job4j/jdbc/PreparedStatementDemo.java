package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public void insert(City city) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO cities(name, population) VALUES (?, ?)"
        )) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        City city = new City(1, "Chelyabinsk", 1_500_000);
        City chelyaba = new City(1, "Chelyaba", 1_700_000);
        PreparedStatementDemo preparedStatement = new PreparedStatementDemo();
        //preparedStatement.insert(city);
        System.out.println("complete: " + preparedStatement.update(chelyaba));
    }
}
