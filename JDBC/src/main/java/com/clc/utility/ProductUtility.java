package com.clc.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProductUtility {
	
	public static Connection getJdbcConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/app");
		return conn;
	}

}
