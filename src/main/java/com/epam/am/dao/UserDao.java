package com.epam.am.dao;

import com.epam.am.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface UserDao {

    void add(User user) throws SQLException;

    void remove(User user);

    void remove(UUID userId);

    void remove(String word, boolean isFirstParUsername);

    void update(User user);

    User findByCredentials(String username, String password, boolean isFirstParUsername);

    List<User> getUserList();
}
