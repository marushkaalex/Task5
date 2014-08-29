package com.epam.am;

import com.epam.am.dao.DaoException;
import com.epam.am.util.FieldInvalidater;
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

    public static void main(String[] args) throws SQLException, DaoException {
        String user = "$pec";
        System.out.println(FieldInvalidater.noSpecSymbols(user));

        //TODO show gallery page
    }

    private static void listPrint(List list) {
        list.forEach(System.out::println);
    }
}
