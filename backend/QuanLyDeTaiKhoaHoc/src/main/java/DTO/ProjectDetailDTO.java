package DTO;

import java.time.LocalDate;

import DAO.AcceptanceCouncilDAO;
import DAO.LecturerDAO;
import DAO.ProjectDAO;
import DAO.RegistrationDAO;
import DAO.TopicDAO;
import Models.AcceptanceCouncil;
import Models.Lecturer;
import Models.Project;
import Models.Registration;
import Models.Topic;

public class ProjectDetailDTO {
	private String id;
	private String name;
	private LocalDate createDate;
	private String description;
	private int maxMember;
	private LocalDate openRegDate;
	private LocalDate closeRegDate;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate acceptanceDate;
	private float estBudget;
	private String result;
	private String comment;
	private TopicDTO topic;
	private ShowLecturerDTO lecturer;
	private AcceptanceCouncilDTO aCouncil;
	
	public ProjectDetailDTO() {
		super();
	}

	public ProjectDetailDTO(Project project) {
		super();
		this.id = project.getProjectCode();
		this.name = project.getName();
		this.createDate = project.getCreateDate();
		this.description = project.getDescription();
		this.maxMember = project.getMaxMember();
		this.openRegDate = project.getOpenRegDate();
		this.closeRegDate = project.getCloseRegDate();
		this.startDate = project.getStartDate();
		this.endDate = project.getEndDate();
		this.acceptanceDate = project.getAcceptanceDate();
		this.estBudget = project.getEstBudget();
		this.result = project.getResult();
		this.comment = project.getComment();
		this.topic = new TopicDTO(project.getTopic());
		if (project.getLecturer() != null) {
			this.lecturer = new ShowLecturerDTO(project.getLecturer());
		}
		if (project.getaCouncil() != null) {
			this.aCouncil = new AcceptanceCouncilDTO(project.getaCouncil());
		}
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

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxMember() {
		return maxMember;
	}

	public void setMaxMember(int maxMember) {
		this.maxMember = maxMember;
	}

	public LocalDate getOpenRegDate() {
		return openRegDate;
	}

	public void setOpenRegDate(LocalDate openRegDate) {
		this.openRegDate = openRegDate;
	}

	public LocalDate getCloseRegDate() {
		return closeRegDate;
	}

	public void setCloseRegDate(LocalDate closeRegDate) {
		this.closeRegDate = closeRegDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(LocalDate acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public float getEstBudget() {
		return estBudget;
	}

	public void setEstBudget(float estBudget) {
		this.estBudget = estBudget;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public AcceptanceCouncilDTO getaCouncil() {
		return aCouncil;
	}

	public void setaCouncil(AcceptanceCouncilDTO aCouncil) {
		this.aCouncil = aCouncil;
	}
}
