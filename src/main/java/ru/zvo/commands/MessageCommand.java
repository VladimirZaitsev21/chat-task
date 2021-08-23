package ru.zvo.commands;

import ru.zvo.datalayer.dto.User;
import ru.zvo.exception.BannedUserException;
import ru.zvo.exception.MutedUserException;
import ru.zvo.exception.OfflineUserException;
import ru.zvo.servicelayer.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MessageCommand implements Command {

    private MessageService messageService;

    public MessageCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        String messageContent = request.getParameter("messageText");
        try {
            if (messageContent.trim().length() > 0) {
                messageService.sendMessage(messageContent, user.getId());
            }
            response.sendRedirect(request.getContextPath() + "/chat");
        } catch (BannedUserException | MutedUserException | OfflineUserException e) {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
