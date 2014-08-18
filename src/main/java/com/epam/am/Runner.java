package com.epam.am;

import com.epam.am.database.DBConnectionManager;
import com.jolbox.bonecp.BoneCP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//TODO servlet
//TODO database
//TODO actions
//TODO properties X
//TODO logs X
//TODO custom exception type
//TODO java-version
//TODO filter for charset X
//TODO DAO
//TODO sql scripts
//TODO registration

public class Runner {
    private static Logger log = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) throws SQLException {
        BoneCP pool = DBConnectionManager.getH2ConnectionPool();
        Connection connection = null;
        connection = pool.getConnection();
        System.out.println(connection);
        System.out.println(pool);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT username FROM user WHERE username=?");
        preparedStatement.setString(1, "1");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(resultSet.first());
    }
}
