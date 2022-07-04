package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = Util.getUtil().getSessionFactory();
    private static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS `users` (" +
            "`id` INT NOT NULL AUTO_INCREMENT,\n" +
            "        `name` VARCHAR(45) NOT NULL,\n" +
            "        `lastName` VARCHAR(45) NOT NULL,\n" +
            "        `age` INT(3) NOT NULL,\n" +
            "        PRIMARY KEY (`id`))\n" +
            "        ENGINE = InnoDB\n" +
            "        DEFAULT CHARACTER SET = utf8";
    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS `mydb113`.`users`";
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery(SQL_CREATE_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery(SQL_DROP_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.printf("User - %s добавлен в таблицу", name);
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            User deleteThis = session.get(User.class, id);
            session.delete(deleteThis);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List userList = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            userList = session.createQuery("From " + User.class.getSimpleName()).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM `mydb113`.`users`").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        }
    }
}
