package ru.zvo.filters;

import ru.zvo.datalayer.dto.Status;
import ru.zvo.datalayer.dto.User;
import ru.zvo.utils.ApplicationContextProvider;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OfflineUserFilter implements Filter {

    private ApplicationContextProvider contextProvider = new ApplicationContextProvider();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if ("loginCommand".equals(request.getParameter("command"))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null || user.getStatus() == Status.OFFLINE) {
                ((HttpServletResponse) servletResponse).sendRedirect(request.getContextPath() + "/login");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
