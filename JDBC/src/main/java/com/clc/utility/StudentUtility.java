package com.clc.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentUtility {
		
		public static Connection con=null;
		
		public static Connection getJdbcConnection() throws ClassNotFoundException, SQLException {
			
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root", "root");
			return con;
		}

	}



