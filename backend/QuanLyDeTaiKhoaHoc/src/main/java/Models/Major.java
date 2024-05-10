package Models;

import DAO.FalculityDAO;

public class Major {
	private String majorCode;
	private String name;
	private Falculity falc;
	
	public Major() {
		super();
	}

	public Major(String majorCode, String name, String falcCode) {
		super();
		this.majorCode = majorCode;
		this.name = name;
		FalculityDAO falcDAO = new FalculityDAO();
		this.falc = falcDAO.selectFalculityByFalcCode(falcCode);
	}

	public String getMajorCode() {
		return majorCode;
	}

	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Falculity getFalc() {
		return falc;
	}

	public void setFalc(Falculity falc) {
		this.falc = falc;
	}
}
