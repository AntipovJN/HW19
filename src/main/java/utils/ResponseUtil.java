package utils;

import factory.serviceFactories.SessionServiceFactory;
import services.interfaces.SessionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    private static final String MAIN_PAGE = "/pokupka";
    private final static SessionService SESSION_SERVICE = SessionServiceFactory.getInstance();

    public static boolean isAdminResponse(HttpServletRequest request,
                                          HttpServletResponse response) throws IOException {
        return SESSION_SERVICE.isAdmin(request);
    }

    public static boolean checkLoginResponse(HttpServletRequest request,
                                             HttpServletResponse response) throws IOException {
        return SESSION_SERVICE.isLogin(request);
    }

    public static boolean checkNotLoginResponse(HttpServletRequest request,
                                                HttpServletResponse response) throws IOException {
        return !SESSION_SERVICE.isLogin(request);
    }
}
