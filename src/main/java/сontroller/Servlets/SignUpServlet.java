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

@WebServlet(value = "/register")
public class SignUpServlet extends HttpServlet {

    private static final UserService ACCOUNT_SERVICE = UserServiceFactory.getInstance();
    private static final SessionService SESSION_SERVICE = SessionServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (!ACCOUNT_SERVICE.isLogin(req)
                || ACCOUNT_SERVICE.getUserFromSession(req).get().getLogin().equals("admin")) {
            req.setAttribute("process", "Sign Up");
            req.setAttribute("action", "register");
            req.getServletContext().getRequestDispatcher("/Authorization.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (!ACCOUNT_SERVICE.isLogin(req)
                || ACCOUNT_SERVICE.getUserFromSession(req).get().getLogin().equals("admin")) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String passwordRepeat = req.getParameter("passwordRepeat");
            if (!ACCOUNT_SERVICE.signUp(login, password, passwordRepeat)) {
                req.setAttribute("process", "Sign Up");
                req.setAttribute("action", "register");
                req.setAttribute("login", login);
                req.setAttribute("isInvalid", "Login was taken or passwords are not equals");
                req.getServletContext().getRequestDispatcher("/Authorization.jsp")
                        .forward(req, resp);
            }
        }
        resp.sendRedirect("/pokupka");
    }
}