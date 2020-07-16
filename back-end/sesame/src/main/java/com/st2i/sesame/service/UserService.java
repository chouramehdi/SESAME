package com.st2i.sesame.service;

import java.util.List;

import com.st2i.sesame.entities.User;


public interface UserService {
    void addUser(User user);
    User findOne(Long id);
    List<User> getAll();
    void delete(Long id);
}
