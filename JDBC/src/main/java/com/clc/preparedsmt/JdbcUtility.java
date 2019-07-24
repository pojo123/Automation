package com.clc.preparedsmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtility { 
	
	//private static Connection con= null;
	
	public static Connection getJdbcConnection() throws ClassNotFoundException, SQLException {
		
		
			Class.forName("com.mysql.jdbc.Driver");
		
			Connection	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/teacher","root","root");
		
		return con;
		
	}

}
