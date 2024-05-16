package Models;

import DAO.StudentDAO;
import DAO.ProjectDAO;

public class ProjectMember {
	private Student student;
	private Project project;

	public ProjectMember() {
		super();
	}

	public ProjectMember(String studentCode, String projectCode) {
		this.student = new StudentDAO().selectStudentByStudentCode(studentCode);
		this.project = new ProjectDAO().selectProjectByProjectCode(projectCode);
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
