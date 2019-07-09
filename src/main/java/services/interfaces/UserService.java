package services.interfaces;

import entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();

    boolean signIn(String login, String pass, HttpServletRequest request);

    boolean signUp(String login, String pass, String passwordRepeat);

    Optional<User> getUser(HttpServletRequest request);

    boolean isLogin(HttpServletRequest request);

    void endSession(HttpServletRequest request);

    void removeUser(int id);
}