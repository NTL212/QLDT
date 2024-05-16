package Models;

import java.time.LocalDate;
import DAO.FalculityDAO;

public class Lecturer extends User {
	private String lecturerCode;
	private Falculity falculity;

	public Lecturer() {
		super();
	}

	public Lecturer(String lecturerCode, String name, LocalDate birthday, String address, String idNum, String phoneNum,
			String email, String sex, String falculityCode) {
		super();
		this.lecturerCode = lecturerCode;
		this.name = name;
		this.birthday = birthday;
		this.address = address;
		this.idNum = idNum;
		this.phoneNum = phoneNum;
		this.email = email;
		this.sex = sex;
		FalculityDAO falcDAO = new FalculityDAO();
		this.falculity = falcDAO.selectFalculityByFalcCode(falculityCode);
	}

	public String getLecturerCode() {
		return lecturerCode;
	}

	public void setLecturerCode(String lecturerCode) {
		this.lecturerCode = lecturerCode;
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

	public Falculity getFalculity() {
		return falculity;
	}

	public void setFalculity(Falculity falculity) {
		this.falculity = falculity;
	}
}