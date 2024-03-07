package DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    // פונקציה ליצירת טבלה חדשה
    public static void createTable(String name) {
        // משתנים לשימוש במהלך הפונקציה
        Connection connection = null;
        Statement statement = null;
        System.out.println("class creat"+name);
        try {
            // יצירת חיבור לבסיס הנתונים
            connection = DBConnect.getConnection();
            // יצירת אובייקט סטייטמנט
            statement = connection.createStatement();

            // שאילתא ליצירת הטבלה, במידה והיא לא קיימת כבר
            String createTableSQL;
            if (name.contains(" ")) {
                // נמצא שם שמורכב משניים או יותר מילים - נחלץ את המילה הראשונה בלבד
                String[] parts = name.split("\\s+");
                String firstName = parts[0];
                createTableSQL = "CREATE TABLE IF NOT EXISTS " + firstName + " (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(50)" +
                        ")";
            } else {
                // אחרת, השם אחד ואפשר להשתמש בו כפי שהוא
                createTableSQL = "CREATE TABLE IF NOT EXISTS " + name + " (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(50)" +
                        ")";
            }
            // ביצוע השאילתא
            statement.execute(createTableSQL);

            // הדפסה שהטבלה נוצרה בהצלחה
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            // במידה שיש שגיאה, הדפסתה
            e.printStackTrace();
        } finally {
            // סגירת הסטייטמנט
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // סגירת החיבור לבסיס הנתונים
            DBConnect.closeConnection(connection);
        }
    }

}
