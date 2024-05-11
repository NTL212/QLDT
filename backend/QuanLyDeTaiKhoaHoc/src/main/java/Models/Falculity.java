package Models;

public class Falculity {
	
	private String falculityCode;
	private String name;
	
	
	public Falculity() {
		super();
	}

	public Falculity(String falculityCode, String name) {
		super();
		this.falculityCode = falculityCode;
		this.name = name;
	}

	public String getFalculityCode() {
		return falculityCode;
	}

	public void setFalculityCode(String falculityCode) {
		this.falculityCode = falculityCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
