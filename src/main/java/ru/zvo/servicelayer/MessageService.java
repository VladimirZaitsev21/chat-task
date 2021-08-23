package ru.zvo.servicelayer;

import ru.zvo.datalayer.dto.Message;

import java.util.List;

public interface MessageService {

    public List<Message> getLastMessages(int count);

    void sendMessage(String message, Long userId);

}
