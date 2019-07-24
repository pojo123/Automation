package com.clc.preparedsmt;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeachTest {
	Scanner sc = new Scanner(System.in);

	public void insertTeacher() throws ClassNotFoundException, SQLException {
		
		Teacher t = new Teacher();
		Connection con = JdbcUtility.getJdbcConnection();
		PreparedStatement ps = con.prepareStatement("insert into teach_Details(name,salary,address)values(?,?,?)");

		System.out.println("Enter name");
		t.setName(sc.next());
		System.out.println("Enter salary");
		t.setSalary(sc.nextFloat());
		System.out.println("Enter address");
		t.setAddress(sc.next());

		ps.setString(1, t.getName());
		ps.setFloat(2, t.getSalary());
		ps.setString(3, t.getAddress());

		int result = ps.executeUpdate();
        if (result > 0) {
			System.out.println("Pass");
		} else {
			System.out.println("Fail");
		}
	}

	public void selectTeacher() throws ClassNotFoundException, SQLException {

		List<Teacher> l = new ArrayList<Teacher>();
		
		Connection con = JdbcUtility.getJdbcConnection();
		PreparedStatement ps1 = con.prepareStatement("select * from teach_Details");

		ResultSet rs = ps1.executeQuery();
		while (rs.next()) {

			Teacher t = new Teacher();
			t.setId(rs.getInt(1));
			t.setName(rs.getString(2));
			t.setSalary(rs.getFloat(3));
			t.setAddress(rs.getString(4));

			l.add(t);
		}
		    for (Teacher teacher : l) {
			System.out.println(teacher.getId() + "\t" + teacher.getName() + "\t" + teacher.getSalary() + "\t"
					+ teacher.getAddress());
		}
	}
	
	         public void deleteTeacher() throws ClassNotFoundException, SQLException {
	        	 Teacher t = new Teacher();
	        	 Connection con=JdbcUtility.getJdbcConnection();
	        	 System.out.println("Enter id");
	        	 t.setId(sc.nextInt());
	        	 PreparedStatement ps=con.prepareStatement("delete from teach_Details where id="+t.getId());
	        	 int rs=ps.executeUpdate();
	        	 if (rs > 0) {
					System.out.println("pass");
				} else {
                     System.out.println("fail");
				}
	         }
	         
	         public void updateTeacher() throws ClassNotFoundException, SQLException {
	        	 
	        	 Teacher t = new Teacher();
	        	 System.out.println("Enter id");
	        	 t.setId(sc.nextInt());
	        	 
	        	 Connection conn=JdbcUtility.getJdbcConnection();
	        	 PreparedStatement pss=conn.prepareStatement("update teach_Details set name='ranbir' where id="+t.getId());
	             int result=pss.executeUpdate();
	             if (result > 0) {
					System.out.println("success");
				} else {
                    System.out.println("fail");
				}
	         }
	
             public static void main(String[] args) throws ClassNotFoundException, SQLException {

		     TeachTest tt = new TeachTest();
		     // tt.insertTeacher();
		    // tt.deleteTeacher();
		     tt.updateTeacher();
		     tt.selectTeacher();
		     
	}

}
