package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("hibernate.connection.driver_class"));
            connection = DriverManager.getConnection(
                    properties.getProperty("hibernate.connection.url"),
                    properties.getProperty("hibernate.connection.username"),
                    properties.getProperty("hibernate.connection.password")
            );
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        var sql = String.format("CREATE TABLE IF NOT EXISTS %s();", tableName);
        edit(sql);
    }

    public void dropTable(String tableName) {
        var sql = String.format("DROP TABLE %s", tableName);
        edit(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        var sql = String.format("ALTER TABLE %s ADD COLUMN %s %s;",
                tableName, columnName, type);
        edit(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        var sql = String.format("ALTER TABLE %s DROP COLUMN %s", tableName, columnName);
        edit(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        var sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s",
                tableName, columnName, newColumnName);
        edit(sql);
    }

    public static String getTableScheme(Connection connection, String tableName) {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1;", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private void edit(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream(
                "postgres.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable("create_test");
            System.out.println(getTableScheme(tableEditor.connection, "create_test"));
            tableEditor.addColumn("create_test", "id", "serial PRIMARY KEY");
            tableEditor.addColumn("create_test", "name", "varchar(255)");
            tableEditor.addColumn("create_test", "age", "int");
            System.out.println(getTableScheme(tableEditor.connection, "create_test"));
            tableEditor.renameColumn("create_test", "name", "surname");
            System.out.println(getTableScheme(tableEditor.connection, "create_test"));
            tableEditor.dropColumn("create_test", "age");
            System.out.println(getTableScheme(tableEditor.connection, "create_test"));
            tableEditor.dropTable("create_test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
