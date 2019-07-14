package dao.interfaces;

import entity.User;
import java.util.List;

public interface UserDao {

    boolean addUser(String login, String password);

    User getUserByLogin(String login);

    User getUserById(int id);

    List<User> getAllUsers();

    void updateUser(User user);

    void removeUser(User user);
}
