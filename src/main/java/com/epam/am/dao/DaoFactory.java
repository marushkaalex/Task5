package com.epam.am.dao;

import java.sql.Connection;

public interface DaoFactory {

    public Connection getConnection() throws DaoException;

    public DaoManager getDaoManager() throws DaoException;

//    void open() throws DaoException;
//
//    void close() throws DaoException;
}
