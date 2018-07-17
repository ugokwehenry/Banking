package com.cloud.computing.project.deposit.account.persistence;

import java.util.List;
import com.cloud.computing.project.deposit.account.data.DepositAccountData;
import com.cloud.computing.project.exception.UserNotFoundException;

public interface DepositAccountDAO {
	public List<DepositAccountData> getAllDepositAccounts();
	public DepositAccountData getDepositAccount() throws UserNotFoundException;
	public String getDepositAccountByAccount(String accountNumber) throws UserNotFoundException;
}
