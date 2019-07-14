package factory.serviceFactories;

import services.implementations.SessionServiceImpl;

public class SessionServiceFactory {

    private static SessionServiceImpl sessionService;

    public static SessionServiceImpl getInstance() {
        if (sessionService == null) {
            sessionService = new SessionServiceImpl();
        }
        return sessionService;
    }
}
