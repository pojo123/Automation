package many_to_many;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Student {
	private int rollno;
	private String name;
	private int age;
	private List<Address> listOfAddr;
	 

}
