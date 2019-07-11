package сontroller.Filters;

import utils.ResponseUtil;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value = { "/items/edit","/items/remove"})
public class AccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (!ResponseUtil.isAdminResponse((HttpServletRequest) request,
                (HttpServletResponse) response)) {
            ((HttpServletResponse) response).sendRedirect("/pokupka");
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
