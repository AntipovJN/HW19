package сontroller.Servlets;

import factory.serviceFactories.SessionServiceFactory;
import factory.serviceFactories.UserServiceFactory;
import services.interfaces.SessionService;
import services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/pokupka", loadOnStartup = 1)
public class MainPageServlet extends HttpServlet {

    private static final UserService ACCOUNT_SERVICE = UserServiceFactory.getInstance();
    private static final SessionService SESSION_SERVICE = SessionServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (ACCOUNT_SERVICE.isLogin(req)) {
            req.setAttribute("userName", SESSION_SERVICE.getUserFromSession(req).get().getLogin());
            req.setAttribute("users", ACCOUNT_SERVICE.getAll());
            req.setAttribute("isAdmin", SESSION_SERVICE.isAdmin(req));
            req.getServletContext().getRequestDispatcher("/LoginMain.jsp").forward(req, resp);
        } else {
            req.getServletContext().getRequestDispatcher("/Main.jsp").forward(req, resp);
        }
    }
}
