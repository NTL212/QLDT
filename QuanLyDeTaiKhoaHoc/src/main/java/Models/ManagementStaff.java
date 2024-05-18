package Models;

import java.time.LocalDate;

public class ManagementStaff extends User {
	private String empCode;

	public ManagementStaff() {
		super();
	}

	public ManagementStaff(String empCode, String name, LocalDate birthday, String idNum, String phoneNum, String email,
			String sex, String address) {
		super();
		this.empCode = empCode;
		this.name = name;
		this.birthday = birthday;
		this.idNum = idNum;
		this.phoneNum = phoneNum;
		this.email = email;
		this.sex = sex;
		this.address = address;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
