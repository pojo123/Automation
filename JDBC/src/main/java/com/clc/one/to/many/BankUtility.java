package com.clc.one.to.many;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankUtility {
	
public static Connection getJdbcConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/one_to_many", "root", "root");
		
		return conn;

}
}