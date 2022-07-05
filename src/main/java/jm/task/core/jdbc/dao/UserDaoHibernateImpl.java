package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();
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
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(SQL_CREATE_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.getMessage();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(SQL_DROP_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.getMessage();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.printf("User - %s добавлен в таблицу", name);
        } catch (Exception e) {
            transaction.rollback();
            e.getMessage();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User deleteThis = session.get(User.class, id);
            session.delete(deleteThis);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.getMessage();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List userList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            userList = session.createQuery("From " + User.class.getSimpleName()).list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.getMessage();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM `mydb113`.`users`").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.getMessage();
        }
    }
}
