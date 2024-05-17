package DTO;

import java.time.LocalDate;

import Models.Falculity;
import Models.Student;

public class StudentDTO {
	private String id;
	private String name;
	private LocalDate birthday;
	private Falculity falculity;
	private ClassDTO classObj;
	
	public StudentDTO(Student student) {
		this.id = student.getStudentCode();
		this.name = student.getName();
		this.birthday = student.getBirthday();
		this.falculity = student.getClassDTO().getMajor().getFalc();
		this.classObj = new ClassDTO(student.getClassDTO());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Falculity getFalculity() {
		return falculity;
	}

	public void setFalculity(Falculity falculity) {
		this.falculity = falculity;
	}

	public ClassDTO getClassObj() {
		return classObj;
	}

	public void setClassObj(ClassDTO classObj) {
		this.classObj = classObj;
	}
	
}
