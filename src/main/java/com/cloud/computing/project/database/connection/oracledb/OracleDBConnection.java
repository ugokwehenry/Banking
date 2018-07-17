package com.cloud.computing.project.database.connection.oracledb;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.cloud.computing.project.security.PasswordSecurity;
import org.apache.log4j.Logger;
//import org.sqlite.SQLiteConfig;
//import org.sqlite.SQLiteOpenMode;



public class OracleDBConnection implements com.cloud.computing.project.common.connection.Connection{
	final static Logger logger = Logger.getLogger( OracleDBConnection.class );
	
	Connection connection = null;
	
	public Object get() {
		return connection;
	}

	public boolean open() {
		connection = null;
	    try {
	    	
	    	try {
	    		  System.out.println("-------- Oracle JDBC Connection Testing ------");
	            Class.forName("oracle.jdbc.driver.OracleDriver");

	        } catch (ClassNotFoundException e) {

	            System.out.println("Where is your Oracle JDBC Driver?");
	            e.printStackTrace();
	            return false;

	        }
	    	System.out.println("Oracle JDBC Driver Registered!");	 
	    	 connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.34:1521:rubikon", "rubikon", "neptune");
	      
	    } catch ( Exception e ) {
	      logger.debug( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    logger.debug("Opened database successfully");
	    // database openend - init database schema
	    createUserSchema();
	    // create admin user
	    createAdminUser();
	    return true;
	}

	public boolean close() {
		if( connection != null ) {
			try {
				connection.close();
			} catch ( SQLException e ) {
				logger.debug( "Databank not closed correctly: " + e.getMessage() );
			}
			return true;
		}
		return false;
	}
	private boolean checkForUserSchema() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT TABLE_NAME FROM USER_TABLES WHERE TABLE_NAME='USERS' " );
			if (rs.next()) {
				return true;
			}
			rs.close();
			stmt.close();
		} catch ( Exception e ) {
			logger.debug( e.getClass().getName() + ": " + e.getMessage() );
		}
		return false;
	}
	
	
	
	private void createUserSchema() {
		if( checkForUserSchema() == true ) return;
		
		try {
			Statement stmt = connection.createStatement();
			String sql = "CREATE TABLE USERS( " +
					     " SYSUSER_ID NUMBER NOT NULL ENABLE," +
					     " EMAIL_ADDR 		   VARCHAR2(35 BYTE) NOT NULL ENABLE, " +
					     " FIRST_NM      VARCHAR2(35 BYTE) NOT NULL ENABLE, " + 
					     " LAST_NM       VARCHAR2(35 BYTE) NOT NULL ENABLE, " +
					     " PASSWORD		  VARCHAR2(200 BYTE) NOT NULL ENABLE, " + 
					     " TOKEN          VARCHAR2(100 BYTE), " + 
					     " ROLE           VARCHAR2(20 BYTE))"; 
			
			System.out.println(sql);
			
			stmt.executeUpdate( sql );
			stmt.close();
		} catch ( Exception e ) {
			logger.debug( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
	
	private boolean checkForAdminUser() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT EMAIL_ADDR FROM USERS  WHERE EMAIL_ADDR='contact@tutorial-academy.com' " );
			if (rs.next()) {
				return true;
			}
			rs.close();
			stmt.close();
		} catch ( Exception e ) {
			logger.debug( e.getClass().getName() + ": " + e.getMessage() );
		}
		return false;
	}
	
	private void createAdminUser() {
		if( checkForAdminUser() ) return;
		
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement(
	    	 "INSERT INTO USERS(" + 
			 "SYSUSER_ID,EMAIL_ADDR,FIRST_NM,LAST_NM,PASSWORD,ROLE) VALUES" +
			 "(SYS_USER_SEQ.NEXTVAL,?,?,?,?,?)" );
			
			//stmt.setString( 1, "SYS_USER_SEQ.NEXTVAL");
			stmt.setString( 1, "contact@tutorial-academy.com" );
			stmt.setString( 2, "tutorial" );
			stmt.setString( 3, "academy" );
			stmt.setString( 4, PasswordSecurity.generateHash( "secret" ) );
			stmt.setString( 5, "admin" );
			stmt.executeUpdate();
	    } catch ( SQLException e ) {
	    	logger.debug( e.getClass().getName() + ": " + e.getMessage() );
	    } catch (NoSuchAlgorithmException e) {
	    	logger.debug( e.getClass().getName() + ": " + e.getMessage() );
		} catch (InvalidKeySpecException e) {
			logger.debug( e.getClass().getName() + ": " + e.getMessage() );
		}
	    finally {
	    	try {
				stmt.close();
			} catch ( SQLException e ) {
				logger.warn( e.getMessage() );
			}
	    }
	}
}
