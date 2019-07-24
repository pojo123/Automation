package com.transac.managemnt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.clc.one.to.one.Aadhar;
import com.clc.one.to.one.AadharUtility;
import com.clc.one.to.one.Person;

public class TransacTest {

	Scanner sc = new Scanner(System.in);

	public void insertPerson() throws ClassNotFoundException, SQLException {

		Connection con = AadharUtility.getJdbcConnection();
		try {
			Person person = new Person();
			Aadhar aadhar = new Aadhar();

			con.setAutoCommit(false);

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

			aadhar.setPerson(person); // has-a
			person.setAadhar(aadhar); // has-a

			PreparedStatement ps = con.prepareStatement("insert into person(name,mobile,address)values(?,?,?)");

			ps.setString(1, person.getName());
			ps.setString(2, person.getMobile());
			ps.setString(3, person.getAddress());

			int result = ps.executeUpdate();
			if (result > 0) {
				PreparedStatement ps1 = con.prepareStatement("select max(id) from person");
				ResultSet rs = ps1.executeQuery();
				if (rs.next())
					person.setId(rs.getInt(1));

				PreparedStatement ps2 = con.prepareStatement("insert into aadhar(id, aadhar_no) values(?,?)");
				ps2.setInt(1, person.getId());
				ps2.setString(2, person.getAadhar().getAadhar_no());

				int rst = ps2.executeUpdate();
				if (rst > 0) {
					System.out.println("Success");
				} else {
					System.out.println("Failure");
				}
			}
			con.commit();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		TransacTest tt = new TransacTest();

		tt.insertPerson();

	}

}
