package com.epam.am.dao;

import com.epam.am.database.DBHelper;
import com.epam.am.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.am.dao.DaoUtil.*;
import static com.epam.am.database.DBHelper.USER.*;

public class H2UserDao implements UserDao {

    private static final String FIND_BY_USERNAME = "SELECT * FROM " + TABLE + " WHERE " + USERNAME + "=?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM " + TABLE + " WHERE " + EMAIL + "=?";
    private static final String FIND_BY_ID = "SELECT * FROM " + TABLE + " WHERE " + ID + "=?";
    private static final String FIND_BY_USERNAME_AND_PASSWORD =
            "SELECT * FROM " + TABLE + " WHERE " + USERNAME + "=? AND " + PASSWORD + "=?";
    private static final String FIND_BY_EMAIL_AND_PASSWORD =
            "SELECT * FROM " + TABLE + " WHERE " + EMAIL + "=? AND " + PASSWORD + "=?";
    private static final String ADD
            = "INSERT INTO " + TABLE + "(" + USERNAME + "," + EMAIL + ","
            + PASSWORD + "," + ROLE + "," + DATE_OF_BIRTH + ") VALUES (?,?,?,?,?)";
    private static final String REMOVE_BY_ID = "DELETE FROM " + TABLE + " WHERE " + DBHelper.USER.ID + "=?";
    private static final String REMOVE_BY_EMAIL = "DELETE FROM " + TABLE + " WHERE " + EMAIL + "=?";
    private static final String REMOVE_BY_USERNAME = "DELETE FROM " + TABLE + " WHERE " + USERNAME + "=?";
    private static final String UPDATE = "UPDATE " + TABLE + " SET " + USERNAME + "=?, "
            + EMAIL + "=?, " + PASSWORD + "=?, " + ROLE + "=?," + DATE_OF_BIRTH + "=? WHERE " + ID + "=?";
    private static final String GET_ALL = "SELECT * FROM " + TABLE;
    private static final String IS_DUPLICATE = "SELECT " + USERNAME + ", " + EMAIL + " FROM " + TABLE + " WHERE " +
            ID + "=? OR " + USERNAME + "=? OR " + EMAIL + "=?";

    private final Connection connection;

    public H2UserDao(Connection connection) {
        this.connection = connection;
    }

    private void checkConnection() throws DaoException {
        try {
            if (connection == null || connection.isClosed()) {
                throw new DaoException("no connection");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User findByUsername(String username) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            checkConnection();
            preparedStatement = prepareStatement(connection, FIND_BY_USERNAME, false, username);
//            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? createUser(resultSet) : null;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public User findByEmail(String email) throws DaoException {
        checkConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = prepareStatement(connection, FIND_BY_EMAIL, false, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? createUser(resultSet) : null;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws DaoException {
        checkConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement =
                    connection.prepareStatement(FIND_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? createUser(resultSet) : null;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            checkConnection();
            preparedStatement = prepareStatement(connection, FIND_BY_EMAIL_AND_PASSWORD, false, email, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? createUser(resultSet) : null;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public User find(User user) throws DaoException {
        return findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    @Override
    public long add(User user) throws DaoException {
        try {
            checkConnection();
            Date date = new Date(user.getDob().getTime());
            PreparedStatement preparedStatement = prepareStatement(connection, ADD, true,
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getRole().ordinal(),
                    date);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Creating user failed: no rows affected");
            }
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            long id = -1;
            if (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
                user.setId(id);
            } else {
                throw new DaoException("Creating user failed: no ID obtained");
            }
            close(preparedStatement);
            return id;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void remove(User user) throws DaoException {
        removeByID(user.getId());
    }

    @Override
    public void removeByID(long userId) throws DaoException {
        checkConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = prepareStatement(connection, REMOVE_BY_ID, false, userId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public void removeByEmail(String email) throws DaoException {
        checkConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = prepareStatement(connection, REMOVE_BY_EMAIL, false, email);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public void removeByUsername(String username) throws DaoException {
        checkConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = prepareStatement(connection, REMOVE_BY_USERNAME, false, username);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public void update(User user) throws DaoException {
        checkConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = prepareStatement(connection, UPDATE, false,
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getRole().ordinal(),
                    toSqlDate(user.getDob()),
                    user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public List<User> getUserList() throws DaoException {
        try {
            checkConnection();
            List<User> result = new ArrayList<>();
            PreparedStatement preparedStatement = prepareStatement(connection, GET_ALL, false);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(createUser(resultSet));
            }
            close(preparedStatement);
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private User createUser(ResultSet resultSet) throws DaoException {
        checkConnection();
        try {
            long id = resultSet.getLong(DBHelper.USER.ID);
            String username = resultSet.getString(USERNAME);
            String email = resultSet.getString(EMAIL);
            String password = resultSet.getString(PASSWORD);
            User.Role role = User.Role.values()[resultSet.getInt(ROLE)];
            Date dob = resultSet.getDate(DATE_OF_BIRTH);
            return new User.Builder().id(id)
                    .username(username)
                    .email(email)
                    .password(password)
                    .role(role)
                    .dateOfBirth(dob)
                    .build();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User find(long id) throws DaoException {
        checkConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = prepareStatement(connection, FIND_BY_ID, false, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? createUser(resultSet) : null;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public List<String> isDuplicate(User user) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            checkConnection();
            preparedStatement = prepareStatement(connection, IS_DUPLICATE, false,
                    user.getId(),
                    user.getUsername(),
                    user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> result = new ArrayList<>();

            while (resultSet.next()) {
                if (resultSet.getString(USERNAME).equals(user.getUsername())) result.add(user.getUsername());
                if (resultSet.getString(EMAIL).equals(user.getEmail())) result.add(user.getEmail());
            }

            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
        }
    }
}
