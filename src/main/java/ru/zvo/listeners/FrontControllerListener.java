package ru.zvo.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class FrontControllerListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext contextApp = new ClassPathXmlApplicationContext("context.xml");
        servletContextEvent.getServletContext().setAttribute("dataProvider", contextApp.getBean("dataProvider"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
