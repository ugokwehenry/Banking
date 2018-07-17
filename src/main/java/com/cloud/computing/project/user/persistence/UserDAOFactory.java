package com.cloud.computing.project.user.persistence;

import com.cloud.computing.project.common.config.DbConfig;
import com.cloud.computing.project.common.connection.Connection;
import com.cloud.computing.project.common.connection.ConnectionFactory;
import com.cloud.computing.project.user.persistence.SqlUserDAO;

public class UserDAOFactory {
	
	public static UserDAO getUserDAO() {
		// get connection
		Connection connection = ConnectionFactory.getConnection();
		
		// use driver specified according to database
		switch( DbConfig.getDbType() ) {
			case ORACLE:
				return new SqlUserDAO( connection );
			default:
				// should not happen: we test for correct input in DbConfig.java
				return null;
		}
	}
}



