package com.sugako.repository.rowmapper;

import com.sugako.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sugako.repository.columns.UserColumns.CHANGED;
import static com.sugako.repository.columns.UserColumns.CREATED;
import static com.sugako.repository.columns.UserColumns.ID;
import static com.sugako.repository.columns.UserColumns.IS_DELETED;
import static com.sugako.repository.columns.UserColumns.LOGIN;
import static com.sugako.repository.columns.UserColumns.NAME;
import static com.sugako.repository.columns.UserColumns.PASSWORD;
import static com.sugako.repository.columns.UserColumns.ROLES_ID;
import static com.sugako.repository.columns.UserColumns.SURNAME;

@Component
public class UserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        User user;
        try{
            user=User.builder()
                    .id(rs.getLong(ID))
                    .name(rs.getString(NAME))
                    .surname(rs.getString(SURNAME))
                    .login(rs.getString(LOGIN))
                    .password(rs.getString(PASSWORD))
                    .rolesId(rs.getLong(ROLES_ID))
                    .created(rs.getTimestamp(CREATED))
                    .changed(rs.getTimestamp(CHANGED))
                    .isDeleted(rs.getBoolean(IS_DELETED))
                    .build();
        } catch (SQLException e){throw new RuntimeException(e);}
    return user;}
}
