package com.clc.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilityMtoM {

	public static Connection getJdbcConnection() throws ClassNotFoundException, SQLException {
		
		
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/many_to_many", "root", "root");
		return con;
	}
	
}
