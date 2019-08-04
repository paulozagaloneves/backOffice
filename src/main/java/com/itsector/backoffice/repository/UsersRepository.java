package com.itsector.backoffice.repository;

import com.itsector.backoffice.domain.User;
import com.itsector.backoffice.usecase.users.gateway.UsersGateway;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.itsector.backoffice.repository.RepositoryUtils.optionalResult;

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

        final String sql = "INSERT INTO TBL_USERS (id, user_name, name, password, create_timestamp, update_timestamp) VALUES (USER_SEQ.nextval, :userName, :name, :password, CURRENT_TIMESTAMP, null)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, getInsertValues(user), keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer deleteUser(Integer id) {
        String sql = "DELETE FROM TBL_USERS WHERE ID = :id";
        return jdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    @Override
    public Integer updateUser(User user) {
        final String sql = "UPDATE TBL_USERS SET name = :name, password = :password, update_timestamp = CURRENT_TIMESTAMP WHERE ID = :id";
        return jdbcTemplate.update(sql, generateParams(user));
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        final String sql = "SELECT * FROM TBL_USERS WHERE id = :id";

        return optionalResult(() ->
                jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id), userRowMapper));
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
        final String sql = "SELECT * FROM TBL_USERS WHERE user_name = :userName";

        return optionalResult(() ->
                jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("userName", userName), userRowMapper));
    }

    private MapSqlParameterSource generateParams(User user) {
        final MapSqlParameterSource params = new MapSqlParameterSource("id", user.getId());
        params.addValue("name", user.getName());
        params.addValue("password", user.getPassword());

        return params;
    }

    private MapSqlParameterSource getInsertValues(User user) {
        MapSqlParameterSource values = new MapSqlParameterSource();
        values.addValue("userName", user.getUserName());
        values.addValue("name", user.getName());
        values.addValue("password", user.getPassword());

        return values;
    }
}
