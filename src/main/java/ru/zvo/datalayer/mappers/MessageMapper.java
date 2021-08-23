package ru.zvo.datalayer.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.zvo.datalayer.dto.Message;
import ru.zvo.datalayer.dto.Role;
import ru.zvo.datalayer.dto.Status;
import ru.zvo.datalayer.dto.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class MessageMapper implements RowMapper<Message> {
    @Override
    public Message mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Message(resultSet.getLong("message id"),
                resultSet.getTimestamp("time"),
                resultSet.getString("content"),
                new User(
                        resultSet.getLong("user id"),
                        resultSet.getString("user nick"),
                        Role.valueOf(resultSet.getString("user role").toUpperCase()),
                        Status.valueOf(resultSet.getString("user status").toUpperCase())
                ));
    }
}
