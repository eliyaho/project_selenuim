package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateTable {

    // פונקציה ליצירת טבלה ראשית
    public static void createMainTable(String name) {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement("SELECT 1 FROM " + name.split(" ")[0] + " LIMIT 1");
             ResultSet resultSet = checkStatement.executeQuery()) {

            if (!resultSet.next()) {
                try (PreparedStatement statement = connection.prepareStatement("CREATE TABLE " + name.split(" ")[0] + " (" +
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
            System.out.println("error" + e.getMessage());
        }
    }

// פונקציה להכנסת נתונים לטבלה ראשית
    public static void insertDataIntoMainTable(String nameTable, String name, String link) {
        String tableName = nameTable;

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement("SELECT COUNT(*) FROM " + tableName + " WHERE name = ? AND link = ?");
             PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO " + tableName + " (name, link, py) VALUES (?, ?, DEFAULT)")) {

            // בדיקה האם הנתון כבר קיים בטבלה
            checkStatement.setString(1, name);
            checkStatement.setString(2, link);
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count == 0) {
                // הכנסת הנתונים לטבלה רק אם הם לא קיימים כבר
                insertStatement.setString(1, name);
                insertStatement.setString(2, link);
                insertStatement.executeUpdate();

                System.out.println("Entered successfully");
            } else {
                System.out.println("Exists");
            }
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
    }





    // פונקציה ליצירת טבלה משנית בתוך טבלה ראשית
    public static void createSubTable(String subTableName) {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement("SELECT 1 FROM " + subTableName.split(" ")[0] + " LIMIT 1");
             ResultSet resultSet = checkStatement.executeQuery()) {

            if (!resultSet.next()) {
                try (PreparedStatement statement = connection.prepareStatement("CREATE TABLE " + subTableName.split(" ")[0] + " (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "sub_name VARCHAR(50)," +
                        "sub_link VARCHAR(200)," +
                        "py VARCHAR(50)" +
                        ")")) {
                    statement.execute();
                    System.out.println("create table");
                } catch (SQLException e) {
                    System.out.println("error" + e.getMessage());
                }
            } else {
                System.out.println("The table exists");
            }
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
    }


    public static void insertDataIntoSubTable(String subTableName, String subName, String subLink, String py) {
        String tableName = subTableName;

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement("SELECT COUNT(*) FROM " + tableName + " WHERE sub_name = ? AND sub_link = ?");
             PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO " + tableName + " (sub_name, sub_link, py) VALUES (?, ?, ?)")) {

            // בדיקה האם הנתון כבר קיים בטבלה
            checkStatement.setString(1, subName);
            checkStatement.setString(2, subLink);
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count == 0) {
                // הכנסת הנתונים לטבלה רק אם הם לא קיימים כבר
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


}