package com.epam.am;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

    //TODO look in the skype \o/
    public static void main(String[] args) throws SQLException {
//        UserDao userDao = new H2UserDao();
//        User user = new User.Builder().username("username1")
//                .uuid(ID.randomUUID())
//                .email("email@email.com")
//                .password("password")
//                .role(User.Role.CLIENT)
//                .build();
////        userDao.add(user);
////        User user1 = userDao.findByUsernameAndPassword("username", "password");
////        System.out.println(user1);
//        User user1 = userDao.findByUsername("user");
//        System.out.println(user1);
        BoneCPConfig config = new BoneCPConfig();
        config.setJdbcUrl("jdbc:h2:tcp://localhost/~/task5/database");
        config.setUsername("sa");
        config.setPassword("sa");
        config.setMaxConnectionsPerPartition(20);
        System.out.println("--- creating cp");
        BoneCP cp = new BoneCP(config);
        Connection connection = cp.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("");
        System.out.println(statement.isClosed());
        System.out.println(connection.isClosed());
        System.out.println("--- closing");
        connection.close();
        System.out.println(statement.isClosed());
        System.out.println(connection.isClosed());
    }

    private static void listPrint(List list) {
        list.forEach(System.out::println);
    }
}
