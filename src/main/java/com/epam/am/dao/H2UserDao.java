package com.epam.am.dao;

import com.epam.am.database.DBHelper;
import com.epam.am.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.am.dao.DaoUtil.close;
import static com.epam.am.database.DBHelper.USER.*;

public class H2UserDao implements UserDao {

    private static final String FIND_BY_USERNAME = "SELECT * FROM " + TABLE + " WHERE " + USERNAME + "=?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM " + TABLE + " WHERE " + EMAIL + "=?";
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

    @Override
    public User findByUsername(String username) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? createUser(resultSet) : null;
        } catch (SQLException e) {
            throw e;
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? createUser(resultSet) : null;
        } catch (SQLException e) {
            throw e;
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement =
                    connection.prepareStatement(FIND_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? createUser(resultSet) : null;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                close(preparedStatement);
            }
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement =
                    connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? createUser(resultSet) : null;
        } catch (SQLException e) {
            throw e;
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public User find(User user) throws SQLException {
        return findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    @Override
    public long add(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setInt(4, user.getRole().ordinal());
        Date date = new Date(user.getDob().getTime());
        preparedStatement.setDate(5, date);
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating user failed: no rows affected");
        }

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        long id = -1;
        if (generatedKeys.next()) {
            id = generatedKeys.getLong(1);
            user.setId(id);
        } else {
            throw new SQLException("Creating user failed: no ID obtained");
        }
        close(preparedStatement);
        return id;
    }

    @Override
    public void remove(User user) throws SQLException {
        removeByID(user.getId());
    }

    @Override
    public void removeByID(long userId) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement(REMOVE_BY_ID);
        preparedStatement.setObject(1, userId);
        preparedStatement.execute();
        close(preparedStatement);
    }

    @Override
    public void removeByEmail(String email) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement(REMOVE_BY_EMAIL);
        preparedStatement.setObject(1, email);
        preparedStatement.execute();
        close(preparedStatement);
    }

    @Override
    public void removeByUsername(String username) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement(REMOVE_BY_USERNAME);
        preparedStatement.setObject(1, username);
        preparedStatement.execute();
        close(preparedStatement);
    }

    @Override
    public void update(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setInt(4, user.getRole().ordinal());
        preparedStatement.setDate(5, new Date(user.getDob().getTime()));
        preparedStatement.setLong(6, user.getId());
        preparedStatement.execute();
        close(preparedStatement);
    }

    @Override
    public List<User> getUserList() throws SQLException {
        List<User> result = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result.add(createUser(resultSet));
        }
        close(preparedStatement);
        return result;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
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
    }

    @Override
    public List<String> isDuplicate(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(IS_DUPLICATE);
        preparedStatement.setObject(1, user.getId());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getEmail());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> result = new ArrayList<>();

        while (resultSet.next()) {
            if (resultSet.getString(USERNAME).equals(user.getUsername())) result.add(user.getUsername());
            if (resultSet.getString(EMAIL).equals(user.getEmail())) result.add(user.getEmail());
        }
        close(preparedStatement);
        return result;
    }
}
