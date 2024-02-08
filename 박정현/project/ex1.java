package project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ex1 {
    public static void main(String[] args) {
        String query = "SELECT * FROM attendance";
        ResultSet rs = QueryExecutor.executeQuery(query);
        displayResultSet(rs);
        MysqlConnection.closeConnection();
    }

    public static void displayResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                while (rs.next()) {
                   
                    String column1 = rs.getString("user_id");
                    String column2 = rs.getString("attendance_status");
               
                    System.out.println(column1 + "\t" + column2);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("µ¹¾Æ°¡");
        }
    }
}