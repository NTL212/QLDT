package Models;

import DAO.MajorDAO;

public class Class {
	private String classCode;
	private String name;
	private Major major;
	
	public Class() {
		super();
	}

	public Class(String classCode, String name, String majorCode) {
		super();
		this.classCode = classCode;
		this.name = name;
		MajorDAO majorDAO = new MajorDAO();
		this.major = majorDAO.selectMajorByMajorCode(majorCode);
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}
}
