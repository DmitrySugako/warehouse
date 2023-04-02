package com.sugako.repository;

import com.sugako.domain.User;

import java.util.List;

public interface UserRepository extends CRUDRepository<Long, User> {

    void checkingAndHardDelete();

    String findByLogin(String login);
}
