package ru.zvo.commands;

import org.springframework.stereotype.Component;
import ru.zvo.exception.BannedUserException;
import ru.zvo.servicelayer.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginCommand implements Command {

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nick = request.getParameter("nick");
        if (nick.trim().length() > 0) {
            try {
                userService.login(nick);
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("user", userService.getUserByNick(nick));
                response.sendRedirect(request.getContextPath() + "/chat");
            } catch (BannedUserException e) {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

}
