package DTO;

import Models.Class;

public class ClassDTO {
	private String id;
	private String name;
	
	public ClassDTO(Class classObj) {
		this.id = classObj.getClassCode();
		this.name = classObj.getName();
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
