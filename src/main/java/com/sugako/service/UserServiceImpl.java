package com.sugako.service;

import com.sugako.domain.User;
import com.sugako.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User object) {
        return userRepository.create(object);
    }

    @Override
    public User update(User object) {
        return userRepository.update(object);
    }

    @Override
    public User delete(Long id) {
        return userRepository.delete(id);
    }

    @Override
    public void checkingAndHardDelete() {
        userRepository.checkingAndHardDelete();

    }

    @Override
    public String findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
