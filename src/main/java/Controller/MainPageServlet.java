package Controller;

import DAO.Implementations.ItemDaoHibernateImpl;
import DAO.Implementations.UserDaoHibernateImpl;
import DAO.Interfaces.ItemDao;
import DAO.Interfaces.UserDao;
import Service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/pokupka", loadOnStartup = 1)
public class MainPageServlet extends HttpServlet {

    private static final AccountService ACCOUNT_SERVICE = AccountService.instance();
    private static final UserDao USER_DAO= UserDaoHibernateImpl.instance();
    private static final ItemDao ITEM_DAO = ItemDaoHibernateImpl.instance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ACCOUNT_SERVICE.isLogin()) {
            req.setAttribute("userName", ACCOUNT_SERVICE.getUser().getLogin());
            req.setAttribute("items",ITEM_DAO.getAll());
            req.setAttribute("users",USER_DAO.getAllUsers());
            req.getServletContext().getRequestDispatcher("/LoginMain.jsp").forward(req, resp);

        } else {
            req.getServletContext().getRequestDispatcher("/Main.jsp").forward(req, resp);
        }
    }
}
