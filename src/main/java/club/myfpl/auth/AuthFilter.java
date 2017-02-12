package club.myfpl.auth;

import club.myfpl.utils.SessionConstants;
import com.google.common.collect.Sets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * User: Saket
 * Date: 10/01/17
 * Time: 12:34 PM
 */
public class AuthFilter implements Filter {

    private final Set<String> allowedUrls = Sets.newHashSet("/rest/auth", "/rest/fpl");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // allow if this request is in allowedUrls
        if (isAllowedToBypass(req.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        if (req.getSession().getAttribute(SessionConstants.USER) == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please login.");
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isAllowedToBypass(String requestURI) {
        for (String uri : allowedUrls) {
            if (requestURI.startsWith(uri)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
    }
}
