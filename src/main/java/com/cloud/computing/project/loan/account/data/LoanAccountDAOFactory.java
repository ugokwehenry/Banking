package com.cloud.computing.project.loan.account.data;

import com.cloud.computing.project.common.config.DbConfig;
import com.cloud.computing.project.common.connection.Connection;
import com.cloud.computing.project.common.connection.ConnectionFactory;
import com.cloud.computing.project.loan.account.persistence.LoanAccountDAO;

public class LoanAccountDAOFactory {
	public static LoanAccountDAO getLoanAccountDAO(){
		//get Connection
		Connection connection = ConnectionFactory.getConnection();
		
		//use the driver specified according to database
		switch(DbConfig.getDbType()){
		case ORACLE:
			return new SqlLoanAccountDAO(connection);
			default:
			//should not happen: we test for correct input in the DbConfig.java
			return null;
		}
	}
}
