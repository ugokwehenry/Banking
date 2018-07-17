package com.cloud.computing.project.deposit.account.persistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cloud.computing.project.deposit.account.data.DepositAccountData;
import com.cloud.computing.project.exception.UserNotFoundException;

public class SqlDepositAccountDAO implements DepositAccountDAO{
	final static Logger logger = Logger.getLogger(SqlDepositAccountDAO.class);
	
	private Connection connection = null;
	
	public SqlDepositAccountDAO(com.cloud.computing.project.common.connection.Connection connection){
		this.connection = (Connection) connection.get();
	}

	@Override
	public List<DepositAccountData> getAllDepositAccounts() {
		logger.debug("getAllDepositAccounts");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<DepositAccountData> depositAccountList = new ArrayList<DepositAccountData>();
		
		try{
			stmt = connection.prepareStatement("SELECT DEPOSIT_ACCT_ID, ACCT_NM, ACCT_NO, OPENED_DT  FROM DEPOSIT_ACCOUNT WHERE ROWNUM <= 50");
			rs = stmt.executeQuery();
			
			while(rs.next()){
				String accountId = String.valueOf(rs.getInt("DEPOSIT_ACCT_ID"));
				String accountName = rs.getString("ACCT_NM");
				String accountNumber = rs.getString("ACCT_NO");
				String openedDate = String.valueOf(rs.getDate("OPENED_DT"));
				
				depositAccountList.add(new DepositAccountData(accountId, accountName, accountNumber, openedDate));
				for(DepositAccountData dpAccount: depositAccountList){
					System.out.println(dpAccount);
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
		return depositAccountList;
	}

	@Override
	public DepositAccountData getDepositAccount() throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDepositAccountByAccount(String accountNumber)
			throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String args[]){
		DepositAccountDAO dpAccountDao = DepositAccountDAOFactory.getDepositAccountDAO();
		dpAccountDao.getAllDepositAccounts();
	}
}
