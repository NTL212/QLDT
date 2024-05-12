package Models;

import java.time.LocalDate;

import DAO.LecturerDAO;
import DAO.AcceptanceCouncilDAO;
import DAO.TopicDAO;
import DAO.ProjectDAO;
import DAO.RegistrationDAO;
import Models.Registration;

public class Project {
	private String projectCode;
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
	private Topic topic;
	private Lecturer lecturer;
	private AcceptanceCouncil aCouncil;
	private Boolean isProposed;
	
	public Project() {
		super();
	}

	public Project(String projectCode, String name, LocalDate createDate, String description, int maxMember,
			LocalDate openRegDate, LocalDate closeRegDate, LocalDate startDate, LocalDate endDate,
			LocalDate acceptanceDate, float estBudget, String result, String comment, String topicCode, String lecturerCode,
			String aCouncilCode) {
		super();
		this.projectCode = projectCode;
		this.name = name;
		this.createDate = createDate;
		this.description = description;
		this.maxMember = maxMember;
		this.openRegDate = openRegDate;
		this.closeRegDate = closeRegDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.acceptanceDate = acceptanceDate;
		this.estBudget = estBudget;
		this.result = result;
		this.comment = comment;
		this.topic = new TopicDAO().selectTopicByTopicCode(topicCode);
		this.lecturer = new LecturerDAO().selectLecturerByLectCode(lecturerCode);
		this.aCouncil = new AcceptanceCouncilDAO().selectACouncilByACouncilCode(aCouncilCode);
	}
	
	public Project(String projectCode, String name, LocalDate createDate, String description, int maxMember,
			LocalDate openRegDate, LocalDate closeRegDate, LocalDate startDate, LocalDate endDate,
			LocalDate acceptanceDate, float estBudget, String result, String comment, String topicCode, String lecturerCode,
			String aCouncilCode, Boolean isProposed) {
		super();
		this.projectCode = projectCode;
		this.name = name;
		this.createDate = createDate;
		this.description = description;
		this.maxMember = maxMember;
		this.openRegDate = openRegDate;
		this.closeRegDate = closeRegDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.acceptanceDate = acceptanceDate;
		this.estBudget = estBudget;
		this.result = result;
		this.comment = comment;
		this.topic = new TopicDAO().selectTopicByTopicCode(topicCode);
		this.lecturer = new LecturerDAO().selectLecturerByLectCode(lecturerCode);
		this.aCouncil = new AcceptanceCouncilDAO().selectACouncilByACouncilCode(aCouncilCode);
		this.isProposed = isProposed;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
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

	public Boolean getIsProposed() {
		return isProposed;
	}

	public void setIsProposed(Boolean isProposed) {
		this.isProposed = isProposed;
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

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	public AcceptanceCouncil getaCouncil() {
		return aCouncil;
	}

	public void setaCouncil(AcceptanceCouncil aCouncil) {
		this.aCouncil = aCouncil;
	}
	
	public String getStatus() {
		ProjectDAO temp = new ProjectDAO();
		return temp.calcProjStatus(this.getProjectCode());
	}
	
	public String getProjectStatusOfLecturer() {
		LocalDate curDate = LocalDate.now();
		if (isProposed) {
			Registration reg = new RegistrationDAO().selectOneRegistration(lecturer.getLecturerCode(), projectCode);
			if (reg.isAccepted() == null) {
				return "Đang đợi phê duyệt";
			} else if (reg.isAccepted() == false) {
				return "Đã bị từ chối";
			}
		}
		if (startDate.isAfter(curDate)) {
			return "Chưa tới hạn làm";
		} else if ((startDate.isBefore(curDate) || startDate.isEqual(curDate)) && (endDate.isAfter(curDate) || endDate.isEqual(curDate))) {
			return "Đang thực hiện";
		}
		return "Đã hoàn thành";
	}

}
