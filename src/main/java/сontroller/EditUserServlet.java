package сontroller;

import entity.User;
import factory.serviceFactories.UserServiceFactory;
import services.interfaces.UserService;
import utils.ResponseUtil;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.zip.DataFormatException;

@WebServlet(value = "/users/edit")
public class EditUserServlet extends HttpServlet {

    private static final UserService USER_SERVICE = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (ResponseUtil.isAdminResponse(req, resp)) {
            User user;
            try {
                user = USER_SERVICE.getUser(Integer.parseInt(req.getParameter("id")));
                req.setAttribute("action", "users/edit");
                req.setAttribute("process", "Edit user №" + req.getParameter("id"));
                req.setAttribute("login", user.getLogin());
                req.getSession().setAttribute("id", req.getParameter("id"));
                req.getServletContext().getRequestDispatcher("/Authorization.jsp")
                        .forward(req, resp);
            } catch (Exception e) {
                resp.getWriter().println("Invalid id");
            }
        } else {
            resp.sendRedirect("/pokupka");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (ResponseUtil.isAdminResponse(req, resp)) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String repeatPassword = req.getParameter("passwordRepeat");
            try {
                User user = USER_SERVICE.getUser(Integer.valueOf(
                        req.getSession().getAttribute("id").toString()));
                USER_SERVICE.updateUser(login, password, repeatPassword, user, req);
                resp.sendRedirect("/pokupka");
            } catch (DataFormatException | AuthenticationException e) {
                req.setAttribute("isInvalid", e.getMessage());
                req.setAttribute("login", login);
                req.setAttribute("action", "users/edit");
                req.setAttribute("process", "Change user №" + req.getParameter("id"));
                req.getServletContext().getRequestDispatcher("/Authorization.jsp")
                        .forward(req, resp);
            }
        } else {
            resp.sendRedirect("/pokupka");
        }
    }
}
