package com.todo.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	private static Connection conn;

	public static Connection getConnection() {
		if (conn != null)
			return conn;

		// InputStream inputStream =
		// DBUtil.class.getClassLoader().getResourceAsStream(
		// "/com/db.properties" );
		// Properties properties = new Properties();
		try {
			// properties.load( inputStream );
			// String driver = properties.getProperty( "driver" );
			// String url = properties.getProperty( "url" );
			// String user = properties.getProperty( "user" );
			// String password = properties.getProperty( "password" );
			Class.forName("com.mysql.jdbc.Driver");
			// conn = DriverManager.getConnection( url, user, password );
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/TaskManagement", "root",
					"root");
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void closeConnection(Connection toBeClosed) {
		if (toBeClosed == null)
			return;
		try {
			toBeClosed.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
