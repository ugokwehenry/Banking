package com.cloud.computing.project.deposit.account.persistence;

import com.cloud.computing.project.common.config.DbConfig;
import com.cloud.computing.project.common.connection.Connection;
import com.cloud.computing.project.common.connection.ConnectionFactory;

public class DepositAccountDAOFactory {
	public static DepositAccountDAO getDepositAccountDAO(){
		//get Connection
		Connection connection = ConnectionFactory.getConnection();
		
		//use the driver specified according to database
		switch(DbConfig.getDbType()){
		case ORACLE:
			return new SqlDepositAccountDAO(connection);
			default:
			//should not happen: we test for correct input in the DbConfig.java
			return null;
		}
	}
}
