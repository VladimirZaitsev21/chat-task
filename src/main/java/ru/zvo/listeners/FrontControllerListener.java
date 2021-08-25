package ru.zvo.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.zvo.config.BeansConfig;
import ru.zvo.utils.DataProvider;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class FrontControllerListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        ApplicationContext contextApp = new ClassPathXmlApplicationContext("context.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);
        servletContextEvent.getServletContext().setAttribute("dataProvider", context.getBean(DataProvider.class));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
