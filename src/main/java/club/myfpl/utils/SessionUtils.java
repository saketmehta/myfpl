package club.myfpl.utils;

import club.myfpl.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Saket
 * Date: 11/01/17
 * Time: 10:11 PM
 */
public class SessionUtils {

    private static final String CURRENT_USER_KEY = "user";

    public static User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(CURRENT_USER_KEY);
    }

    public static void setUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute(CURRENT_USER_KEY, user);
    }
}
