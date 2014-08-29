package com.epam.am.action;

import com.epam.am.dao.*;
import com.epam.am.entity.User;
import com.epam.am.util.FieldInvalidater;
import com.epam.am.util.HashCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.epam.am.database.DBHelper.UserTable.*;

public class RegisterCheckAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(RegisterCheckAction.class);

    //W for Wrong
    private static final String W_USERNAME = "register.wrong.username";
    private static final String W_EMAIL = "register.wrong.email";
    private static final String W_PASSWORD = "register.wrong.password";
    private static final String W_DOB = "register.wrong.dob";
    private static final String W_ROLE = "register.wrong.role";
    String username;
    String email;
    String password;
    String role;
    String date;
    private ActionResult register = new ActionResult("register");
    private ActionResult home = new ActionResult("home", true);

    public RegisterCheckAction() {
    }

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {

        if (!validateFields(req)) {
            return register;
        }

        DaoFactory daoFactory = new H2DaoFactory();
        UserDao userDao = null;
        List<String> errorList = null;
        try {
            DaoManager daoManager = daoFactory.getDaoManager();
            userDao = daoManager.getUserDao();
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
                        .password(HashCalculator.hash(password))
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

    private boolean validateFields(HttpServletRequest req) {

        boolean isValid = true;

        username = req.getParameter(USERNAME);
        email = req.getParameter(EMAIL);
        password = req.getParameter(PASSWORD);
        role = req.getParameter(ROLE);
        date = req.getParameter(DATE_OF_BIRTH);

        if (!FieldInvalidater.username(username)) {
            req.setAttribute("w_username", W_USERNAME);
            isValid = false;
        }
        if (!FieldInvalidater.email(email)) {
            req.setAttribute("w_email", W_EMAIL);
            isValid = false;
        }
        if (!FieldInvalidater.password(password)) {
            req.setAttribute("w_password", W_PASSWORD);
            isValid = false;
        }
        if (!FieldInvalidater.role(role)) {
            req.setAttribute("w_role", W_ROLE);
            isValid = false;
        }
        if (!FieldInvalidater.date(date)) {
            req.setAttribute("w_dob", W_DOB);
            isValid = false;
        }
        return isValid;
    }
}
