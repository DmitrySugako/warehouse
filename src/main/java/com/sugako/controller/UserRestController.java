package com.sugako.controller;

import com.sugako.controller.requests.UserCreateRequest;
import com.sugako.controller.requests.UserUpdateRequest;
import com.sugako.domain.User;
import com.sugako.repository.UserRepository;
import com.sugako.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable Long id) {
        User user = userService.findOne(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/search/{login}")
    public ResponseEntity<Object> findByUsers(@PathVariable String login){
        String name=userService.findByLogin(login);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{amountOfDays}")
    public ResponseEntity<Object> hardDelete(@PathVariable int amountOfDays) {
        userService.checkingAndHardDelete(amountOfDays);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "delete/{id}")
    public ResponseEntity<Object> getDeleteUser(@PathVariable Long id) {
        User user = userService.delete(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Object> updateUser(@RequestBody UserUpdateRequest request){
        User build = User.builder()
                .id(request.getId())
                .name(request.getName())
                .surname(request.getSurname())
                .login(request.getLogin())
                .password(request.getPassword())
                .rolesId(request.getRolesId())
                .created(request.getCreated())
                .changed(request.getChanged())
                .isDeleted(request.getIsDeleted())
                .build();

        User updateUser = userService.update(build);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateRequest request) {
        User build = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .login(request.getLogin())
                .password(request.getPassword())
                .rolesId(request.getRolesId())
                .build();

        User createUser = userService.create(build);

        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }


}
