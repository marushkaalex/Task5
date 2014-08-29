package com.epam.am.util;

import com.epam.am.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldInvalidater {

    private static final int PASSWORD_MIN_SIZE = 8;
    private static final int PASSWORD_MAX_SIZE = 32;
    private static final String USERNAME_REGEX = "^[A-z]{5,20}$";
    private static final String DATE_REGEX = "^([12][0-9]{3})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
    private static final String NO_SPEC_SYMBOLS = "[^`~!@#$%^&*()=+/\\\\]*";
    private static final String EMAIL_REGEX = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@" +
            "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+(?:[A-Z]{2}|com|org|net|edu|gov|mil|" +
            "biz|info|mobi|name|aero|asia|jobs|museum)\\b";

    private FieldInvalidater() {
    }

    public static boolean username(String username) {
        Pattern pattern = Pattern.compile(USERNAME_REGEX);
        return pattern.matcher(username).matches();
    }

    public static boolean password(String password) {
        return noSpaces(password) && password.length() >= PASSWORD_MIN_SIZE && password.length() <= PASSWORD_MAX_SIZE;
    }

    public static boolean date(String date) {
        Pattern pattern = Pattern.compile(DATE_REGEX);
        return pattern.matcher(date).matches();
    }

    public static boolean email(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean role(String role) {
        for (User.Role r : User.Role.values()) {
            if (r.toString().equals(role)) return true;
        }
        return false;
    }

    public static boolean noSpaces(String str) {
        return !str.contains(" ");
    }

    public static boolean noSpecSymbols(String str) {
        Pattern pattern = Pattern.compile(NO_SPEC_SYMBOLS);
        return pattern.matcher(str).matches();
    }
}
