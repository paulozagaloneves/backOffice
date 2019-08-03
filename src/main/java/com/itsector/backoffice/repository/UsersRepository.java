package com.itsector.backoffice.repository;

import com.itsector.backoffice.domain.User;
import com.itsector.backoffice.usecase.users.gateway.UsersGateway;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UsersRepository implements UsersGateway {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper;

    public UsersRepository(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        final JdbcTemplateMapperFactory jdbcTemplateMapperFactory = JdbcTemplateMapperFactory.newInstance()
                .addAlias("user_name", "userName")
                .addAlias("create_timestamp", "createTimestamp")
                .addAlias("update_timestamp", "updateTimestamp");
        userRowMapper = jdbcTemplateMapperFactory.newRowMapper(User.class);
    }

    @Override
    public List<User> getAllUsers() {
        final String sql = "SELECT * FROM TBL_USERS ORDER BY id";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public Integer createUser(User user) {
        final String sql = "INSERT INTO TBL_USERS (id, user_name, name, password, create_timestamp,  update_timestamp) VALUES (USER_SEQ.nextval, :userName, :name, :password, CURRENT_TIMESTAMP, null)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, getInsertValues(user), keyHolder);

        return keyHolder.getKey().intValue();
    }

    private MapSqlParameterSource getInsertValues(User user) {
        MapSqlParameterSource values = new MapSqlParameterSource();
        values.addValue("userName", user.getUserName());
        values.addValue("name", user.getName());
        values.addValue("password", user.getPassword());

        return values;
    }
}
