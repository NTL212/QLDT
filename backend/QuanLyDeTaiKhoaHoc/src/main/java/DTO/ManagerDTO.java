package DTO;

import Models.Account;
import Models.ManagementStaff;

public class ManagerDTO {
	private ManagementStaff managementStaff;
	
	private Account account;

	public ManagementStaff getManagementStaff() {
		return managementStaff;
	}

	public void setManagementStaff(ManagementStaff managementStaff) {
		this.managementStaff = managementStaff;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public ManagerDTO(ManagementStaff managementStaff, Account account) {
		super();
		this.managementStaff = managementStaff;
		this.account = account;
	}

	public ManagerDTO() {
		super();
	}
	
	
}
