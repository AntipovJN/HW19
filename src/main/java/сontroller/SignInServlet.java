package сontroller;

import factory.serviceFactories.AccountServiceFactory;
import services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login")
public class SignInServlet extends HttpServlet {

    private static final UserService ACCOUNT_SERVICE = AccountServiceFactory.getAccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (ACCOUNT_SERVICE.isLogin()) {
            resp.sendRedirect("/pokupka");
            return;
        }
        req.setAttribute("process", "Sign in");
        req.setAttribute("action", "login");
        req.getServletContext().getRequestDispatcher("/Authorization.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (ACCOUNT_SERVICE.signIn(login, password)) {
            resp.sendRedirect("/pokupka");
        } else {
            req.setAttribute("isInvalid", "Invalid login or pass");
            req.setAttribute("process", "Sign In");
            req.setAttribute("action", "login");
            req.setAttribute("login", login);
            req.getServletContext().getRequestDispatcher("/Authorization.jsp").forward(req, resp);
        }
    }
}
