package ru.zvo.servicelayer;

import org.springframework.stereotype.Service;
import ru.zvo.datalayer.UserDAO;
import ru.zvo.datalayer.dto.Role;
import ru.zvo.datalayer.dto.Status;
import ru.zvo.datalayer.dto.User;
import ru.zvo.exception.AdminException;
import ru.zvo.exception.BannedUserException;
import ru.zvo.exception.NotAdminException;
import ru.zvo.exception.OfflineUserException;

import java.util.List;

@Service
public class SimpleUserService implements UserService {

    private UserDAO userDAO;

    public SimpleUserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void login(String nick) {
        User user = userDAO.getUserByNick(nick);
        if (user != null) {
            if (user.getStatus() == Status.MUTED) {
                return;
            }
            if (user.getStatus() != Status.BANNED) {
                user.setStatus(Status.ONLINE);
                userDAO.updateUser(user.getId(), user);
            } else {
                throw new BannedUserException();
            }
        } else {
            user = new User();
            user.setNick(nick);
            user.setRole(getUserRoleByNick(nick));
            user.setStatus(Status.ONLINE);
            userDAO.saveUser(user);
        }
    }

    @Override
    public void logout(long id) {
        User user = userDAO.getUserById(id);
        user.setStatus(Status.OFFLINE);
        userDAO.updateUser(user.getId(), user);
    }

    @Override
    public void ban(String adminNick, String userNick) {
        User admin = userDAO.getUserByNick(adminNick);
        User user = userDAO.getUserByNick(userNick);
        if (admin.getStatus() != Status.OFFLINE) {
            if (admin.getRole() == Role.ADMIN) {
                if (user.getRole() != Role.ADMIN) {
                    user.setStatus(Status.BANNED);
                    userDAO.updateUser(user.getId(), user);
                }
            } else {
                throw new NotAdminException(adminNick + " is not admin");
            }
        } else {
            throw new OfflineUserException();
        }
    }

    @Override
    public void unban(String adminNick, String userNick) {
        User admin = userDAO.getUserByNick(adminNick);
        User user = userDAO.getUserByNick(userNick);
        if (admin.getStatus() != Status.OFFLINE) {
            if (admin.getRole() == Role.ADMIN) {
                if (user.getStatus() == Status.BANNED) {
                    user.setStatus(Status.OFFLINE);
                    userDAO.updateUser(user.getId(), user);
                }
            } else {
                throw new NotAdminException(adminNick + " is not admin");
            }
        } else {
            throw new OfflineUserException();
        }
    }

    @Override
    public void mute(String adminNick, String userNick) {
        User admin = userDAO.getUserByNick(adminNick);
        User user = userDAO.getUserByNick(userNick);
        if (admin.getStatus() != Status.OFFLINE) {
            if (admin.getRole() == Role.ADMIN) {
                if (user.getRole() != Role.ADMIN) {
                    user.setStatus(Status.MUTED);
                    userDAO.updateUser(user.getId(), user);
                } else {
                    throw new AdminException(userNick + " is admin and can't be muted");
                }
            } else {
                throw new NotAdminException(adminNick + " is not admin to be able to mute");
            }
        } else {
            throw new OfflineUserException();
        }
    }

    @Override
    public void unmute(String adminNick, String userNick) {
        User admin = userDAO.getUserByNick(adminNick);
        User user = userDAO.getUserByNick(userNick);
        if (admin.getStatus() != Status.OFFLINE) {
            if (admin.getRole() == Role.ADMIN) {
                if (user.getStatus() == Status.MUTED) {
                    user.setStatus(Status.OFFLINE);
                    userDAO.updateUser(user.getId(), user);
                }
            } else {
                throw new NotAdminException(adminNick + " is not admin");
            }
        } else {
            throw new OfflineUserException();
        }
    }

    @Override
    public User getUserByNick(String nick) {
        return userDAO.getUserByNick(nick);
    }

    @Override
    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public List<User> getALlUsers() {
        return userDAO.getAllUsers();
    }

    private Role getUserRoleByNick(String nick) {
        if (nick.endsWith("@sber.ru")) {
            return Role.ADMIN;
        } else {
            return Role.USER;
        }
    }
}
