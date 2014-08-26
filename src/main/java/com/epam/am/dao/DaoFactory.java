package com.epam.am.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {

    public Connection getConnection() throws SQLException;

    public UserDao getUserDao() throws DaoException;

    public PaintingDao getPaintingDao() throws DaoException;

    void open() throws DaoException;

    void close() throws DaoException;
}
