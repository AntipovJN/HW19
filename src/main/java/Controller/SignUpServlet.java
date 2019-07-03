package Controller;

import Service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class SignUpServlet extends HttpServlet {
    private static final AccountService ACCOUNT_SERVICE = AccountService.instance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ACCOUNT_SERVICE.isLogin()) {
            resp.sendRedirect("/pokupka");
            return;
        }
        req.setAttribute("process", "Sign Up");
        req.setAttribute("action", "register");
        req.getServletContext().getRequestDispatcher("/Authorization.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("passwordRepeat");
        if (ACCOUNT_SERVICE.signUp(login, password, passwordRepeat)) {
            resp.sendRedirect("/pokupka");
        } else {
            req.setAttribute("isInvalid", "Login was taken or different passwords in fields");
            req.setAttribute("process", "Sign Up");
            req.setAttribute("action", "register");
            req.getServletContext().getRequestDispatcher("/Authorization.jsp").forward(req, resp);
        }
    }
}
