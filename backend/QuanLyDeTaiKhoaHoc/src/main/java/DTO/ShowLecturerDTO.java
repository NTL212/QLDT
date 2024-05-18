package DTO;

import Models.Lecturer;

public class ShowLecturerDTO {
	private String id;
	private String name;
	
	public ShowLecturerDTO() { }
	
	public ShowLecturerDTO(Lecturer lecturer) {
		this.id = lecturer.getLecturerCode();
		this.name = lecturer.getName();
	}
	
	public ShowLecturerDTO(String id, String name) {
		this.id = id;
		this.name = name;
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
}
