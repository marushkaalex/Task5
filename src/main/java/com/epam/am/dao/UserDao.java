package com.epam.am.dao;

import com.epam.am.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    long add(User user) throws SQLException;

    User findByUsernameAndPassword(String username, String password) throws SQLException;

    User findByEmailAndPassword(String email, String password) throws SQLException;

    User findByUsername(String username) throws SQLException;

    User findByEmail(String email) throws SQLException;

    User find(User user) throws SQLException;

    User find(long id) throws SQLException;

    void remove(User user) throws SQLException;

    void removeByID(long userId) throws SQLException;

    void removeByEmail(String word) throws SQLException;

    void removeByUsername(String word) throws SQLException;

    void update(User user) throws SQLException;

    List<String> isDuplicate(User user) throws SQLException;

    List<User> getUserList() throws SQLException;
}
