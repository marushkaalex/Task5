package com.epam.am.dao;

import com.epam.am.entity.User;

import java.util.List;

public interface UserDao {

    long add(User user) throws DaoException;

    User findByUsernameAndPassword(String username, String password) throws DaoException;

    User findByEmailAndPassword(String email, String password) throws DaoException;

    User findByUsername(String username) throws DaoException;

    User findByEmail(String email) throws DaoException;

    User find(User user) throws DaoException;

    User find(long id) throws DaoException;

    void remove(User user) throws DaoException;

    void removeByID(long userId) throws DaoException;

    void removeByEmail(String word) throws DaoException;

    void removeByUsername(String word) throws DaoException;

    void update(User user) throws DaoException;

    List<String> isDuplicate(User user) throws DaoException;

    List<User> getUserList() throws DaoException;

    List<User> getByRole(User.Role role) throws DaoException;
}
