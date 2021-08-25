package ru.zvo.utils;

import org.springframework.stereotype.Repository;
import ru.zvo.datalayer.dto.Message;
import ru.zvo.datalayer.dto.User;
import ru.zvo.servicelayer.MessageService;
import ru.zvo.servicelayer.UserService;

import java.util.List;

@Repository
public class DataProvider {

    private final MessageService messageService;
    private final UserService userService;

    public DataProvider(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    public List<User> getAllUsers() {
        return userService.getALlUsers();
    }

    public List<Message> getAllMessages() {
        return messageService.getLastMessages(9);
    }
}
