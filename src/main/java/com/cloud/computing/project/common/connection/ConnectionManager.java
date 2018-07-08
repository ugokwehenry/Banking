package com.cloud.computing.project.common.connection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//import java.util.logging.Logger;
import org.apache.log4j.Logger;
import javax.naming.InitialContext;


public class ConnectionManager implements Connection{
	private static final Logger logger = Logger.getLogger(ConnectionManager.class.getName());
	
	private int maxSize = 1;

	/**
	 * the pool of connections available for the application
	 * this is to prevent DAO getting there own resources
	 * this would unify all database management.
	 */
	private Map connPool = new HashMap();
	/**
	 * the pool of connections in use by an entity, module or session.
	 */
	private Map usePool = new HashMap();
	/**
	 *  the connection manager instance
	 */
	
	private static ConnectionManager instance = null;
	
	private ConnectionManager(){
		init();
	}
	
	
	private void init()
	{			
		logger.info("Getting DB connection.....");
	      System.out.println("Initializing ..................");
			try
			{					
				//Class.forName("com.sybase.jdbc3.jdbc.SybDriver");
				for (int i = 0; i < getMaxSize(); i++)
				{
					/**Connection conn =
							DriverManager.getConnection(
									"jdbc:sybase:Tds:10.152.2.151:5000/banking",
									"sa",
									"csa123");
					static method that reads properties from the orbitwebservice.xml config file 
					*/
					//Connection conn = new WebserviceConnection().makeConnection();						
					Connection conn = null;
					getConnPool().put(conn, conn);

				}
				
				System.out.println("Initialized ..................");

			} catch (Exception e)// problem loading driver, class not exist?
			{
				logger.debug(e.getClass().getName() + ": " +e.getMessage());
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
	 * this creates the connection manager and ensures that 
	 * only this class exists in the whole application.
	 * @return
	 */
	public static ConnectionManager getInstance()
	{
		Lock lock = new ReentrantLock();
		lock.lock();
		if (instance == null)
		{
			instance = new ConnectionManager();
		}
		lock.unlock();
		return instance;
	}
	
	Connection connection  = null;
	public Object get(){
		return connection;
	}
	
	public boolean Open(){
		connection = null;
		
		return false;
	}

	public boolean Close() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
