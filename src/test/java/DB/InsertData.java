package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {

    public static void insertData(String name, String link) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnect.getConnection();
            String insertQuery = "INSERT INTO example_table (name, link) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, link);
            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DBConnect.closeConnection(connection);
        }
    }
}
