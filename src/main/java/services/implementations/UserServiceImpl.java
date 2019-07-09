package services.implementations;

import entity.User;
import factory.daoFactories.UserDaoFactory;
import factory.serviceFactories.SessionServiceFactory;
import services.interfaces.SessionService;
import services.interfaces.UserService;
import dao.interfaces.UserDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private SessionService sessionService = SessionServiceFactory.getInstance();
    private UserDao userDao = UserDaoFactory.getUserDaoImpl();

    @Override
    public List<User> getAll() {
        return userDao.getAllUsers();
    }

    @Override
    public boolean signIn(String login, String pass, HttpServletRequest request) {
        User userDB = userDao.getUserByLogin(login);
        if (userDB != null) {
            if (userDB.getPassword().equals(pass)) {
                userDB.setAuthorized(true);
                userDao.updateUser(userDB);
                sessionService.setUserInSession(request, userDB);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean signUp(String login, String password, String passwordRepeat) {
        if ((Objects.isNull(login)) || Objects.isNull(password) || Objects.isNull(passwordRepeat)
                || (login.isEmpty()) || (password.isEmpty()) || (passwordRepeat.isEmpty())) {
            return false;
        }
        if (password.equals(passwordRepeat)) {
            return userDao.addUser(login, password);
        }
        return false;
    }

    @Override
    public Optional<User> getUser(HttpServletRequest request) {
        return sessionService.getUserFromSession(request);
    }

    @Override
    public boolean isLogin(HttpServletRequest request) {
        return sessionService.isLogin(request);
    }

    @Override
    public void endSession(HttpServletRequest request) {
        if (sessionService.getUserFromSession(request).isPresent()) {
            User user = userDao.getUserById(sessionService.getUserFromSession(request).get().getId());
            user.setAuthorized(false);
            userDao.updateUser(user);
        }
        sessionService.removeUserFromSession(request);
    }

    @Override
    public void removeUser(int id) {
        userDao.removeUser(userDao.getUserById(id));
    }


}