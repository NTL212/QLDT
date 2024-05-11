package Models;

import DAO.ProjectDAO;

public class Topic {
	private String topicCode;
	private String name;
	private boolean isEnabled;
	public Topic() {
		super();
	}
	
	public Topic(String topicCode, String name, boolean isEnabled) {
		super();
		this.topicCode = topicCode;
		this.name = name;
		this.isEnabled = isEnabled;
	}
	
	public String getTopicCode() {
		return topicCode;
	}
	
	public void setTopicCode(String topicCode) {
		this.topicCode = topicCode;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public String getAmountProject()
	{
		ProjectDAO projectDAO = new ProjectDAO();
		return projectDAO.calProjectByTopic(this);
	}
	
	public String getAmountEnableProject()
	{
		ProjectDAO projectDAO = new ProjectDAO();
		return projectDAO.calEnableProjectByTopic(this);
	}
}
