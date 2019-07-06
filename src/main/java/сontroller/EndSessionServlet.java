package сontroller;

import factory.serviceFactories.AccountServiceFactory;
import services.interfaces.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/exit")
public class EndSessionServlet extends HttpServlet {

    private UserService accountService = AccountServiceFactory.getAccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        if (accountService.isLogin()) {
            accountService.endSession();
        }
        resp.sendRedirect("/pokupka");
    }
}
