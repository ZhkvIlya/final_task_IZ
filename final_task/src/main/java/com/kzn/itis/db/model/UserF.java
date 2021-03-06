package com.kzn.itis.db.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserF {

	public UserF() {

	}
	
	public UserF(int id, String firstname, String lastname, int age) {
		if (age < 0 || firstname == null || lastname == null || id < 0) {
			this.id = 0;
			this.firstname = "error_firstname";
			this.lastname = "error_lastname";
			this.age = 0;
		} else {
			this.id = id;
			this.firstname = firstname;
			this.lastname = lastname;
			this.age = age;
		}
	}

	private int id;

	@Column(name = "user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String firstname;

	@Column(name = "firstname")
	@Basic
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	private String lastname;

	@Column(name = "lastname")
	@Basic
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	private int age;

	@Column(name = "age")
	@Basic
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserF event = (UserF) o;

		if (id != event.id)
			return false;
		if (lastname != null ? !lastname.equals(event.lastname)
				: event.lastname != null)
			return false;

		if (age != event.age)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
		result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
		result = 31 * result + age;

		return result;
	}

	public String getUser(UserF res) {
		return res.getId() + ',' + res.getFirstname() + "," + res.getLastname()
				+ ',' + res.getAge();
	}

}
