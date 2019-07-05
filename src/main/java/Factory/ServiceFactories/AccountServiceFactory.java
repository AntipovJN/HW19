package Factory.ServiceFactories;

import Service.Implementations.AccountServiceImpl;
import Service.Interfaces.AccountService;

public class AccountServiceFactory {

    private static AccountService accountService;

    public static AccountService getAccountServiceImpl() {
        if (accountService == null) {
            accountService = AccountServiceImpl.instance();
        }
        return accountService;
    }
}
