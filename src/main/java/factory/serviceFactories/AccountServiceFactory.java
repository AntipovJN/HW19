package factory.serviceFactories;

import services.implementations.UserServiceImpl;
import services.interfaces.UserService;

public class AccountServiceFactory {

    private static UserService accountService;

    public static UserService getAccountServiceImpl() {
        if (accountService == null) {
            accountService = UserServiceImpl.instance();
        }
        return accountService;
    }
}
