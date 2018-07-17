package com.cloud.computing.project.loan.account.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.cloud.computing.project.deposit.account.persistence.DepositAccountDAOFactory;
import com.cloud.computing.project.exception.UserNotFoundException;
import com.cloud.computing.project.loan.account.persistence.LoanAccountDAO;

public class SqlLoanAccountDAO implements LoanAccountDAO{
	final static Logger logger = Logger.getLogger(SqlLoanAccountDAO.class);
	
	private Connection connection = null;
	
	public SqlLoanAccountDAO(com.cloud.computing.project.common.connection.Connection connection){
		this.connection = (Connection) connection.get();
	}

	@Override
	public List<LoanAccountData> getAllLoanAccount() {
		logger.debug("getAllDepositAccounts");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<LoanAccountData> loanAccountList = new ArrayList<LoanAccountData>();
		
		try{
			stmt = connection.prepareStatement("SELECT ACCT_ID, ACCT_NM, ACCT_NO, DISBURSEMENT_LIMIT, START_DT, MATURITY_DT, TERM_CD, TERM_VALUE, CAPITALIZED_EVENT_DUE_DT_OPTN, FINAL_REPAYMENT_DT, CLOSED_DT,PRIMARY_OFFICER_ID FROM LOAN_ACCOUNT ");
			rs = stmt.executeQuery();
			
			while(rs.next()){
				String accountId = String.valueOf(rs.getInt("ACCT_ID"));
				String accountName = rs.getString("ACCT_NM");
				String accountNumber = rs.getString("ACCT_NO");
				String loanAmount = rs.getString("DISBURSEMENT_LIMIT");
				String startDate = String.valueOf(rs.getDate("START_DT"));
				String marturityDate = String.valueOf(rs.getDate("MATURITY_DT"));
				String termCode = rs.getString("TERM_CD");
				String termValue = rs.getString("TERM_VALUE");
				String captilizedEvent = rs.getString("CAPITALIZED_EVENT_DUE_DT_OPTN");
				String finalRepaymentDate = String.valueOf(rs.getDate("FINAL_REPAYMENT_DT"));
				String closedDate = String.valueOf(rs.getDate("CLOSED_DT"));
				String accountOfficer = String.valueOf(rs.getInt("PRIMARY_OFFICER_ID"));
				
				loanAccountList.add(new LoanAccountData(accountId, accountName, accountNumber, loanAmount, startDate, marturityDate, termCode, termValue, captilizedEvent, finalRepaymentDate, closedDate, accountOfficer));
				for(LoanAccountData lnAccount: loanAccountList){
					System.out.println(lnAccount);
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
		return loanAccountList;
	}

	@Override
	public LoanAccountData getLoanAccount() throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLoanAccountByAccount(String accountNumber)
			throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String args[]){
		LoanAccountDAO lnAccountDao = LoanAccountDAOFactory.getLoanAccountDAO();
		lnAccountDao.getAllLoanAccount();
	}
}
