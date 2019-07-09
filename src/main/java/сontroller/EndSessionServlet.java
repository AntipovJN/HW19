package сontroller;

import factory.serviceFactories.UserServiceFactory;
import services.interfaces.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/exit")
public class EndSessionServlet extends HttpServlet {

    private UserService accountService = UserServiceFactory.getUserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        if (accountService.isLogin(req)) {
            accountService.endSession(req);
        }
        resp.sendRedirect("/pokupka");
    }
}
