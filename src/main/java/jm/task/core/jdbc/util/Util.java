package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    // реализуйте настройку соеденения с БД
    public Connection getJdbcConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb113","root","chmykh99");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
