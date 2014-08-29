package com.epam.am.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class H2DaoManager implements DaoManager {

    private Connection connection;

    public H2DaoManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public PaintingDao getPaintingDao() {
        return new H2PaintingDao(connection);
    }

    @Override
    public UserDao getUserDao() {
        return new H2UserDao(connection);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void openTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void closeTransaction() throws DaoException {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void rollBack() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void close() throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
