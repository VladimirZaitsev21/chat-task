package ru.zvo.commands;

import org.springframework.stereotype.Component;
import ru.zvo.datalayer.dto.User;
import ru.zvo.servicelayer.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class BanCommand implements Command {

    UserService userService;

    public BanCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User admin = (User) request.getSession().getAttribute("user");
        userService.ban(admin.getNick(), request.getParameter("banNick"));
        response.sendRedirect(request.getContextPath() + "/chat");
    }
}
