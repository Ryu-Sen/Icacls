package permissions.logic;

import java.util.Date;

import lombok.Data;


@Data
public class Permissions {

	public String name;
	public String email;
	public Date dateOfBirth;
	public double salary;

	public Permissions(String name, String email, Date dateOfBirth, double salary) {

		this.name = name;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.salary = salary;

	}

}
