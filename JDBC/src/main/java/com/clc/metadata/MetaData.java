package com.clc.metadata;

import java.sql.Connection;

import com.clc.one.to.one.Aadhar;
import com.clc.one.to.one.AadharUtility;
import com.clc.utility.JdbcUtility;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MetaData {
	
	public static void main(String[] args) throws ClassNotFoundException{
		
		try {
		Connection con=AadharUtility.getJdbcConnection();
	  
		DatabaseMetaData dmd=con.getMetaData();
		System.out.println(dmd.getDatabaseProductName());
		System.out.println(dmd.getDatabaseProductVersion());
		System.out.println(dmd.getDriverName());
		System.out.println(dmd.getSchemas());
		
		
		ResultSet rs=con.prepareStatement("select * from person").executeQuery();
		ResultSetMetaData rs1=	rs.getMetaData();
		System.out.println(rs1.getCatalogName(1));
		System.out.println(rs1.getColumnType(2));
		System.out.println(rs1.getTableName(1));
			
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	}

}
