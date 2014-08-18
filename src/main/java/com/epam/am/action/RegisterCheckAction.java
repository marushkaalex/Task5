package com.epam.am.action;

import com.epam.am.dao.H2UserDao;
import com.epam.am.dao.UserDao;
import com.epam.am.database.DBConnectionManager;
import com.epam.am.entity.User;
import com.jolbox.bonecp.BoneCP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.epam.am.database.DBHelper.USER.*;

public class RegisterCheckAction implements Action {

    //TODO something

    private static final Logger log = LoggerFactory.getLogger(RegisterCheckAction.class);
    private static final BoneCP pool;

    private ActionResult register = new ActionResult("register", true);
    private ActionResult home = new ActionResult("home");

    static {
        BoneCP tmp = null;
        try {
            tmp = DBConnectionManager.getH2ConnectionPool();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pool = tmp;
    }

    public RegisterCheckAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest req) {
        String username = req.getParameter(USERNAME);
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        System.out.println(username);
        System.out.println(email);
        System.out.println(password);

        Map<String, Boolean> errorMap = new HashMap<>();
        errorMap.put(USERNAME, isDuplicate(USERNAME, username));
        errorMap.put(EMAIL, isDuplicate(EMAIL, email));

        List<String> errorList = createErrorList(errorMap);
        System.out.println("errorList size: " + errorList.size());

        if (errorList.size() == 0) {
            User user = new User.Builder()
                    .uuid(UUID.fromString(username))
                    .username(username)
                    .email(email)
                    .password(password) // TODO password hash
                    .role(User.Role.CLIENT)
                    .build();
            UserDao userDao = new H2UserDao();
            try {
                userDao.add(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return register;
    }

    private List<String> createErrorList(Map<String, Boolean> errorMap) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Boolean> stringBooleanEntry : errorMap.entrySet()) {
            if (stringBooleanEntry.getValue()) {
                result.add(stringBooleanEntry.getKey());
            }
        }
        return result;
    }

    private boolean isDuplicate(String column, String value) {
        ResultSet resultSet = null;
        switch (column) {
            case USERNAME:
                resultSet = select(USERNAME, TABLE, USERNAME, value);
                break;
            case EMAIL:
                resultSet = select(EMAIL, TABLE, EMAIL, value);
                break;
        }
        if (resultSet == null) throw new RuntimeException("resultSet == null");
        try {
            if (!resultSet.first())
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private ResultSet select(String column, String table, String where, String isWhat) {
        ResultSet resultSet = null;
        try {
            Connection connection = pool.getConnection();
            if (connection == null) throw new RuntimeException("no connection");
            PreparedStatement statement = connection.prepareStatement("SELECT " + column + " FROM " + table + " WHERE ?=?");
            statement.setString(1, where);
            statement.setString(2, isWhat);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
