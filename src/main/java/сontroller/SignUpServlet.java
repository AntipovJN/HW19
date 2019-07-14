package сontroller;

import factory.serviceFactories.UserServiceFactory;
import services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class SignUpServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (!userService.isLogin(req)
                || userService.getUserFromSession(req).get().getLogin().equals("admin")) {
            req.setAttribute("process", "Sign Up");
            req.setAttribute("action", "register");
            req.getServletContext().getRequestDispatcher("/Authorization.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (!userService.isLogin(req)
                || userService.getUserFromSession(req).get().getLogin().equals("admin")) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String passwordRepeat = req.getParameter("passwordRepeat");
            if (userService.signUp(login, password, passwordRepeat)) {
                resp.sendRedirect("/pokupka");
            } else {
                req.setAttribute("process", "Sign Up");
                req.setAttribute("action", "register");
                req.setAttribute("login", login);
                req.setAttribute("isInvalid", "Login was taken or passwords are not equals");
                req.getServletContext().getRequestDispatcher("/Authorization.jsp")
                        .forward(req, resp);
            }
        }
    }
}
