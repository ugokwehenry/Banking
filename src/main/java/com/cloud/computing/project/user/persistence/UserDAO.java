package com.cloud.computing.project.user.persistence;

import java.util.List;

import com.cloud.computing.project.exception.UserExistingException;
import com.cloud.computing.project.exception.UserNotFoundException;
import com.cloud.computing.project.user.data.User;
import com.cloud.computing.project.user.data.UserSecurity;

public interface UserDAO {
	public boolean createUser( UserSecurity user ) throws UserExistingException;
	
	public String getUserIdByEmail( String email ) throws UserNotFoundException;
	public User getUser( String id ) throws UserNotFoundException;
	
	public List<User> getAllUsers();
	
	public UserSecurity getUserAuthentication( String id ) throws UserNotFoundException;
	public boolean setUserAuthentication( UserSecurity user ) throws UserNotFoundException;
	
	public boolean updateUser( User user ) throws UserNotFoundException;
	public boolean deleteUser( String id ) throws UserNotFoundException;
}
