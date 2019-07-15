package factory.serviceFactories;

import services.implementations.MailServiceImpl;
import services.interfaces.MailService;

public class MailServiceFactory {

    private static MailService mailService;

    public static MailService getInstance() {
        if (mailService == null) {
            mailService = new MailServiceImpl();
        }
        return mailService;
    }
}
