package com.clc.one.to.one;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
	
	Scanner sc= new Scanner(System.in);
	
	public void insertPerson() throws ClassNotFoundException, SQLException {
		
		Person person= new Person();
		Aadhar aadhar= new Aadhar();
		
		System.out.println("Enter name");
		person.setName(sc.next());
		System.out.println("Enter mobile");
		person.setMobile(sc.next());
		System.out.println("Enter address");
		person.setAddress(sc.next());
		
		System.out.println("Enter id");
		aadhar.setId(sc.nextInt());
		System.out.println("Enter aadhar no");
		aadhar.setAadhar_no(sc.next());
		
		aadhar.setPerson(person);         //has-a
		person.setAadhar(aadhar);         //has-a
		
		PreparedStatement ps=AadharUtility.getJdbcConnection().prepareStatement("insert into person(name,mobile,address)values(?,?,?)");
		
		ps.setString(1, person.getName());
		ps.setString(2, person.getMobile());
		ps.setString(3, person.getAddress());
		
		int result=ps.executeUpdate();
		if (result > 0) {
			PreparedStatement ps1=AadharUtility.getJdbcConnection().prepareStatement("select max(id) from person");
			ResultSet rs=ps1.executeQuery();
			if(rs.next())
				person.setId(rs.getInt(1));
			
			PreparedStatement ps2=AadharUtility.getJdbcConnection().prepareStatement("insert into aadhar(id, aadhar_no) values(?,?)");
			ps2.setInt(1, person.getId());
			ps2.setString(2, person.getAadhar().getAadhar_no());
			
			int rst=ps2.executeUpdate();
			if (rst > 0) {
				System.out.println("Success");
			} else {
                System.out.println("Failure");
			}
		}
	}
	
	public void selectPerson() throws ClassNotFoundException, SQLException {
		
		List<Person> list= new ArrayList<Person>();
		
		ResultSet rs=AadharUtility.getJdbcConnection().prepareStatement("select * from person p, aadhar a where p.id=a.id").executeQuery();
		
		while(rs.next()) {
			
			Person person= new Person();
			Aadhar aadhar= new Aadhar();
			
			person.setId(rs.getInt(1));
			person.setName(rs.getString(2));
			person.setMobile(rs.getString(3));
			person.setAddress(rs.getString(4));
			
			aadhar.setId(rs.getInt(5));
			aadhar.setAadhar_no(rs.getString(6));
			
			aadhar.setPerson(person);
			person.setAadhar(aadhar);
			
			list.add(person);			
		}
		for (Person p : list) {
			System.out.println(p.getId()+"\t"+p.getName()+"\t"+p.getMobile()+"\t"+p.getAddress()+"\t"+p.getAadhar().getAadhar_no());
		}
	}
	
	public void deletePerson() throws ClassNotFoundException, SQLException {
		
		Person p= new Person();
		//Aadhar a= new Aadhar();
		
		System.out.println("Enter id");
		p.setId(sc.nextInt());
		
		int result=AadharUtility.getJdbcConnection().prepareStatement("delete from person where id="+p.getId()).executeUpdate();
		if (result > 0) {
			System.out.println("pass..");
		} else {
            System.out.println("fail");
		}
		
	}
	
	public void updatePerson() throws ClassNotFoundException, SQLException {
		
		Person p= new Person();
		Aadhar a= new Aadhar();
		
		System.out.println("Enter id");
		p.setId(sc.nextInt());
		//a.setId(sc.nextInt());
		
		int rst=AadharUtility.getJdbcConnection().prepareStatement("update person set name='Jully' where id="+p.getId()).executeUpdate();
	    if (rst > 0) {
			System.out.println("success");
		} else {
            System.out.println("fail");
		}
	}
	
		
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Test t= new Test();
		//t.insertPerson();
		//t.deletePerson();
	//	t.updatePerson();
		t.selectPerson();
	}
		
		
	

}
