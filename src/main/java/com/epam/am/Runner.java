package com.epam.am;

import com.epam.am.database.connection.DBConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public static void main(String[] args) {
        DBConnectionManager.setConfig(DBConnectionManager.getConfig("h2.properties"));
        DBConnectionManager.getConnectionPool();
    }
}
