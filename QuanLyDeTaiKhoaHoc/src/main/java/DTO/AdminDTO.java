package DTO;

import Models.Account;
import Models.Admin;

public class AdminDTO {
	private Admin admin;
	private Account account;
	public AdminDTO(Admin admin, Account account) {
		super();
		this.admin = admin;
		this.account = account;
	}
	public AdminDTO() {
		super();
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
}
