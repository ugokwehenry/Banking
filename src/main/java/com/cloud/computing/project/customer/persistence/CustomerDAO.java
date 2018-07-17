package com.cloud.computing.project.customer.persistence;

import java.util.List;
import com.cloud.computing.project.customer.data.CustomerData;
import com.cloud.computing.project.exception.UserNotFoundException;

public interface CustomerDAO {
public List<CustomerData> getAllCustomers();
public CustomerData getCustomer() throws UserNotFoundException;
public String getCustomerByAccount(String accountNumber) throws UserNotFoundException;
}
