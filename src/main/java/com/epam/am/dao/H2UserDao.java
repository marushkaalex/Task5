package com.epam.am.dao;

import com.epam.am.database.DBConnectionManager;
import com.epam.am.database.DBHelper;
import com.epam.am.entity.User;
import com.jolbox.bonecp.BoneCP;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.epam.am.database.DBHelper.USER.*;

public class H2UserDao implements UserDao {

    private static final String FIND_BY_USERNAME = "SELECT * FROM " + TABLE + " WHERE " + USERNAME + "=?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM " + TABLE + " WHERE " + EMAIL + "=?";
    private static final String FIND_BY_USERNAME_AND_PASSWORD =
            "SELECT * FROM " + TABLE + " WHERE " + USERNAME + "=? AND " + PASSWORD + "=?";
    private static final String FIND_BY_EMAIL_AND_PASSWORD =
            "SELECT * FROM " + TABLE + " WHERE " + EMAIL + "=? AND " + PASSWORD + "=?";
    private static final String ADD = "INSERT INTO " + TABLE + " VALUES (?,?,?,?,?)";
    private static final String REMOVE_BY_UUID = "DELETE FROM " + TABLE + " WHERE " + DBHelper.USER.UUID + "=?";
    private static final String REMOVE_BY_EMAIL = "DELETE FROM " + TABLE + " WHERE " + EMAIL + "=?";
    private static final String REMOVE_BY_USERNAME = "DELETE FROM " + TABLE + " WHERE " + USERNAME + "=?";
    private static final String UPDATE = "UPDATE " + TABLE + " SET " + USERNAME + "=?, "
            + EMAIL + "=?, " + PASSWORD + "=?, " + ROLE + "=? WHERE " + UUID + "=?";
    private static final String GET_ALL = "SELECT * FROM " + TABLE;
    private static final String IS_DUPLICATE = "SELECT " + USERNAME + ", " + EMAIL + " FROM " + TABLE + " WHERE " +
            UUID + "=? OR " + USERNAME + "=? OR " + EMAIL + "=?";

    private static final BoneCP pool;

    static {
        BoneCP tmp = null;
        try {
            tmp = DBConnectionManager.getH2ConnectionPool();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pool = tmp;
    }

    private void closeAndCommit(Statement statement) throws SQLException {
        Connection connection = statement.getConnection();
        statement.close();
        connection.commit();
        connection.close();
    }

    @Override
    public User findByUsername(String username) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? createUser(resultSet) : null;
        } catch (SQLException e) {
            throw e;
        } finally {
            closeAndCommit(preparedStatement);
        }
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? createUser(resultSet) : null;
        } catch (SQLException e) {
            throw e;
        } finally {
            closeAndCommit(preparedStatement);
        }
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = pool.getConnection();
            preparedStatement =
                    connection.prepareStatement(FIND_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? createUser(resultSet) : null;
        } catch (SQLException e) {
            throw e;
        } finally {
            closeAndCommit(preparedStatement);
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = pool.getConnection();
            preparedStatement =
                    connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? createUser(resultSet) : null;
        } catch (SQLException e) {
            throw e;
        } finally {
            closeAndCommit(preparedStatement);
        }
    }

    @Override
    public User find(User user) throws SQLException {
        return findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    @Override
    public void add(User user) throws SQLException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ADD);
        preparedStatement.setObject(1, user.getUuid());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getRole().toString());
        preparedStatement.execute();
        closeAndCommit(preparedStatement);
    }

    @Override
    public void remove(User user) throws SQLException {
        removeByUUID(user.getUuid());
    }

    @Override
    public void removeByUUID(UUID userId) throws SQLException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement(REMOVE_BY_UUID);
        preparedStatement.setObject(1, userId);
        preparedStatement.execute();
        closeAndCommit(preparedStatement);
    }

    @Override
    public void removeByEmail(String email) throws SQLException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement(REMOVE_BY_EMAIL);
        preparedStatement.setObject(1, email);
        preparedStatement.execute();
        closeAndCommit(preparedStatement);
    }

    @Override
    public void removeByUsername(String username) throws SQLException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement(REMOVE_BY_USERNAME);
        preparedStatement.setObject(1, username);
        preparedStatement.execute();
        closeAndCommit(preparedStatement);
    }

    @Override
    public void update(User user) throws SQLException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getRole().toString());
        preparedStatement.setObject(5, user.getUuid());
        preparedStatement.execute();
        closeAndCommit(preparedStatement);
    }

    @Override
    public List<User> getUserList() throws SQLException {
        List<User> result = new ArrayList<>();
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result.add(createUser(resultSet));
        }
        closeAndCommit(preparedStatement);
        return result;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        UUID uuid = ((UUID) resultSet.getObject(DBHelper.USER.UUID));
        String username = resultSet.getString(USERNAME);
        String email = resultSet.getString(EMAIL);
        String password = resultSet.getString(PASSWORD);
        User.Role role = User.Role.valueOf(resultSet.getString(ROLE));
        return new User.Builder().uuid(uuid)
                .username(username)
                .email(email)
                .password(password)
                .role(role)
                .build();
    }

    @Override
    public List<String> isDuplicate(User user) throws SQLException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(IS_DUPLICATE);
        preparedStatement.setObject(1, user.getUuid());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getEmail());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> result = new ArrayList<>();

        while (resultSet.next()) {
            if (resultSet.getString(USERNAME).equals(user.getUsername())) result.add(user.getUsername());
            if (resultSet.getString(EMAIL).equals(user.getEmail())) result.add(user.getEmail());
        }
        closeAndCommit(preparedStatement);
        return result;
    }
}
