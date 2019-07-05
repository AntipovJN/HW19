package Controller;

import Factory.ServiceFactories.AccountServiceFactory;
import Service.Interfaces.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/pokupka", loadOnStartup = 1)
public class MainPageServlet extends HttpServlet {

    private static final AccountService ACCOUNT_SERVICE = AccountServiceFactory.getAccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (ACCOUNT_SERVICE.isLogin()) {
            req.setAttribute("userName", ACCOUNT_SERVICE.getUser().getLogin());
            req.setAttribute("users", ACCOUNT_SERVICE.getAll());
            req.getServletContext().getRequestDispatcher("/LoginMain.jsp").forward(req, resp);
        } else {
            req.getServletContext().getRequestDispatcher("/Main.jsp").forward(req, resp);
        }
    }
}
