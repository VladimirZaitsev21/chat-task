package ru.zvo.datalayer.pgsql;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.zvo.datalayer.MessageDAO;
import ru.zvo.datalayer.dto.Message;
import ru.zvo.datalayer.mappers.MessageMapper;
import ru.zvo.utils.QueryProvider;

import java.util.List;

public class PostgreSQLMessageDAO implements MessageDAO {

    private JdbcTemplate jdbcTemplate;
    private QueryProvider queryProvider;

    public PostgreSQLMessageDAO(JdbcTemplate jdbcTemplate, QueryProvider queryProvider) {
        this.jdbcTemplate = jdbcTemplate;
        this.queryProvider = queryProvider;
    }

    @Override
    public List<Message> getLastMessages(int number) {
        return jdbcTemplate.query(queryProvider.get("queries.getLast"), new MessageMapper(), number);
    }

    @Override
    public void sendMessage(Message message) {
        jdbcTemplate.update(queryProvider.get("queries.sendMessage"), message.getAuthor().getId(), message.getMessageContent());
    }
}
