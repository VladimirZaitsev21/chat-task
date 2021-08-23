package ru.zvo.datalayer.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.zvo.datalayer.dto.Role;
import ru.zvo.datalayer.dto.Status;
import ru.zvo.datalayer.dto.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return new User(resultSet.getLong(1),
                resultSet.getString(2),
                Role.valueOf(resultSet.getString(3).toUpperCase()),
                Status.valueOf(resultSet.getString(4).toUpperCase()));
    }
}
