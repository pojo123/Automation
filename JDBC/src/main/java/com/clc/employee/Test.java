package com.clc.employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.clc.utility.JdbcUtility;

public class Test {

	
		
		public void insertEmployee() throws ClassNotFoundException, SQLException {
		
		Employee e= new Employee();
		
		Scanner sc= new Scanner(System.in);
	
		System.out.println("Enter Name");
		e.setName(sc.next());
		System.out.println("Enter Mobile");
		e.setMobile(sc.next());
		System.out.println("Enter Age");
		e.setAge(sc.nextInt());
		System.out.println("Enter Address");
		e.setAddress(sc.next());
		
		String sql= "insert into employee(Name, Mobile, Age, Address)values('"+e.getName()+"',"+e.getMobile()+","+e.getAge()+",'"+e.getAddress()+"')";
		
		int result=JdbcUtility.getJdbcConnection().createStatement().executeUpdate(sql);;
	//	int result=smt.executeUpdate(sql);
		
		if (result>0) {
			System.out.println("Successfully inserted....!");
			
		} else {
			System.out.println("failure...try again...!!");

		}
		
		}
		
		public void selectEmployee() throws ClassNotFoundException, SQLException {
			
			List<Employee> l= new ArrayList<Employee>();
		
		//	String sql= "select * from employee";
			String sql= "select min(age) from employee";
			Statement smt=JdbcUtility.getJdbcConnection().createStatement();
			 ResultSet result=smt.executeQuery(sql);
			 
			 while(result.next()) {
				 
				 Employee e= new Employee();
				// e.setId(result.getInt(1));
				// e.setName(result.getString(2));
				// e.setMobile(result.getString(3));
				// e.setAge(result.getInt("age"));
			//	 e.setAddress(result.getString(5));
				 
				 e.setAge(result.getInt(1));
				 
				 l.add(e);
			 }
				for(Employee emp:l)	{
					System.out.println(emp.getId()+"\t"+emp.getName()+"\t"+emp.getMobile()+"\t"+emp.getAge()+"\t"+emp.getAddress());
				}
		}
		
		public void deleteEmployee() throws ClassNotFoundException, SQLException {
			Employee e1= new Employee();
			Scanner sc= new Scanner(System.in);
			System.out.println("Enter id");
			e1.setId(sc.nextInt());
			
			String sql= "delete from employee where id="+e1.getId();
			Statement smt=JdbcUtility.getJdbcConnection().createStatement();
			int rs=smt.executeUpdate(sql);
			if (rs>0) {
				System.out.println("Pass");
			} else {
                 System.out.println("Fail");
			}
			
		}
		
		public void updateEmployee() throws ClassNotFoundException, SQLException {
			Employee e2= new Employee();
			Scanner sc= new Scanner(System.in);
			System.out.println("Enter id");
			e2.setId(sc.nextInt());
			
			String sql= "update employee set age=23 where id="+e2.getId();
			Statement smt=JdbcUtility.getJdbcConnection().createStatement();
			int rst=smt.executeUpdate(sql);
			if (rst>0) {
				System.out.println("successfully updated");
			} else {
                System.out.println("failed..");
			}
			
		}
		
		
		
		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			
			Test t= new Test();
			//t.insertEmployee();
			//t.deleteEmployee();
			//t.updateEmployee();
			t.selectEmployee();

	}

}
