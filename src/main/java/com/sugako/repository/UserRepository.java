package com.sugako.repository;

import com.sugako.domain.User;

import java.util.List;

public interface UserRepository extends CRUDRepository<Long, User> {

    void checkingAndHardDelete(int amountOfDays);

    String findByLogin(String login);
}
