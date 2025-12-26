package model.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class User {
	private String name;
	private String email;
	private Date birthDate;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Calendar cal = Calendar.getInstance();
	
	public User() {
	}

	public User(String name, String email, Date birthDate) {
		super();
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public Integer id() {
		return 1000 + cal.get(Calendar.YEAR);
	}
	
}
