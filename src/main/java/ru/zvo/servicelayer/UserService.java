package ru.zvo.servicelayer;

import ru.zvo.datalayer.dto.User;

import java.util.List;

public interface UserService {

    void login(String nick);

    void logout(long id);

    void ban(String adminNick, String userNick);

    void unban(String adminNick, String userNick);

    void mute(String adminNick, String userNick);

    void unmute(String adminNick, String userNick);

    User getUserByNick(String nick);

    User getUserById(long id);

    List<User> getALlUsers();
}
