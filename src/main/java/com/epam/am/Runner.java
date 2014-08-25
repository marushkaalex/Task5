package com.epam.am;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
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
        Logger log = LoggerFactory.getLogger(Runner.class);
        log.error("message");
    }

    private static void listPrint(List list) {
        list.forEach(System.out::println);
    }
}
