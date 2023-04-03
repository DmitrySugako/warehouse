package com.sugako.service;

import com.sugako.domain.User;

import java.util.List;

public interface UserService {

    User findOne(Long id);

    List<User> findAll();

    User create(User object);

    User update(User object);

    User delete(Long id);

    void checkingAndHardDelete(int amountOfDays);

    String findByLogin(String login);
}
