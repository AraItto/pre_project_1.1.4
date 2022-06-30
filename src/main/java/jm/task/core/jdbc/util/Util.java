package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection connection = Util.getJdbcConnection();
    private static final String URL = "jdbc:mysql://localhost:3306/mydb113";
    private static final String USER = "root";
    private static final String PASSWORD = "chmykh99";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection getJdbcConnection() {
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static Connection getNewConnection() {
        try {
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
