package DTO;

import Models.Account;
import Models.Lecturer;

public class LectureDTO {
	private Lecturer lecturer;
	private Account account;
	
	
	public LectureDTO() {
		super();
	}
	public LectureDTO(Lecturer lecturer, Account account) {
		super();
		this.lecturer = lecturer;
		this.account = account;
	}
	public Lecturer getLecturer() {
		return lecturer;
	}
	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	

}
