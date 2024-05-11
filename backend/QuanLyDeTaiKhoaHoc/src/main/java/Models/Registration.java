package Models;

import java.time.LocalDate;

import DAO.LecturerDAO;
import DAO.ProjectDAO;
import DAO.ManagementStaffDAO;

public class Registration {
	private Lecturer lect;
	private Project proj;
	private LocalDate regDate;
	private LocalDate acceptDate;
	private Boolean isAccepted;
	private ManagementStaff mgtStaff;
	
	public Registration() {
		super();
	}

	public Registration(String lectCode, String projCode, LocalDate regDate, LocalDate acceptDate, Boolean isAccepted,
			String mgtStaffCode) {
		super();
		this.lect = new LecturerDAO().selectLecturerByLectCode(lectCode);
		this.proj = new ProjectDAO().selectProjectByProjectCode(projCode);
		this.regDate = regDate;
		this.acceptDate = acceptDate;
		this.isAccepted = isAccepted;
		this.mgtStaff = new ManagementStaffDAO().selectByEmpCode(mgtStaffCode);
	}
	
	public Registration(String lectCode, String projCode) {
		super();
		this.lect = new LecturerDAO().selectLecturerByLectCode(lectCode);
		this.proj = new ProjectDAO().selectProjectByProjectCode(projCode);
	}

	public Lecturer getLect() {
		return lect;
	}

	public void setLect(Lecturer lect) {
		this.lect = lect;
	}
	
	public Boolean isAccepted() {
		return isAccepted;
	}

	public void setisAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public Project getProj() {
		return proj;
	}

	public void setProj(Project proj) {
		this.proj = proj;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public LocalDate getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(LocalDate acceptDate) {
		this.acceptDate = acceptDate;
	}

	public ManagementStaff getMgtStaff() {
		return mgtStaff;
	}

	public void setMgtStaff(ManagementStaff mgtStaff) {
		this.mgtStaff = mgtStaff;
	}
}
