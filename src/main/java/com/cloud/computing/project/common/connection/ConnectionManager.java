package com.cloud.computing.project.common.connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//import java.util.logging.Logger;
import org.apache.log4j.Logger;

import com.cloud.computing.project.common.config.DbConfig;


public class ConnectionManager implements Connection {
	private static final Logger logger = Logger
			.getLogger(ConnectionManager.class.getName());

	private int maxSize = 5;

	/**
	 * the pool of connections available for the application this is to prevent
	 * DAO getting there own resources this would unify all database management.
	 */
	private Map connPool = new HashMap();
	/**
	 * the pool of connections in use by an entity, module or session.
	 */
	private Map usePool = new HashMap();
	/**
	 * the connection manager instance
	 */

	private static ConnectionManager instance = null;

	private ConnectionManager() {
		init();
	}

	private void init() {
		logger.info("Getting DB connection.....");
		System.out.println("Initializing ..................");
		try {
			// Class.forName("com.sybase.jdbc3.jdbc.SybDriver");
			for (int i = 0; i < getMaxSize(); i++) {
				/**
				 * Connection conn = DriverManager.getConnection(
				 * "jdbc:sybase:Tds:10.152.2.151:5000/banking", "sa", "csa123");
				 * static method that reads properties from the
				 * orbitwebservice.xml config file
				 */
				// Connection conn = new
				// WebserviceConnection().makeConnection();

				Connection conn = null;
				getConnPool().put(conn, conn);

			}

			System.out.println("Initialized ..................");

		} catch (Exception e)// problem loading driver, class not exist?
		{
			logger.debug(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public Map getConnPool() {
		return connPool;
	}

	public void setConnPool(Map connPool) {
		this.connPool = connPool;
	}

	public Map getUsePool() {
		return usePool;
	}

	public void setUsePool(Map usePool) {
		this.usePool = usePool;
	}

	/**
	 * this creates the connection manager and ensures that only this class
	 * exists in the whole application.
	 * 
	 * @return
	 */
	public static ConnectionManager getInstance() {
		Lock lock = new ReentrantLock();
		lock.lock();
		if (instance == null) {
			instance = new ConnectionManager();
		}
		lock.unlock();
		return instance;
	}

	java.sql.Connection connection = null;

	public Object get() {
		return connection;
	}

	public boolean Open() {
		connection = null;
		try {
			Class.forName("com.sybase.jdbc3.jdbc.SybDriver");
			
			String path = null;
			
			if (DbConfig.getDbPath() != null) {
				path = DbConfig.getDbPath();
			} else if (DbConfig.getDbName() != null) {
				path = "jdbc:sybase:Tds" + DbConfig.getDbName();
			} else  {
				path = "jdbc:sybase:Tds" + DbConfig.getDbName();
			}
			connection = DriverManager.getConnection("", "", "");
		} catch (Exception e) {
			logger.debug(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		logger.debug("Opened database successfully");

		return true;
	}

	public boolean Close() {
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

	@Override
	public boolean open() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return false;
	}

}
