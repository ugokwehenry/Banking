package com.cloud.computing.project.customer.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cloud.computing.project.customer.data.CustomerData;
import com.cloud.computing.project.exception.UserNotFoundException;
import org.apache.log4j.Logger;

public class SqlCustomerDAO implements CustomerDAO {
	final static Logger logger = Logger.getLogger(SqlCustomerDAO.class);

	private Connection connection = null;

	public SqlCustomerDAO(com.cloud.computing.project.common.connection.Connection connection) {
		this.connection = (Connection) connection.get();
	}
	
	@Override
	public List<CustomerData> getAllCustomers() {
		logger.debug("getAllCustomers");
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CustomerData> customer = new ArrayList<CustomerData>();
		try{
			stmt = connection.prepareStatement( "SELECT CUST_ID, CUST_NO, CUST_NM, REC_ST, CUST_SHORT_NM FROM CUSTOMER where rownum <= 100 " );
		    rs = stmt.executeQuery();
		    
		    while( rs.next() ) {
		    	String customerId = String.valueOf( rs.getInt("CUST_ID") );
		    	String customerNumber = rs.getString("CUST_NO");
		    	String customerName = rs.getString("CUST_NM");
		    	String status = rs.getString("REC_ST");
		    	String customerShortName = rs.getString("CUST_SHORT_NM");
		    	
		    	customer.add( new CustomerData( customerId, customerName, customerNumber, status, customerShortName ) );
		    	for(CustomerData customerData: customer){
		    		System.out.println(customerData);
		    	}
		    }
		}catch(SQLException e){
			logger.debug(e.getClass().getName() +": "+e.getMessage());
		}finally{
			try{
				rs.close();
				stmt.close();
			}catch(SQLException e){
				logger.warn(e.getMessage());
			}
		}
		return customer;
	}

	@Override
	public CustomerData getCustomer() throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCustomerByAccount(String accountNumber)
			throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
		public static void main(String args[]){
			CustomerDAO customerDao = CustomerDAOFactory.getCustomerDAO();
			customerDao.getAllCustomers();
		}
}
