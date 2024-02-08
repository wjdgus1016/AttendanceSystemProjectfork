package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {
    private static final String _driver = "com.mysql.cj.jdbc.Driver";
    private static final String _url = "jdbc:mysql://localhost:3306/attendancesystem";
    private static final String _username = "root";
    private static final String _password = "1234";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(_driver);
                connection = DriverManager.getConnection(_url, _username, _password);
                System.out.println("DB ¿¬°áµÊ.");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println(" ¿¬°á ²÷±è ");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
