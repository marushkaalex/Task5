package com.epam.am.dao;

import java.sql.Connection;

public interface DaoManager extends AutoCloseable {

    PaintingDao getPaintingDao();

    UserDao getUserDao();

    Connection getConnection();

    void openTransaction() throws DaoException;

    void closeTransaction() throws DaoException;

    void rollBack() throws DaoException;

    void commit() throws DaoException;

    void close() throws DaoException;
}
