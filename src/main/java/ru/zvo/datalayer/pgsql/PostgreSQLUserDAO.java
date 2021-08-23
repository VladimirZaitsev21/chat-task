package ru.zvo.datalayer.pgsql;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.zvo.datalayer.UserDAO;
import ru.zvo.datalayer.dto.User;
import ru.zvo.datalayer.mappers.UserMapper;
import ru.zvo.utils.QueryProvider;

import java.util.List;

public class PostgreSQLUserDAO implements UserDAO {

    private JdbcTemplate jdbcTemplate;
    private QueryProvider queryProvider;

    public PostgreSQLUserDAO(JdbcTemplate jdbcTemplate, QueryProvider queryProvider) {
        this.jdbcTemplate = jdbcTemplate;
        this.queryProvider = queryProvider;
    }

    @Override
    public User getUserById(long id) {
        return jdbcTemplate.queryForObject(queryProvider.get("queries.getUserById"),
                new UserMapper(), id);
    }

    @Override
    public User getUserByNick(String nick) {
        try {
            return jdbcTemplate.queryForObject(queryProvider.get("queries.getUserByNick"), new UserMapper(), nick);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateUser(long userId, User user) {
        jdbcTemplate.update(queryProvider.get("queries.updateUser"), user.getNick(), user.getRole().toString().toLowerCase(), user.getStatus().toString().toLowerCase(), user.getId());
    }

    @Override
    public void saveUser(User user) {
        jdbcTemplate.update(queryProvider.get("queries.saveUser"), user.getNick(), user.getRole().toString().toLowerCase(), user.getStatus().toString().toLowerCase());
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(queryProvider.get("queries.getAllUsers"), new UserMapper());
    }
}
