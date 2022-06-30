package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        Connection connection = Util.getNewConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS `mydb113`.`users` (" +
                    "`id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "        `name` VARCHAR(45) NOT NULL,\n" +
                    "        `last_name` VARCHAR(45) NOT NULL,\n" +
                    "        `age` INT(3) NOT NULL,\n" +
                    "        PRIMARY KEY (`id`))\n" +
                    "        ENGINE = InnoDB\n" +
                    "        DEFAULT CHARACTER SET = utf8");
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        Connection connection = Util.getNewConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS `mydb113`.`users`");
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getNewConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            if (preparedStatement.executeUpdate() > 0) {
                System.out.printf("User с именем – %s добавлен в базу данных %n", name);
            }
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getNewConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        Connection connection = Util.getNewConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, last_name, age FROM users");
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
            connection.commit();
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        Connection connection = Util.getNewConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM `mydb113`.`users`");
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
