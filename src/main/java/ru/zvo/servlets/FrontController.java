package ru.zvo.servlets;

import ru.zvo.commands.Command;
import ru.zvo.utils.ApplicationContextProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("command") != null) {
            Command command = defineCommand(req.getParameter("command"));
            command.execute(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = defineCommand(req.getParameter("command"));
        command.execute(req, resp);
    }

    private Command defineCommand(String command) {
        return (Command) new ApplicationContextProvider().getApplicationContext().getBean(command);
    }

}
