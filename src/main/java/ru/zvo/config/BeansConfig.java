package ru.zvo.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.zvo.commands.*;
import ru.zvo.datalayer.MessageDAO;
import ru.zvo.datalayer.pgsql.PostgreSQLMessageDAO;
import ru.zvo.datalayer.pgsql.PostgreSQLUserDAO;
import ru.zvo.datalayer.UserDAO;
import ru.zvo.servicelayer.MessageService;
import ru.zvo.servicelayer.SimpleMessageService;
import ru.zvo.servicelayer.SimpleUserService;
import ru.zvo.servicelayer.UserService;
import ru.zvo.utils.DataProvider;
import ru.zvo.utils.QueryProvider;


import javax.sql.DataSource;
import java.util.ResourceBundle;

@Configuration
public class BeansConfig {

    @Bean
    public DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(5432);
        dataSource.setDatabaseName("postgres");
        dataSource.setUser("postgres");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public QueryProvider queryProvider(){
        return new QueryProvider(ResourceBundle.getBundle("queries"));
    }

    @Bean
    public UserDAO userDAO() {
        return new PostgreSQLUserDAO(jdbcTemplate(), queryProvider());
    }

    @Bean
    public MessageDAO messageDAO() {
        return new PostgreSQLMessageDAO(jdbcTemplate(), queryProvider());
    }

    @Bean
    public UserService userService() {
        return new SimpleUserService(userDAO());
    }

    @Bean
    public MessageService messageService() {
        return new SimpleMessageService(messageDAO(), userService());
    }

    @Bean
    public Command loginCommand() {
        return new LoginCommand(userService());
    }

    @Bean
    public Command logoutCommand() {
        return new LogoutCommand(userService());
    }

    @Bean
    public Command messageCommand() {
        return new MessageCommand(messageService());
    }

    @Bean
    public Command banCommand() {
        return new BanCommand(userService());
    }

    @Bean
    public Command muteCommand() {
        return new MuteCommand(userService());
    }

    @Bean
    public Command unbanCommand() {
        return new UnbanCommand(userService());
    }

    @Bean
    public Command unmuteCommand() {
        return new UnmuteCommand(userService());
    }

    @Bean
    public DataProvider dataProvider() {
        return new DataProvider(messageService(), userService());
    }

}
