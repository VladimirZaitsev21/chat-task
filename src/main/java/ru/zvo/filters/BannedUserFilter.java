package ru.zvo.filters;

import ru.zvo.datalayer.dto.Status;
import ru.zvo.datalayer.dto.User;
import ru.zvo.servicelayer.UserService;
import ru.zvo.utils.ApplicationContextProvider;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BannedUserFilter implements Filter {

    private ApplicationContextProvider contextProvider = new ApplicationContextProvider();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String command = request.getParameter("command");
        if ("loginCommand".equals(command)) {
            String nick = request.getParameter("nick");
            UserService userService = (UserService) contextProvider.getApplicationContext().getBean("userService");
            User user = userService.getUserByNick(nick);
            if (user != null && user.getStatus() == Status.BANNED) {
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            User user = (User) request.getSession().getAttribute("user");
            if (user.getStatus() == Status.BANNED) {
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
