package Models;

import DAO.FalculityDAO;

public class AcceptanceCouncil {
	private String councilCode;
	private String name;
	private Falculity falculity;

	public AcceptanceCouncil() {
		super();
	}

	public AcceptanceCouncil(String councilCode, String name, String falcCode) {
		super();
		this.councilCode = councilCode;
		this.name = name;
		FalculityDAO falcDAO = new FalculityDAO();
		this.falculity = falcDAO.selectFalculityByFalcCode(falcCode);
	}

	public String getCouncilCode() {
		return councilCode;
	}

	public void setCouncilCode(String councilCode) {
		this.councilCode = councilCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Falculity getFalculity() {
		return falculity;
	}

	public void setFalculity(Falculity falculity) {
		this.falculity = falculity;
	}
}
