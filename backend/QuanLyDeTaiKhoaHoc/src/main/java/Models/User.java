package Models;

import java.time.LocalDate;

public class User {
	protected String name;
	protected LocalDate birthday;
	protected String address;
	protected String idNum;
	protected String phoneNum;
	protected String email;
	protected String sex;

	public User() {
		super();
	}

	public User(String name, LocalDate birthday, String address, String idNum, String phoneNum, String email,
			String sex) {
		super();
		this.name = name;
		this.birthday = birthday;
		this.address = address;
		this.idNum = idNum;
		this.phoneNum = phoneNum;
		this.email = email;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
