package com.epam.am.dao;

import com.epam.am.database.DBConnectionManager;
import com.jolbox.bonecp.BoneCP;

import java.sql.Connection;
import java.sql.SQLException;

public class H2DaoFactory implements DaoFactory {

    private static final BoneCP pool;
    private static Connection connection;
    private UserDao userDao;

    static {
        BoneCP tmp = null;
        try {
            tmp = DBConnectionManager.getH2ConnectionPool();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pool = tmp;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = pool.getConnection();
        }
        return connection;
    }

    @Override
    public UserDao getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = new H2UserDao(getConnection());
        }
        return userDao;
    }

    @Override
    public void open() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = pool.getConnection();
        }
    }

    @Override
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
