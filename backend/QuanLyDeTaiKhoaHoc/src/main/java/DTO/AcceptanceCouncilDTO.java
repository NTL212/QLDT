package DTO;

import Models.AcceptanceCouncil;

public class AcceptanceCouncilDTO {
	private String id;
	private String name;
	
	public AcceptanceCouncilDTO(AcceptanceCouncil aCouncil) {
		this.id = aCouncil.getCouncilCode();
		this.name = aCouncil.getName();
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
