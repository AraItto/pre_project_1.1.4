package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
//        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        userDaoJDBC.createUsersTable();
//        userDaoJDBC.saveUser("Bob","Dilan", (byte) 36);
//        userDaoJDBC.saveUser("A","B", (byte) 18);
//        userDaoJDBC.saveUser("C","D",(byte) 44);
//        userDaoJDBC.saveUser("Alex", "Chmykh", (byte) 23);
//        System.out.println(userDaoJDBC.getAllUsers());
//        userDaoJDBC.cleanUsersTable();
//        userDaoJDBC.dropUsersTable();
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("A","B",(byte)2);
        userDaoHibernate.saveUser("C","D",(byte)4);
        userDaoHibernate.saveUser("Alex","Bibigo",(byte)18);
        userDaoHibernate.saveUser("Mark","Dilan",(byte)53);
        System.out.println(userDaoHibernate.getAllUsers());
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}
