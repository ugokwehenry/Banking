package com.cloud.computing.project.user.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cloud.computing.project.exception.UserExistingException;
import com.cloud.computing.project.exception.UserNotFoundException;
import com.cloud.computing.project.user.data.User;
import com.cloud.computing.project.user.data.UserSecurity;
import com.cloud.computing.project.user.persistence.UserDAO;
import com.cloud.computing.project.user.persistence.UserDAOFactory;

public class SqlUserDAO implements UserDAO {
	final static Logger logger = Logger.getLogger( SqlUserDAO.class );
	
	private Connection connection = null;
	
	public SqlUserDAO( com.cloud.computing.project.common.connection.Connection connection ) {
		this.connection = (Connection) connection.get();
	}

	public boolean createUser( UserSecurity user ) throws UserExistingException {
		logger.debug( "createUser: " + user.getEmail() );
		
		PreparedStatement stmt = null;
		
	    try {
	    	
			// check if user already registered
			try {
				if( getUserIdByEmail( user.getEmail() ) != null ) {
					throw new UserExistingException( user.getEmail() );
				}
			}
			// continue if no user found
			catch( UserNotFoundException e) {}

	    	stmt = connection.prepareStatement( "INSERT INTO USERS(" + 
	    									    "SYSUSER_ID,EMAIL_ADDR,FIRST_NM,LAST_NM,PASSWORD,ROLE) VALUES" +
	    									    "(SYS_USER_SEQ.NEXTVAL,?,?,?,?,?)" );
	    	stmt.setString( 1, user.getEmail() );
	    	stmt.setString( 2, user.getFirstname() );
	    	stmt.setString( 3, user.getLastname() );
	    	stmt.setString( 4, user.getPassword() );
	    	stmt.setString( 5, user.getRole() );
		    stmt.executeUpdate();
		    
	    } catch ( SQLException e ) {
	    	logger.debug( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    finally {
	    	try {
				stmt.close();
			} catch ( SQLException e ) {
				logger.warn( e.getMessage() );
			}
	    }
	    
	    return true;
	}

	public String getUserIdByEmail(String email) throws UserNotFoundException {
		logger.debug( "getUserIdByEmail: " + email );
		
		String id = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
	    try {
	    	stmt = connection.prepareStatement( "SELECT SYSUSER_ID FROM USERS WHERE EMAIL_ADDR=? " );
	    	stmt.setString(1, email);
		    rs = stmt.executeQuery();
		    
		    if( rs.next() ) {
		    	id = String.valueOf( rs.getInt("SYSUSER_ID") );
		    }
		    else {
		    	throw new UserNotFoundException( email );
		    }
		    
	    } catch ( SQLException e ) {
	    	logger.debug( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    finally {
	    	try {
				rs.close();
				stmt.close();
			} catch ( SQLException e ) {
				logger.warn( e.getMessage() );
			}
	    }
	    
	    return id;
	}

	
	public User getUser(String id) throws UserNotFoundException {
		logger.debug( "getUser: " + id );
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
	
	    try {
	    	stmt = connection.prepareStatement( "SELECT SYSUSER_ID, EMAIL_ADDR, FIRST_NM, LAST_NM FROM USERS WHERE SYSUSER_ID=?" );
	    	stmt.setString(1, id);
		    rs = stmt.executeQuery();
		    
		    if( rs.next() ) {
		    	String userId = String.valueOf( rs.getInt("SYSUSER_ID") );
		    	String email = rs.getString("EMAIL_ADDR");
		    	String firstname = rs.getString("FIRST_NM");
		    	String lastname = rs.getString("LAST_NM");
		    			    	
		    	user = new User(userId, email, firstname, lastname );
		    	System.out.println(user.getFirstname());
		    }
		    else {
		    	throw new UserNotFoundException( id );
		    }
		    
	    } catch ( SQLException e ) {
	    	logger.debug( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    finally {
	    	try {
				rs.close();
				stmt.close();
			} catch ( SQLException e ) {
				logger.warn( e.getMessage() );
			}
	    }
	    
	    return user;
	}

	
	public List<User> getAllUsers() {
		logger.debug( "getAllUsers" );
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<User> user = new ArrayList<User>();

	    try {
	    	stmt = connection.prepareStatement( "SELECT SYSUSER_ID, EMAIL_ADDR, FIRST_NM, LAST_NM FROM USERS " );
		    rs = stmt.executeQuery();
		    
		    while( rs.next() ) {
		    	String userId = String.valueOf( rs.getInt("SYSUSER_ID") );
		    	String email = rs.getString("EMAIL_ADDR");
		    	String firstname = rs.getString("FIRST_NM");
		    	String lastname = rs.getString("LAST_NM");
		    	
		    	user.add( new User( userId, email, firstname, lastname ) );
		    	for(User users: user){
		    		System.out.println(users);
		    	}
		    }
		    
	    } catch ( SQLException e ) {
	    	logger.debug( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    finally {
	    	try {
				rs.close();
				stmt.close();
			} catch ( SQLException e ) {
				logger.warn( e.getMessage() );
			}
	    }
	    
	    return user;
	}

	
	public UserSecurity getUserAuthentication( String id ) throws UserNotFoundException {
		logger.debug( "getUserAuthentication: " + id );
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		UserSecurity userSecurity = null;
	
	    try {
	    	stmt = connection.prepareStatement( "SELECT EMAIL_ADDR, PASSWORD, TOKEN, ROLE FROM USERS WHERE SYSUSER_ID=? " );
	    	stmt.setString(1, id);
		    rs = stmt.executeQuery();
		    
		    if( rs.next() ) {
		    	String email = rs.getString("EMAIL_ADDR");
		    	String password = rs.getString("PASSWORD");
		    	String token = rs.getString("TOKEN");
		    	String role = rs.getString("ROLE");
		    	
		    	userSecurity = new UserSecurity( email, password, token, role );
		    }
		    else {
		    	throw new UserNotFoundException( id );
		    }
		    
	    } catch ( SQLException e ) {
	    	logger.debug( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    finally {
	    	try {
				rs.close();
				stmt.close();
			} catch ( SQLException e ) {
				logger.warn( e.getMessage() );
			}
	    }
	    
	    return userSecurity;
	}

	
	public boolean setUserAuthentication( UserSecurity user ) throws UserNotFoundException {
		logger.debug( "setUserAuthentication: " + user.getId() );
		
		PreparedStatement stmt = null;
		
	    try {
	    	// prepare query
	    	StringBuffer query = new StringBuffer();
	    	query.append( "UPDATE USERS SET " );
	    	
	    	boolean comma = false;
	    	List<String> prepare = new ArrayList<String>();
	    	if( user.getPassword() != null ) {
	    		query.append( "password=?" );
	    		comma = true;
	    		prepare.add( user.getPassword() );
	    	}
	    	
	    	if( user.getToken() != null ) {
	    		if( comma ) query.append(",");
	    		query.append( "token=?" );
	    		comma = true;
	    		prepare.add( user.getToken() );
	    	}
	    	
	    	if( user.getRole() != null ) {
	    		if( comma ) query.append(",");
	    		query.append( "role=?" );
	    		prepare.add( user.getRole() );
	    	}
	    	
	    	query.append(" WHERE SYSUSER_ID=?");
	    	stmt = connection.prepareStatement( query.toString() );
	    	
	    	for( int i = 0; i < prepare.size(); i++ ) {
	    		stmt.setString( i+1, prepare.get(i) );
	    	}
	    	
	    	stmt.setInt( prepare.size() + 1, Integer.parseInt( user.getId() ) );
	    	
	    	stmt.executeUpdate();
		    
	    } catch ( SQLException e ) {
	    	logger.debug( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    finally {
	    	try {
				stmt.close();
			} catch ( SQLException e ) {
				logger.warn( e.getMessage() );
			}
	    }
	    
	    return true;
	}

	
	public boolean updateUser( User user ) throws UserNotFoundException {
		logger.debug( "updateUser: " + user.getId() );
		
		PreparedStatement stmt = null;
		
	    try {
	    	// prepare query
	    	StringBuffer query = new StringBuffer();
	    	query.append( "UPDATE USER SET " );
	    	
	    	boolean comma = false;
	    	List<String> prepare = new ArrayList<String>();
	    	if( user.getFirstname() != null ) {
	    		query.append( "firstname=?" );
	    		comma = true;
	    		prepare.add( user.getFirstname() );
	    	}
	    	
	    	if( user.getLastname() != null ) {
	    		if( comma ) query.append(",");
	    		query.append( "lastname=?" );
	    		comma = true;
	    		prepare.add( user.getLastname() );
	    	}
	    	
	    	if( user.getEmail() != null ) {
	    		if( comma ) query.append(",");
	    		query.append( "email=?" );
	    		prepare.add( user.getEmail() );
	    	}
	    	
	    	query.append(" WHERE SYSUSER_ID=?");
	    	stmt = connection.prepareStatement( query.toString() );
	    	
	    	for( int i = 0; i < prepare.size(); i++ ) {
	    		stmt.setString( i+1, prepare.get(i) );
	    	}
	    	
	    	stmt.setInt( prepare.size() + 1, Integer.parseInt( user.getId() ) );
	    	
	    	stmt.executeUpdate();
		    
	    } catch ( SQLException e ) {
	    	logger.debug( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    finally {
	    	try {
				stmt.close();
			} catch ( SQLException e ) {
				logger.warn( e.getMessage() );
			}
	    }
		
		return true;
	}

	
	public boolean deleteUser( String id ) throws UserNotFoundException {
		logger.debug( "deleteUser: " + id );
		
		PreparedStatement stmt = null;
		
	    try {
	    	
	    	stmt = connection.prepareStatement( "DELETE FROM USERS WHERE SYSUSER_ID=?" );
	    	stmt.setString( 1, id );
	    	
		    stmt.executeUpdate();
		    
	    } catch ( SQLException e ) {
	    	logger.debug( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    finally {
	    	try {
				stmt.close();
			} catch ( SQLException e ) {
				logger.warn( e.getMessage() );
			}
	    }
	    
	    return true;
	}
	
	public static void main(String[] args) throws SQLException {
		UserDAO userDAO = UserDAOFactory.getUserDAO();
		userDAO.getAllUsers();
		userDAO.getUser("115830");
	}
}
