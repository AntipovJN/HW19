package dao.interfaces;

import entity.User;

import java.util.List;

public interface UserDao {

    boolean addUser(String login, String password);

    User getUserByLogin(String login);

    List<User> getAllUsers();
}
