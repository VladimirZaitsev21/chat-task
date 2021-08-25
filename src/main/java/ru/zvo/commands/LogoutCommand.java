package ru.zvo.commands;

import org.springframework.stereotype.Component;
import ru.zvo.datalayer.UserDAO;
import ru.zvo.datalayer.dto.Status;
import ru.zvo.datalayer.dto.User;
import ru.zvo.servicelayer.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LogoutCommand implements Command {

    private UserService userService;

    public LogoutCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        userService.logout(user.getId());
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
