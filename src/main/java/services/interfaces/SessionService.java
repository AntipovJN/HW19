package services.interfaces;

import entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface SessionService {

    void setUserInSession(HttpServletRequest request, User user);

    boolean isLogin(HttpServletRequest request);

    Optional<User> getUserFromSession(HttpServletRequest request);

    void removeUserFromSession(HttpServletRequest request);

    boolean isAdmin(HttpServletRequest request);
}
