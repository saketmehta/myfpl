package club.myfpl.auth;

import club.myfpl.utils.SessionConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Saket
 * Date: 10/01/17
 * Time: 12:34 PM
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // allow if this request is for login endpoint
        if (req.getRequestURI().startsWith("/rest/auth")) {
            chain.doFilter(request, response);
            return;
        }

        if (req.getSession().getAttribute(SessionConstants.USER) == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please login.");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
