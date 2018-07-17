package com.cloud.computing.project.loan.account.persistence;

import java.util.List;
import com.cloud.computing.project.exception.UserNotFoundException;
import com.cloud.computing.project.loan.account.data.LoanAccountData;

public interface LoanAccountDAO {
	public List<LoanAccountData> getAllLoanAccount();
	public LoanAccountData getLoanAccount() throws UserNotFoundException;
	public String getLoanAccountByAccount(String accountNumber) throws UserNotFoundException;

}
