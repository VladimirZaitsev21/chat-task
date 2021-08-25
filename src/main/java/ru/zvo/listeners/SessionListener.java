package ru.zvo.listeners;

import ru.zvo.datalayer.dto.User;
import ru.zvo.servicelayer.UserService;
import ru.zvo.utils.ApplicationContextProvider;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    ApplicationContextProvider contextProvider = new ApplicationContextProvider();
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setMaxInactiveInterval(500);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession httpSession = httpSessionEvent.getSession();
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            UserService userService = (UserService) contextProvider.getApplicationContext().getBean(UserService.class);
            userService.logout(user.getId());
        }
    }
}
