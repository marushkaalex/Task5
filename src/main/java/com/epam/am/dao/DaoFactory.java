package com.epam.am.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {

    public Connection getConnection() throws SQLException;

    public UserDao getUserDao() throws SQLException;

    void open() throws SQLException;

    void close() throws SQLException;
}
