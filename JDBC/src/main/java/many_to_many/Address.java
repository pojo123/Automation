package many_to_many;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Address {
	private int id;
	private String address;
	private List<Student> listOfStud;

}
