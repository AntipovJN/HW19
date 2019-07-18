package services.interfaces;

import entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    boolean signIn(String login, String pass);

    boolean signUp(String login, String pass, String passwordRepeat);

    User getUser();

    boolean isLogin();

    void endSession();
}
