package ru.zvo.filters;

import ru.zvo.datalayer.dto.Status;
import ru.zvo.datalayer.dto.User;
import ru.zvo.utils.ApplicationContextProvider;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MutedUserFilter implements Filter {

    private ApplicationContextProvider contextProvider = new ApplicationContextProvider();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String command = servletRequest.getParameter("command");
        if ("messageCommand".equals(command)) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            User user = (User) request.getSession().getAttribute("user");
            if (user.getStatus() == Status.MUTED) {
                ((HttpServletResponse) servletResponse).sendRedirect(request.getContextPath() + "/chat");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
