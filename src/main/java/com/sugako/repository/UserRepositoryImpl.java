package com.sugako.repository;

import com.sugako.domain.User;
import com.sugako.repository.rowmapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Primary
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcCall jdbcCall;

    private final UserRowMapper userRowMapper;

    @Override
    public User findOne(Long id) {

        return jdbcTemplate.queryForObject("select * from users where id = " + id, userRowMapper);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users order by id desc", userRowMapper);
    }

    @Override
    public User create(User object) {

        final String sqlCreateQuery = "insert into users(name, surname, login, password, roles_id," +
                "created, changed) values (:name, :surname, :login, :password, :roles_id, :created, :changed)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", object.getName())
                .addValue("surname", object.getSurname())
                .addValue("login", object.getLogin())
                .addValue("password", object.getPassword())
                .addValue("roles_id", object.getRolesId())
                .addValue("created", new Timestamp(System.currentTimeMillis()))
                .addValue("changed", new Timestamp(System.currentTimeMillis()));

        namedParameterJdbcTemplate.update(sqlCreateQuery, parameterSource, holder, new String[]{"id"});

        object = findOne(holder.getKey().longValue());

        return object;


    }

    @Override
    public User update(User object) {

        final String sqlUpdateQuery = "update users set name=:name, surname=:surname, login=:login," +
                " password=:password, roles_id=:roles_id, changed=:change" +
                " where id=:id";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", object.getName())
                .addValue("surname", object.getSurname())
                .addValue("login", object.getLogin())
                .addValue("password", object.getPassword())
                .addValue("roles_id", object.getRolesId())
                .addValue("change", new Timestamp(System.currentTimeMillis()))
                .addValue("id", object.getId());

        namedParameterJdbcTemplate.update(sqlUpdateQuery, parameterSource);

        return findOne(object.getId());

    }

    @Override
    public User delete(Long id) {

        final String sqlDeleteQuery = "update users set is_deleted=:is_deleted, changed=:changed where id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("is_deleted", true)
                .addValue("changed", new Timestamp(System.currentTimeMillis()))
                .addValue("id", id);
        namedParameterJdbcTemplate.update(sqlDeleteQuery, parameterSource);


        return findOne(id);
    }

    @Override
    public void checkingAndHardDelete() {

    }

    @Override
    public String findByLogin(String login) {

        jdbcCall = new SimpleJdbcCall(jdbcTemplate).withFunctionName("search_by_login");
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("l", login);

        String name = jdbcCall.executeFunction(String.class, parameterSource);

        return name;
    }
}
