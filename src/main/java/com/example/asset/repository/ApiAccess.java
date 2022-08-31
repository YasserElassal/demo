package com.example.asset.repository;

import com.example.asset.model.User;

import java.util.List;

public interface ApiAccess {
    User getOneUSer(int id);

    void insertUser(User user);

    List<User> getAllUSer();

    void updateUser(User user);

    void deleteUser(int id);

    User getByPhone(int phone);
}
