package DTO;

import Models.Notification;

public class NoticeDTO {
	private String senderId;	
	private String receiveId;
	private String content;
	private String title;
	
	
	public NoticeDTO() {
		super();
	}

	
	public NoticeDTO(Notification noti) {
		super();
		this.senderId = noti.getSender().getUsername();
		this.receiveId = noti.getReceiver().getUsername();
		this.content = noti.getContent();
		this.title = noti.getTitle();
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

}
