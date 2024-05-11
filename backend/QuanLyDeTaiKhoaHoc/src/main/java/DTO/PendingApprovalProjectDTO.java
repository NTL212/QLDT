package DTO;

import Models.Project;
import Models.Registration;

public class PendingApprovalProjectDTO {
	private String id;
	private String name;
	private TopicDTO topic;
	private ShowLecturerDTO lecturer;
	private String status;
	
	public PendingApprovalProjectDTO(Registration registration) {
		super();
		Project project = registration.getProj();
		this.id = project.getProjectCode();
		this.name = project.getName();
		this.topic = new TopicDTO(project.getTopic());
		this.lecturer = new ShowLecturerDTO(registration.getLect());
		this.status = calcStatus(project);
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

	public TopicDTO getTopic() {
		return topic;
	}
	
	public void setTopic(TopicDTO topic) {
		this.topic = topic;
	}

	public ShowLecturerDTO getLecturer() {
		return lecturer;
	}

	public void setLecturer(ShowLecturerDTO lecturer) {
		this.lecturer = lecturer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String calcStatus(Project project) {
		if (project.getIsProposed() == true) {
			return "Đề tài đề xuất";
		} else {
			return project.getStatus();
		}
	}
}
