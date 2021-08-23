package ru.zvo.commands;

import ru.zvo.datalayer.dto.User;
import ru.zvo.servicelayer.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MuteCommand implements Command {

    UserService userService;

    public MuteCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User admin = (User) request.getSession().getAttribute("user");
        userService.mute(admin.getNick(), request.getParameter("muteNick"));
        response.sendRedirect(request.getContextPath() + "/chat");
    }
}
