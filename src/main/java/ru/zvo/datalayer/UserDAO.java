package ru.zvo.datalayer;

import ru.zvo.datalayer.dto.Status;
import ru.zvo.datalayer.dto.User;

import java.util.List;

public interface UserDAO {

    User getUserById(long id);

    User getUserByNick(String nick);

    void updateUser(long userId, User user);

    void saveUser(User user);

    List<User> getAllUsers();
}
