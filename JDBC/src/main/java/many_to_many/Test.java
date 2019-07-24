package many_to_many;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.clc.utility.UtilityMtoM;

public class Test {

	Scanner sc = new Scanner(System.in);

	public void selectAddress() throws ClassNotFoundException, SQLException {
		List<Address> addrList = new ArrayList<Address>();

		ResultSet rs = UtilityMtoM.getJdbcConnection().prepareStatement("select *from address").executeQuery();
		while (rs.next()) {
			Address address = new Address();
			address.setId(rs.getInt(1));
			address.setAddress(rs.getString(2));

			addrList.add(address);

		}
		System.out.println("\tID+\t+Address");
		for (Address address : addrList) {
			System.out.println("\t" + address.getId() + "\t" + address.getAddress() + "\t");

		}

	}

	public void insertRecord() throws ClassNotFoundException, SQLException {

		Student s = new Student();

		System.out.println("Enter name");
		s.setName(sc.next());
		System.out.println("Enter age");
		s.setAge(sc.nextInt());
		selectAddress();

		List<Address> addressList = new ArrayList<Address>();
		System.out.println("How Many Address u want to add");
		int noOfAdd = sc.nextInt();
		for (int i = 0; i < noOfAdd; i++) {
			Address address = new Address();
			System.out.println("Enter address id");
			address.setId(sc.nextInt());
			addressList.add(address);
		}
		s.setListOfAddr(addressList);

		int result = UtilityMtoM.getJdbcConnection()
				.prepareStatement("insert into student(name,age) values('" + s.getName() + "'," + s.getAge() + ")")
				.executeUpdate();

		if (result > 0) {

			ResultSet rs = UtilityMtoM.getJdbcConnection().prepareStatement("select max(rollno) from student")
					.executeQuery();

			if (rs.next()) {
				s.setRollno(rs.getInt(1));

				PreparedStatement ps = UtilityMtoM.getJdbcConnection()
						.prepareStatement("insert into studaddress(sid,aid)values(?,?)");

				for (Address ads : s.getListOfAddr()) {
					ps.setInt(1, s.getRollno());
					ps.setInt(2, ads.getId());

					int result1 = ps.executeUpdate();
					if (result1 > 0)
						System.out.println("success...");
					else
						System.out.println("Fail...");
				}
			}
		}

	}

	public void selectRecord() throws ClassNotFoundException, SQLException {

		List<Student> listOfStudent = new ArrayList<Student>();

		ResultSet rs = UtilityMtoM.getJdbcConnection().prepareStatement("select *from student").executeQuery();

		while (rs.next()) {
			Student s1 = new Student();

			s1.setRollno(rs.getInt(1));
			s1.setName(rs.getString(2));
			s1.setAge(rs.getInt(3));

			listOfStudent.add(s1);
		}

		List<Student> studList = new ArrayList<Student>();

		for (Student student : listOfStudent) {

			List<Address> addresses = new ArrayList<Address>();

			ResultSet rs1 = UtilityMtoM.getJdbcConnection().prepareStatement(
					"select a.id, a.address from student s, address a, studaddress sa where s.rollno=sa.sid and a.id=sa.aid and sa.sid="
							+ student.getRollno())
					.executeQuery();
			while (rs1.next()) {

				Address address = new Address();
				address.setId(rs1.getInt(1));
				address.setAddress(rs1.getString(2));
				addresses.add(address);
			}

			student.setListOfAddr(addresses);
			studList.add(student);
		}
		for (Student student : studList) {
			System.out.println(student.getRollno() + "\t" + student.getName() + "\t" + student.getAge());

			for (Address addr : student.getListOfAddr()) {
				System.out.println("\t"+addr.getId() + "\t" + addr.getAddress());
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Test t = new Test();
		//t.insertRecord();
		t.selectRecord();

	}

}
