package com.cloud.computing.project.common.connection;

import com.cloud.computing.project.common.config.DbConfig;
import com.cloud.computing.project.common.connection.Connection;
import com.cloud.computing.project.database.connection.oracledb.*;;


public class ConnectionFactory {
private static Connection connection = null;
	
	public static Connection getConnection() {
		if( connection != null ) return connection;
		
		switch( DbConfig.getDbType() ) {
			case ORACLE:
				connection = new OracleDBConnection();
			default:
				break;
		}

		// open connection
		connection.open();
		
		return connection;
	}
}
