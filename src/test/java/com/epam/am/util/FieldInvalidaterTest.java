package com.epam.am.util;

import org.junit.Test;

import static com.epam.am.util.FieldInvalidater.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FieldInvalidaterTest {

    @Test
    public void testUsername() throws Exception {
        String tooShort = "user";
        String tooLong = "aVeryLongLongUsernameEver";
        String withSpaces = "some space";
        String withSpec = "$uper^$pec";
        String wellDone = "OrdinaryUser";

        assertFalse(username(tooShort));
        assertFalse(username(tooLong));
        assertFalse(username(withSpaces));
        assertFalse(username(withSpec));
        assertTrue(username(wellDone));
    }

    @Test
    public void testPassword() throws Exception {
        String tooShort = "pass";
        String tooLong = "THISisAveryLARGEpasswordIdontKNOWwhatTOtype";
        String wellDone = "V3rySafetYPassw0Rd";

        assertFalse(password(tooShort));
        assertFalse(password(tooLong));
        assertTrue(password(wellDone));
    }

    @Test
    public void testDate() throws Exception {
        String[] noDate = {
                "Date",
                "3000-10-10",
                "2014-13-13",
                "0999-01-01",
                "2o14-05-12",
                "1555-1-10",
                "2222-01-1",
                "today",
                "28 FEB 2000",
                "2014-10-32"};

        String[] date = {
                "1000-10-10",
                "1999-01-31",
                "2015-12-01"};

        for (String s : noDate) {
            assertFalse(date(s));
        }

        for (String s : date) {
            assertTrue(date(s));
        }
    }

    @Test
    public void testEmail() throws Exception {
        String[] wrong = {
                "@mail.com",
                "email",
                "email.com",
                "email@.com",
                "email@mail.ololo",
                "e^m$ail@.mail.mobi"
        };

        String[] wellDone = {
                "123@mail.com",
                "email.mail@mail.com",
                "junit@mail.ru"
        };

        for (String s : wrong) {
            assertFalse(email(s));
        }

        for (String s : wellDone) {
            assertTrue(email(s));
        }
    }

    @Test
    public void testRole() throws Exception {
        String[] wrong = {
                "admin",
                "client",
                "artist",
                "MASTER"};

        String[] wellDone = {
                "ADMIN",
                "CLIENT",
                "ARTIST"};

        for (String s : wrong) {
            assertFalse(role(s));
        }

        for (String s : wellDone) {
            assertTrue(role(s));
        }
    }
}
