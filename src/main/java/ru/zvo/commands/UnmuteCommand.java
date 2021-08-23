package ru.zvo.commands;

import ru.zvo.datalayer.dto.User;
import ru.zvo.servicelayer.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnmuteCommand implements Command {

    UserService userService;

    public UnmuteCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User admin = (User) request.getSession().getAttribute("user");
        userService.unmute(admin.getNick(), request.getParameter("unmuteNick"));
        response.sendRedirect(request.getContextPath() + "/chat");
    }
}
