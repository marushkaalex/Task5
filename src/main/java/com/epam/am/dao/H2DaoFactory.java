package com.epam.am.dao;

import com.epam.am.database.DBConnectionManager;
import com.jolbox.bonecp.BoneCP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class H2DaoFactory implements DaoFactory {

    private static final Logger LOG = LoggerFactory.getLogger(H2DaoFactory.class);
    private static final BoneCP pool;
    private static Connection connection;
    private UserDao userDao;
    private PaintingDao paintingDao;

    static {
        BoneCP tmp = null;
        try {
            tmp = DBConnectionManager.getH2ConnectionPool();
        } catch (SQLException e) {
            LOG.error("Error while trying to get connection", e);
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
    public UserDao getUserDao() throws DaoException {
        if (userDao == null) {
            try {
                userDao = new H2UserDao(getConnection());
            } catch (SQLException e) {
                throw new DaoException("Can not get connection for user dao", e);
            }
        }
        return userDao;
    }

    @Override
    public void open() throws DaoException {
        try {
            if (connection == null || connection.isClosed()) {
                connection = pool.getConnection();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void close() throws DaoException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PaintingDao getPaintingDao() throws DaoException {
        if (paintingDao == null) {
            try {
                paintingDao = new H2PaintingDao(getConnection());
            } catch (SQLException e) {
                throw new DaoException("Can not get connection for user dao", e);
            }
        }
        return paintingDao;
    }
}
