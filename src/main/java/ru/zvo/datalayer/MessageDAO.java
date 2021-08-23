package ru.zvo.datalayer;

import ru.zvo.datalayer.dto.Message;

import java.util.List;

public interface MessageDAO {

    List<Message> getLastMessages(int number);

    void sendMessage(Message message);

}
