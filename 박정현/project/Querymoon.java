package project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Querymoon {
    public static ResultSet executeQuery(String query) {
        ResultSet rs = null;
        Connection conn = MysqlConnection.getConnection();
        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
}