package com.epam.am.dao;

import com.epam.am.database.DBConnectionManager;
import com.jolbox.bonecp.BoneCP;

import java.sql.Connection;
import java.sql.SQLException;

public class H2DaoFactory implements DaoFactory {

    private static final BoneCP pool;
    private static Connection connection;

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
        return connection;
    }

    @Override
    public UserDao getUserDao() throws SQLException {
        return new H2UserDao(getConnection());
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
