package com.clc.student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.clc.utility.JdbcUtility;
import com.clc.utility.StudentUtility;

public class StudTest {
	
	public void insertStudent() throws ClassNotFoundException, SQLException {
		
		Student s= new Student();
		
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter rollno");
		s.setRollno(sc.nextInt());
		System.out.println("Enter name");
		s.setName(sc.next());
		System.out.println("Enter age");
		s.setAge(sc.nextInt());
		
	//	String sql= "insert into stud_Details(Rollno,name,age)values("+s.getRollno()+",'"+s.getName()+"',"+s.getAge()+")";
		Connection con=StudentUtility.getJdbcConnection();
		Statement st=con.createStatement();
		int result=st.executeUpdate("insert into stud_Details(Rollno,name,age)values("+s.getRollno()+",'"+s.getName()+"',"+s.getAge()+")");
		
		if (result > 0) {
			System.out.println("Insert successful");
		} else {
            System.out.println("Failed");
		}				
	}
	
	
	public void selectStudent() throws ClassNotFoundException, SQLException {
		
		List<Student> ll= new ArrayList<Student>();
		
		String sql= "select * from stud_Details";
		Statement st1=StudentUtility.getJdbcConnection().createStatement();
		ResultSet rs=st1.executeQuery(sql);
		
		while(rs.next()) {
			
			Student s2= new Student();
			s2.setRollno(rs.getInt(1));           //column index
			s2.setName(rs.getString("name"));       //column label     
			s2.setAge(rs.getInt(3));
			
			ll.add(s2);	
		}
		
		for(Student st: ll) {
			System.out.println(st.getRollno()+"\t"+st.getName()+"\t"+st.getAge());
		}	
	}
	
	public void deleteStudent() throws ClassNotFoundException, SQLException {
		
		Student s3= new Student();
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter name");
		s3.setName(sc.next());
		
		String sql= "delete from stud_Details where name=" +s3.getName();
		Statement s=StudentUtility.getJdbcConnection().createStatement();
		int rst=s.executeUpdate(sql);
		
		if (rst > 0) {
			System.out.println("Success");
		} else {
            System.out.println("Failed...");
		}
		
	}
	
	public void updateStudent() throws ClassNotFoundException, SQLException {
		
		Student s4= new Student();
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter rollno");
		s4.setRollno(sc.nextInt());
		
		String sql= "update stud_Details set name='vedant' where rollno=" +s4.getRollno();
		Statement st=StudentUtility.getJdbcConnection().createStatement();
		int rs=st.executeUpdate(sql);
		
		if (rs > 0) {
			System.out.println("update success...");
		} else {
            System.out.println("update failed");
		}
		
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		StudTest stt= new StudTest();
		//stt.insertStudent();
		stt.deleteStudent();
		//stt.updateStudent();
		stt.selectStudent();
		
		}
}


