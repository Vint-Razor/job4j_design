package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        String hc = "hibernate.connection.";
        try {
            Class.forName(properties.getProperty(hc + "driver_class"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = properties.getProperty(hc + "url");
        String login = properties.getProperty(hc + "username");
        String password = properties.getProperty(hc + "password");
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {

    }

    public void dropTable(String tableName) {

    }

    public void addColumn(String tableName, String columnName, String type) {

    }

    public void dropColumn(String tableName, String columnName) {

    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {

    }

    public static String getTableScheme(Connection connection, String tableName) throws SQLException {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i)
                ));
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("postgres.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(properties.getProperty("hibernate.connection.url"));
        TableEditor tableEditor = new TableEditor(properties);
    }
}
