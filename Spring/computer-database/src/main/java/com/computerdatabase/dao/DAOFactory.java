package com.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.mysql.jdbc.ResultSet;


public class DAOFactory {
	
	
	@Autowired
	@Qualifier("companyDAO")
	private CompanyDAO companyDAO;
	
	@Autowired
	@Qualifier("computerDAO")
	private ComputerDAO computerDAO;
	@Autowired
	private LogDAO logDAO;
	private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
	private static String url = "jdbc:mysql://127.0.0.1/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static String user = "jee-cdb";
	private static String password = "password";
	private static BoneCP connectionPool = null;
	
	final static Logger logger = LoggerFactory.getLogger(DAOFactory.class);
	
	
	public  void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}

	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}
	
	//PARTIE POOL DE CONNECTION
	
	public static void configureConnPool()
	{
		logger.info("In configurePool");
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(url);
			config.setUsername(user);
			config.setPassword(password);
			
			config.setMinConnectionsPerPartition(1);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(2);
			connectionPool = new BoneCP(config);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.info("ClassNotFoundException in configurePool");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.info("SQLException in configurePool");
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block<dependency>
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("Quit configurePool");
	}
	
	public static void shutdownConnPool()
	{
		BoneCP connectionPool = getConnectionPool();
		if(connectionPool != null)
		{
			connectionPool.shutdown();
		}
	}
	
	public Connection getConnection()
	{
		logger.info("In getConnection method");
		Connection conn = null;
			
		try {
			conn = getConnectionPool().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Probleme dans getConnection");
		}
		
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Quit getConnection method");
		return conn;
		
	}
	
	public void closePreparedStatement(PreparedStatement ps)
	{
			try {
				if(ps != null)
					ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Error in closePreparedStatement");
				e.printStackTrace();
			}
	}
	
	public void closeResultSet(ResultSet results)
	{
		try {
			if(results != null)
				results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in closeResultSet");
			e.printStackTrace();
		}
	}
	
	public static BoneCP getConnectionPool()
	{
		logger.info("In getConnectionPool");
		if(connectionPool == null)
			configureConnPool();
		logger.info("Quit getConnectionPool");
		return connectionPool;
	}
	
	public void setConnectionPool(BoneCP connectionPools) 
	{
		logger.info("In setConnectionPool");
		connectionPool = connectionPools;
		logger.info("Quit setConnectionPool");
	}
	
	public void commitConnection(Connection connection)
	{
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in commitConnection");
			e.printStackTrace();
		}
	}
	
	public void rollbackConnection(Connection connection)
	{
		try {
			connection.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in rollbackConnection");
			e.printStackTrace();
		}
	}

	//Partie THREAD
	public Connection getConnectionThread()
	{
		if(threadLocal.get() == null)
		{
			Connection connection = getConnection(); //Connection de la pool
			threadLocal.set(connection);
		}
		
		try {
			threadLocal.get().setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in getConnectionThread");
			e.printStackTrace();
		}
		
		return threadLocal.get();
	}
	
	public void closeConnection()
	{
		try {
			threadLocal.get().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in closeConnection");
			e.printStackTrace();
		}
		
		threadLocal.set(null);
	}
	
	
	//PARTIE DAO
	
	
	public CompanyDAO getCompanyDAO()
	{
		return companyDAO;
	}
	
	public ComputerDAO getComputerDAO()
	{
		return computerDAO;
	}
	
	public LogDAO getLogDAO()
	{
		return logDAO;
	}
}
