package ru.zvo.servicelayer;

import org.springframework.stereotype.Service;
import ru.zvo.datalayer.MessageDAO;
import ru.zvo.datalayer.dto.Message;
import ru.zvo.datalayer.dto.Status;
import ru.zvo.datalayer.dto.User;
import ru.zvo.exception.BannedUserException;
import ru.zvo.exception.MutedUserException;
import ru.zvo.exception.OfflineUserException;
import java.util.List;

@Service
public class SimpleMessageService implements MessageService {

    private MessageDAO messageDAO;
    private UserService userService;

    public SimpleMessageService(MessageDAO messageDAO, UserService userService) {
        this.messageDAO = messageDAO;
        this.userService = userService;
    }

    @Override
    public List<Message> getLastMessages(int count) {
        return messageDAO.getLastMessages(count);
    }

    @Override
    public void sendMessage(String messageContent, Long userId) {
        User user = userService.getUserById(userId);
        switch (user.getStatus()) {
            case OFFLINE:
                throw new OfflineUserException();
            case MUTED:
                throw new MutedUserException();
            case BANNED:
                throw new BannedUserException();
        }
        Message message = new Message();
        message.setMessageContent(messageContent);
        message.setAuthor(user);
        messageDAO.sendMessage(message);
    }
}
