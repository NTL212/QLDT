package Models;

import java.time.LocalDate;
import DAO.ClassDAO;

public class Student extends User{
	private String studentCode;
	private Class classDTO;
	
	public Student() {
		super();
	}
	
	public Student(String name, LocalDate birthday, String address, String idNum, String phoneNum, String email,
			String sex, String studentCode, String classCode) {
		super();
		this.name = name;
		this.birthday = birthday;
		this.address = address;
		this.idNum = idNum;
		this.phoneNum = phoneNum;
		this.email = email;
		this.sex = sex;
		this.studentCode = studentCode;
		ClassDAO classDAO = new ClassDAO();
		this.classDTO = classDAO.selectClassByClassCode(classCode);
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public Class getClassDTO() {
		return classDTO;
	}

	public void setClassDTO(Class classDTO) {
		this.classDTO = classDTO;
	}
}
