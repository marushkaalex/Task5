package com.epam.am.database.connection;

import com.epam.am.helper.PropertyManager;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class DBConnectionManager {
    public static final String H2 = "h2.properties";
    private static final Logger log = LoggerFactory.getLogger(DBConnectionManager.class);
    private static final String JDBC_URL = "jdbc_url";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MAX_CONNECTIONS = "max_connections_per_partition";
    private static BoneCPConfig config;
    private static BoneCP cp;

    public static BoneCPConfig getConfig(String propertiesFileName) {
        PropertyManager manager = PropertyManager.getManager(propertiesFileName);
        String jdbcUrl = manager.getProperty(JDBC_URL);
        String username = manager.getProperty(USERNAME);
        String password = manager.getProperty(PASSWORD);
        int maxConn = Integer.parseInt(manager.getProperty(MAX_CONNECTIONS));

        BoneCPConfig config = new BoneCPConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaxConnectionsPerPartition(maxConn);

        return config;
    }

    public static BoneCP getConnectionPool() {
        if (cp == null) {
            if (config == null) {
                log.error("BoneCPConfig == null");
                throw new IllegalArgumentException("You should configure connection pool");
            }
            try {
                cp = new BoneCP(config);
            } catch (SQLException e) {
                log.error("Exception during BoneCP initialization", e);
                e.printStackTrace();
            }
        }
        return cp;
    }

    public static void setConfig(BoneCPConfig config) {
        DBConnectionManager.config = config;
    }
}
