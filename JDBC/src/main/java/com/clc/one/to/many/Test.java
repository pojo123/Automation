package com.clc.one.to.many;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

	Scanner sc = new Scanner(System.in);

	public void insertEmpBank() throws ClassNotFoundException, SQLException {

		Employee employee = new Employee();
		System.out.println("Enter name");
		employee.setName(sc.next());
		System.out.println("Enter salary");
		employee.setSalary(sc.nextFloat());

		List<Bank> list = new ArrayList<Bank>();
		System.out.println("Enter no. of banks");
		int noOfBanks = sc.nextInt();

		for (int i = 0; i < noOfBanks; i++) {
			Bank bank = new Bank();
			System.out.println("Enter name");
			bank.setName(sc.next());
			System.out.println("Enter type");
			bank.setType(sc.next());
			System.out.println("Enter accountNo");
			bank.setAccountNo(sc.next());

			bank.setEmployee(employee);
			list.add(bank);
		}
		employee.setListOfBanks(list);

		PreparedStatement pst = BankUtility.getJdbcConnection()
				.prepareStatement("insert into emp_Details(name,salary)values(?,?)");
		pst.setString(1, employee.getName());
		pst.setFloat(2, employee.getSalary());

		int rt = pst.executeUpdate();

		if (rt > 0) {
			ResultSet rs = BankUtility.getJdbcConnection().prepareStatement("select max(id) from emp_Details")
					.executeQuery();

			if (rs.next()) {
				employee.setId(rs.getInt(1));
				for (Bank b : list) {
					PreparedStatement ps = BankUtility.getJdbcConnection()
							.prepareStatement("insert into bank_Details(id,name,type,accountNo)values(?,?,?,?)");
					ps.setInt(1, employee.getId());
					ps.setString(2, b.getName());
					ps.setString(3, b.getType());
					ps.setString(4, b.getAccountNo());

					int rst = ps.executeUpdate();

					if (rst > 0) {
						System.out.println("Success");
					} else {
						System.out.println("Fail...");
					}
				}
			}
		}
	}

	public void selectEmp_Bank() throws ClassNotFoundException, SQLException {

		List<Employee> list = new ArrayList<Employee>();
		ResultSet rs = BankUtility.getJdbcConnection().prepareStatement("select * from emp_Details").executeQuery();
		while (rs.next()) {

			Employee employee = new Employee();
			employee.setId(rs.getInt(1));
			employee.setName(rs.getString(2));
			employee.setSalary(rs.getFloat(3));

			list.add(employee);
		}

		List<Employee> l = new ArrayList<Employee>();

		for (Employee emp : list) {
			PreparedStatement ps1 = BankUtility.getJdbcConnection()
					.prepareStatement("select b.bank_id,b.name,b.type,b.accountNo from emp_Details e, bank_Details b where e.id=b.id and e.id=?");

			ps1.setInt(1, emp.getId());
			ResultSet rss = ps1.executeQuery();

			List<Bank> listOfBank = new ArrayList<Bank>();
			while (rss.next()) {

				Bank bank = new Bank();

				bank.setId(rss.getInt(1));
				bank.setName(rss.getString(2));
				bank.setType(rss.getString(3));
				bank.setAccountNo(rss.getString(4));

				listOfBank.add(bank);
			}
			     emp.setListOfBanks(listOfBank);
			     l.add(emp);
			     
		}   
				
			for (Employee emp1 : l) {
				System.out.println(emp1.getId()+"\t"+emp1.getName()+"\t"+emp1.getSalary());
				
				for (Bank bank:emp1.getListOfBanks()) {
					System.out.println("\t\t\t"+bank.getId()+"\t"+bank.getName()+"\t"+bank.getType()+"\t"+bank.getAccountNo());
				}	
		}
	}
	
	     public void deleteEmp_Details() throws ClassNotFoundException, SQLException {
	    	 
	    	 Employee e= new Employee();
	    	 Bank b= new Bank();
	    	 
	    	 System.out.println("Enter id");
	    	 e.setId(sc.nextInt());
	    	 
	    	 int result=BankUtility.getJdbcConnection().prepareStatement("delete from emp_Details where id="+e.getId()).executeUpdate();
	         if (result > 0) {
				System.out.println("pass...");
			} else {
                System.out.println("fail..");
			}
	     }

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Test t = new Test();
		//t.insertEmpBank();
		t.deleteEmp_Details();
		t.selectEmp_Bank();
	}

}
