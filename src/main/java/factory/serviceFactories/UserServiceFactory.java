package factory.serviceFactories;

import services.implementations.UserServiceImpl;
import services.interfaces.UserService;

public class UserServiceFactory {

    private static UserService userService;

    public static UserService getUserServiceImpl() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }
}
