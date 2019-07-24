package com.clc.callablestmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ShopUtility {

	public static Connection getJdbcConnection() throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shop");
		return con;
	}
}
