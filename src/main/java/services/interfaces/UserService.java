package services.interfaces;

import entity.User;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

public interface UserService {

    List<User> getAll();

    boolean signIn(String login, String pass, HttpServletRequest request);

    boolean signUp(String login, String pass, String passwordRepeat);

    Optional<User> getUserFromSession(HttpServletRequest request);

    boolean isLogin(HttpServletRequest request);

    void endSession(HttpServletRequest request);

    void removeUser(int id, HttpServletRequest request);

    void updateUser(User user);

    User getUser(int id);

    User getUser(String login);

    void updateUser(String login, String password,
                    String repeatPassword, User user, HttpServletRequest request) throws DataFormatException, AuthenticationException;
}
