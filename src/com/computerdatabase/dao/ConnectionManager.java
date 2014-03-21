package com.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.mysql.jdbc.ResultSet;

public class ConnectionManager {
	private static String url = "jdbc:mysql://127.0.0.1/computer-database-db";
	private static String user = "jee-cdb";
	private static String password = "password";
	private static BoneCP connectionPool = null;
	
	final static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	
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
		BoneCP connectionPool = ConnectionManager.getConnectionPool();
		if(connectionPool != null)
		{
			connectionPool.shutdown();
		}
	}
	
	public static Connection getConnection()
	{
		logger.info("In getConnection method");
		Connection conn = null;
			
		try {
			conn = getConnectionPool().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("Probleme dans getConnection");
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
	
	public static void closePreparedStatement(PreparedStatement ps)
	{
			try {
				if(ps != null)
					ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void closeResultSet(ResultSet results)
	{
		try {
			if(results != null)
				results.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(Connection conn)
	{
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generaconfigurePoolted catch block
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
	
	public static void setConnectionPool(BoneCP connectionPool) 
	{
		logger.info("In setConnectionPool");
		ConnectionManager.connectionPool = connectionPool;
		logger.info("Quit setConnectionPool");
	}
	
	public static void commitConnection(Connection connection)
	{
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void rollbackConnection(Connection connection)
	{
		try {
			connection.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
