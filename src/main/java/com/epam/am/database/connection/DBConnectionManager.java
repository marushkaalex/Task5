package com.epam.am.database.connection;

import com.epam.am.helper.PropertyManager;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.sql.SQLException;

public class DBConnectionManager {
    public static final String H2 = "h2.properties";
    private static final String JDBC_URL = "jdbc_url";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MIN_CONNECTIONS = "min_connections_per_partition";
    private static final String MAX_CONNECTIONS = "max_connections_per_partition";
    private static final String PARTITION_COUNT = "partition_count";

    public static BoneCPConfig getConfig(String propertiesFileName) {
        PropertyManager manager = PropertyManager.getManager(propertiesFileName);
        String jdbcUrl = manager.getProperty(JDBC_URL);
        String username = manager.getProperty(USERNAME);
        String password = manager.getProperty(PASSWORD);
        int minConnections = Integer.parseInt(manager.getProperty(MIN_CONNECTIONS));
        int maxConnections = Integer.parseInt(manager.getProperty(MAX_CONNECTIONS));
        int partitionCount = Integer.parseInt(manager.getProperty(PARTITION_COUNT));

        BoneCPConfig config = new BoneCPConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setMinConnectionsPerPartition(minConnections);
        config.setMaxConnectionsPerPartition(maxConnections);
        config.setPartitionCount(partitionCount);

        return config;
    }

    public static BoneCP getConnectionPool(BoneCPConfig config) throws SQLException {
        return new BoneCP(config);
    }
}
