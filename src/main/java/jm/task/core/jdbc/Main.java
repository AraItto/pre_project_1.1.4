package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Util util = new Util();
        Connection connection = util.getJdbcConnection();
        System.out.println(connection);
    }
}
