package com.epam.liavitskaya.main.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.epam.liavitskaya.main.util.ConfigUtil;

public class ConnectionManager {

	private static Connection instance;

	private final String dbDriver;
	private final String dbUrl;
	private final String dbUser;
	private final String dbPassword;

	private static class HolderManager {
		private final static ConnectionManager connectionManager = new ConnectionManager();
	}

	private ConnectionManager() {
		Properties properties = ConfigUtil.getInstance().getPropertiesValues();
		dbDriver = properties.getProperty("mysql.db_driver");
		dbUrl = properties.getProperty("mysql.db_url");
		dbUser = properties.getProperty("mysql.db_user");
		dbPassword = properties.getProperty("mysql.db_password");
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static ConnectionManager getManager() {
		return HolderManager.connectionManager;
	}

	public Connection getConnection() {
		if (instance == null) {
			try {
				instance = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return instance;
	}

	public void closeDbResources(PreparedStatement preparedStatement) {
		closeDbResources(null, preparedStatement, null);
	}

	public void closeDbResources(PreparedStatement preparedStatement, ResultSet resultSet) {
		closeDbResources(null, preparedStatement, resultSet);
	}

	public void closeDbResources(Connection connection, PreparedStatement preparedStatement) {
		closeDbResources(connection, preparedStatement, null);
	}

	public void closeDbResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		closeResultSet(resultSet);
		closeStatement(preparedStatement);
		closeConnection(connection);
	}

	public void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Can not close Connection");
			}
		}
	}

	public void closeStatement(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			System.out.println("Can not close Statement");
		}
	}

	public void closeResultSet(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			System.out.println("Can not close ResultSet");
		}
	}

}
