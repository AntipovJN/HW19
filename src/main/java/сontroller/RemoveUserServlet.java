package сontroller;

import factory.serviceFactories.UserServiceFactory;
import services.interfaces.UserService;
import utils.ResponseUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/users/remove")
public class RemoveUserServlet extends HttpServlet {

    private static final UserService USER_SERVICE = UserServiceFactory.getUserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ResponseUtil.isAdminResponse(req, resp);
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            USER_SERVICE.removeUser(id, req);
            resp.sendRedirect("/pokupka");
        } catch (NumberFormatException | NullPointerException e) {
            resp.getWriter().println("<h1>Invalid id</h1> \n <a href=\"/pokupka\"> Get Back </a>");
        }
    }
}

