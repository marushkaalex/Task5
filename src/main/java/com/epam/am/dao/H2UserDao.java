package com.epam.am.dao;

import com.epam.am.database.DBConnectionManager;
import com.epam.am.entity.User;
import com.jolbox.bonecp.BoneCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static com.epam.am.database.DBHelper.USER.TABLE;

public class H2UserDao implements UserDao {

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

    @Override
    public void add(User user) throws SQLException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + TABLE + " VALUES (?,?,?,?)");
        preparedStatement.setObject(1, user.getUuid());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.execute();
    }

    @Override
    public void remove(User user) {

    }

    @Override
    public void remove(UUID userId) {

    }

    @Override
    public void remove(String word, boolean isFirstParUsername) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User findByCredentials(String username, String password, boolean isFirstParUsername) {
        return null;
    }

    @Override
    public List<User> getUserList() {
        return null;
    }
}
