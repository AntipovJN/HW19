package services.implementations;

import entity.User;
import factory.daoFactories.UserDaoFactory;
import factory.serviceFactories.SessionServiceFactory;
import services.interfaces.SessionService;
import services.interfaces.UserService;
import dao.interfaces.UserDao;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.zip.DataFormatException;

public class UserServiceImpl implements UserService {

    private SessionService sessionService = SessionServiceFactory.getInstance();
    private UserDao userDao = UserDaoFactory.getInstance();

    @Override
    public List<User> getAll() {
        return userDao.getAllUsers();
    }

    @Override
    public boolean signIn(String login, String pass, HttpServletRequest request) {
        User userDB = userDao.getUserByLogin(login);
        if (!Objects.isNull(userDB) && userDB.getPassword().equals(pass)) {
                userDB.setAuthorized(true);
                userDao.updateUser(userDB);
                sessionService.setUserInSession(request, userDB);
                return true;
            }
        return false;
    }

    @Override
    public boolean signUp(String login, String password, String passwordRepeat) {
        if (Objects.isNull(login) || Objects.isNull(password)
                || Objects.isNull(passwordRepeat) || (login.isEmpty())
                || (password.isEmpty()) || passwordRepeat.isEmpty()
                || !password.equals(passwordRepeat)) {
            return false;
        }
        return userDao.addUser(login, password);
    }

    @Override
    public Optional<User> getUserFromSession(HttpServletRequest request) {
        return sessionService.getUserFromSession(request);
    }

    @Override
    public boolean isLogin(HttpServletRequest request) {
        return sessionService.isLogin(request);
    }

    @Override
    public void endSession(HttpServletRequest request) {
        if (sessionService.getUserFromSession(request).isPresent()) {
            User user = userDao.getUserById(
                    sessionService.getUserFromSession(request).get().getId());
            user.setAuthorized(false);
            userDao.updateUser(user);
        }
        sessionService.removeUserFromSession(request);
    }

    @Override
    public void removeUser(int id, HttpServletRequest request) {
        if (sessionService.getUserFromSession(request).isPresent()) {
            if (sessionService.getUserFromSession(request).get().getId() == id) {
                return;
            }
        }
        userDao.removeUser(userDao.getUserById(id));
    }

    @Override
    public User getUser(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUser(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void updateUser(String login, String password, String repeatPassword, User user, HttpServletRequest request) throws DataFormatException, AuthenticationException {
        if (Objects.isNull(login) || Objects.isNull(password) || Objects.isNull(repeatPassword)
                || Objects.isNull(user) || login.isEmpty() || password.isEmpty() || !password.equals(repeatPassword)) {
            throw new DataFormatException("Wrong data");
        } if (userDao.getUserByLogin(login)!=null) {
            throw new AuthenticationException("Login already used");
        }
        user.setLogin(login);
        user.setPassword(password);
        updateUser(user);
    }
}
