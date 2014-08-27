package com.epam.am.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DaoUtil {

    private static final Logger log = LoggerFactory.getLogger(DaoUtil.class);

    private DaoUtil() {
    }

    public static PreparedStatement prepareStatement(Connection connection, String sql,
                                                     boolean returnGeneratedKeys, Object... values) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(sql,
                returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        setValues(preparedStatement, values);
        return preparedStatement;
    }

    public static void setValues(PreparedStatement preparedStatement, Object... values)
            throws SQLException {
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
        }
    }

    public static Date toSqlDate(java.util.Date date) {
        return (date != null) ? new Date(date.getTime()) : null;
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Closing Connection failed", e);
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error("Closing Statement failed", e);
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error("Closing ResultSet failed", e);
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection connection, Statement statement) {
        close(statement);
        close(connection);
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        close(resultSet);
        close(statement);
        close(connection);
    }

    public static void checkConnection(Connection connection) throws DaoException {
        try {
            if (connection == null || connection.isClosed()) {
                throw new DaoException("no connection");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
