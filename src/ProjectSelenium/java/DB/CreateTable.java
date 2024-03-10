package DB;

import java.sql.*;

public class CreateTable {

    //Creating a main table
    public static void createMainTable(String name) {
        String tableName = name.split(" ")[0];
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement("SHOW TABLES LIKE '" + tableName + "'");
             ResultSet resultSet = checkStatement.executeQuery()) {

            if (!resultSet.next()) {
                try (PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(50)," +
                        "link VARCHAR(200)," +
                        "py INT UNIQUE," +
                        "CONSTRAINT chk_py CHECK (py BETWEEN 10 AND 100)" +
                        ")")) {
                    statement.execute();
                    System.out.println("create table");
                } catch (SQLException e) {
                    System.out.println("error " + e.getMessage());
                }
            } else {
                System.out.println("The table exists");
            }
        } catch (SQLException e) {
            System.out.println("error " + e.getMessage());
        }
    }



    // Creating table two
    public static void createSubTable(String subTableName) {
        String tableName = subTableName.split(" ")[0];
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement("SHOW TABLES LIKE '" + tableName + "'");
             ResultSet resultSet = checkStatement.executeQuery()) {

            if (!resultSet.next()) {
                try (PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "sub_name VARCHAR(50)," +
                        "sub_link VARCHAR(200)," +
                        "py VARCHAR(50)" +
                        ")")) {
                    statement.execute();
                    System.out.println("create table");
                } catch (SQLException e) {
                    System.out.println("error " + e.getMessage());
                }
            } else {
                System.out.println("The table exists");
            }
        } catch (SQLException e) {
            System.out.println("error " + e.getMessage());
        }
    }


    // Creating table elements
    public static void createElementTable(String subTableName) {
        String tableName = subTableName.split(" ")[0];
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "Ele_name VARCHAR(50)," +
                "Ele_link VARCHAR(200)," +
                "py VARCHAR(50)," +
                "price VARCHAR(200)" +
                ")";

        try (Connection connection = DBConnect.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
            System.out.println("The table has been created or already exists");
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
    }
}