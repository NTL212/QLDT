package DTO;

import Models.Topic;

public class TopicDTO {
	private String id;
	private String name;
	
	public TopicDTO() { }
	
	public TopicDTO(Topic topic) {
		this.id =  topic.getTopicCode();
		this.name =  topic.getName();
	}
	
	public TopicDTO(String id, String name) {
		this.id = id;
		this.name = name;
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
}
