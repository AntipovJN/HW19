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

    private static final UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ResponseUtil.isAdminResponse(req, resp);
        User user;
        try {
            user = userService.getUser(Integer.parseInt(req.getParameter("id")));
            req.getSession().setAttribute("id",req.getParameter("id"));
            req.setAttribute("action", "users/edit");
            req.setAttribute("process", "Change user №" + req.getParameter("id"));
            req.setAttribute("login", user.getLogin());
            req.getServletContext().getRequestDispatcher("/Authorization.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.getWriter().println("Nonexistent id");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ResponseUtil.isAdminResponse(req, resp);
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("passwordRepeat");
        try {
            User user = userService.getUser(Integer.parseInt(
                    req.getSession().getAttribute("id").toString()));
            userService.updateUser(login, password, repeatPassword, user, req);
            resp.sendRedirect("/pokupka");
        } catch (DataFormatException | AuthenticationException e) {
            req.setAttribute("isInvalid", e.getMessage());
            req.setAttribute("login", login);
            req.setAttribute("action", "users/edit");
            req.setAttribute("process", "Change user №" + req.getParameter("id"));
            req.getServletContext().getRequestDispatcher("/Authorization.jsp").forward(req, resp);
        }
    }
}
