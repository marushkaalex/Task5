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
    public Connection getConnection() throws DaoException {
        try {
            if (connection == null || connection.isClosed()) {
                connection = pool.getConnection();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return connection;
    }

    @Override
    public DaoManager getDaoManager() throws DaoException {
        return new H2DaoManager(getConnection());
    }
}
