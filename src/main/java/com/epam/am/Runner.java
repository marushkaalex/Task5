package com.epam.am;

import com.epam.am.database.connection.DBConnectionManager;

//TODO servlet
//TODO database
//TODO actions
//TODO properties
//TODO logs X
//TODO custom exception type
//TODO java-version
//TODO filter for charset
//TODO DAO
//TODO sql scripts

//DONE
public class Runner {
    public static void main(String[] args) {
        DBConnectionManager.setConfig(DBConnectionManager.getConfig("h2.properties"));
        DBConnectionManager.getConnectionPool();
    }
}
