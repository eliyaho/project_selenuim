package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertData {

    // Entering data into a main table
    public static void insertDataIntoMainTable(String nameTable, String name, String link) {
        String tableName = nameTable;

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement("SELECT COUNT(*) FROM " + tableName + " WHERE name = ? AND link = ?");
             PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO " + tableName + " (name, link, py) VALUES (?, ?, DEFAULT)")) {

            checkStatement.setString(1, name);
            checkStatement.setString(2, link);
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count == 0) {
                insertStatement.setString(1, name);
                insertStatement.setString(2, link);
                insertStatement.executeUpdate();

                System.out.println("Entered successfully");
            } else {
                System.out.println("Exists");
            }
        } catch (SQLException e) {
            System.out.println("error " + e.getMessage());
        }
    }

    // Adding data to table two
    public static void insertDataIntoSubTable(String subTableName, String subName, String subLink, String py) {
        String tableName = subTableName;

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement("SELECT COUNT(*) FROM " + tableName + " WHERE sub_name = ? AND sub_link = ?");
             PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO " + tableName + " (sub_name, sub_link, py) VALUES (?, ?, ?)")) {

            checkStatement.setString(1, subName);
            checkStatement.setString(2, subLink);
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count == 0) {
                insertStatement.setString(1, subName);
                insertStatement.setString(2, subLink);
                insertStatement.setString(3, py);
                insertStatement.executeUpdate();

                System.out.println("Added successfully");
            } else {
                System.out.println("The table exists");
            }
        } catch (SQLException e) {
            System.out.println("error " + e.getMessage());
        }
    }

    //Adding data to table elements
    public static void insertDataIntoElementTable(String subTableName, String Ele_name, String Ele_link, String py, String price) {
        String tableName = subTableName;
        String insertSQL = "INSERT INTO " + tableName + " (Ele_name, Ele_link, py, price) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement("SELECT COUNT(*) FROM " + tableName + " WHERE Ele_name = ? AND Ele_link = ?");
             PreparedStatement insertStatement = connection.prepareStatement(insertSQL)) {

            checkStatement.setString(1, Ele_name);
            checkStatement.setString(2, Ele_link);
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count == 0) {
                insertStatement.setString(1, Ele_name);
                insertStatement.setString(2, Ele_link);
                insertStatement.setString(3, py);
                insertStatement.setString(4, price);
                insertStatement.executeUpdate();
                System.out.println("Added successfully");
            } else {
                System.out.println("The table exists");
            }

        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
    }
}
