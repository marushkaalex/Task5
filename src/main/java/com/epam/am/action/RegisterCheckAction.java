package com.epam.am.action;

import com.epam.am.dao.H2UserDao;
import com.epam.am.dao.UserDao;
import com.epam.am.database.DBConnectionManager;
import com.epam.am.entity.User;
import com.jolbox.bonecp.BoneCP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

import static com.epam.am.database.DBHelper.USER.*;

public class RegisterCheckAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(RegisterCheckAction.class);
    private static final BoneCP pool;

    private ActionResult register = new ActionResult("register");
    private ActionResult home = new ActionResult("home", true);

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

        UserDao userDao = new H2UserDao();
        List<String> errorList = null;
        try {
            errorList = userDao.isDuplicate(new User.Builder().username(username).email(email).build());
            System.out.println("errorList: " + errorList);
            req.setAttribute("register_errorList", errorList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (errorList != null && errorList.size() == 0) {
            try {
                User user = new User.Builder()
                        .uuid(java.util.UUID.randomUUID())
                        .username(username)
                        .email(email)
                        .password(password) // TODO password hash
                        .role(User.Role.CLIENT)
                        .build();
                userDao.add(user);
                req.getSession().setAttribute("user", user);
                return home;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return register;
    }
}
