package com.epam.am.action;

import com.epam.am.dao.DaoException;
import com.epam.am.dao.DaoFactory;
import com.epam.am.dao.H2DaoFactory;
import com.epam.am.dao.UserDao;
import com.epam.am.entity.User;
import com.epam.am.util.HashCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.epam.am.database.DBHelper.USER.*;

public class RegisterCheckAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(RegisterCheckAction.class);
    private static final int MIN_LETTERS = 3;
    private ActionResult register = new ActionResult("register");
    private ActionResult home = new ActionResult("home", true);

    public RegisterCheckAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        String username = req.getParameter(USERNAME);
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        String role = req.getParameter(ROLE);
        String date = req.getParameter(DATE_OF_BIRTH);

        DaoFactory daoFactory = new H2DaoFactory();
        UserDao userDao = null;
        List<String> errorList = null;
        try {
            userDao = daoFactory.getUserDao();
            errorList = userDao.isDuplicate(new User.Builder().username(username).email(email).build());
            System.out.println("errorList: " + errorList);
            req.setAttribute("register_errorList", errorList);
        } catch (DaoException e) {
            throw new ActionException("Exception while registration", e);
        }

        if (errorList != null && errorList.size() == 0) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                User user = new User.Builder()
                        .username(username)
                        .email(email)
                        .password(HashCalculator.hash(password)) // TODO password hash
                        .role(User.Role.valueOf(role))
                        .dateOfBirth(format.parse(date))
                        .build();
                userDao.add(user);
                req.getSession().setAttribute("user", user);
                return home;
            } catch (ParseException e) {
                throw new ActionException("Exception while registration", e);
            } catch (DaoException e) {
                throw new ActionException("Exception while registration", e);
            }
        }

        return register;
    }
}
