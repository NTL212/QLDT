package DTO;

import Models.Project;

public class ProjectDTO {
	private String id;
	private String name;
	private TopicDTO topic;
	private ShowLecturerDTO lecturer;
	private String statusForManager;
	private String statusForLecturer;
	
	public ProjectDTO() {}

	public ProjectDTO(String id, String name, TopicDTO topic, ShowLecturerDTO lecturer) {
		this.id = id;
		this.name = name;
		this.topic = topic;
		this.lecturer = lecturer;
	}
	
	public ProjectDTO(Project project) {
		this.id = project.getProjectCode();
		this.name = project.getName();
		this.topic = new TopicDTO(project.getTopic());
		if (project.getLecturer() != null) {
			this.lecturer = new ShowLecturerDTO(project.getLecturer());
		} else {
			this.lecturer = null;
		}
		this.statusForManager = project.getStatus();
		this.statusForLecturer = project.getProjectStatusOfLecturer();
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

	public String getStatusForManager() {
		return statusForManager;
	}

	public void setStatusForManager(String statusForManager) {
		this.statusForManager = statusForManager;
	}

	public String getStatusForLecturer() {
		return statusForLecturer;
	}

	public void setStatusForLecturer(String statusForLecturer) {
		this.statusForLecturer = statusForLecturer;
	}
	
	
}
