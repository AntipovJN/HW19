package services.implementations;

import entity.User;
import services.interfaces.SessionService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SessionServiceImpl implements SessionService {

    @Override
    public void setUserInSession(HttpServletRequest request, User user) {
        request.getSession().setAttribute("sessionUser", user);
    }

    @Override
    public boolean isLogin(HttpServletRequest request) {
        return getUserFromSession(request).isPresent()
                && getUserFromSession(request).get().isAuthorized();
    }

    @Override
    public Optional<User> getUserFromSession(HttpServletRequest request) {
        return Optional.ofNullable((User) request.getSession().getAttribute("sessionUser"));
    }

    public void removeUserFromSession(HttpServletRequest request) {
        request.getSession().removeAttribute("sessionUser");

    }

    public boolean isAdmin(HttpServletRequest request) {
        return getUserFromSession(request).isPresent()
                && getUserFromSession(request).get().getAccessLevel().equals("ADMIN");
    }
}
