package Models;

import java.time.LocalDateTime;
import DAO.AccountDAO;

public class Notification {
	private int id;
	private Account sender;
	private Account receiver;
	private String title;
	private String content;
	private LocalDateTime sentTime;
	
	public Notification() {
		super();
	}

	public Notification(int id, String senderId, String receiverId, String title, String content,
			LocalDateTime sentTime) {
		super();
		this.id = id;
		this.sender = new AccountDAO().selectByUsername(senderId);
		this.receiver = new AccountDAO().selectByUsername(receiverId);
		this.title = title;
		this.content = content;
		this.sentTime = sentTime;
	}
	
	public Notification(String senderId, String receiverId, String title, String content,
			LocalDateTime sentTime) {
		super();
		this.sender = new AccountDAO().selectByUsername(senderId);
		this.receiver = new AccountDAO().selectByUsername(receiverId);
		this.title = title;
		this.content = content;
		this.sentTime = sentTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	public Account getReceiver() {
		return receiver;
	}

	public void setReceiver(Account receiver) {
		this.receiver = receiver;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getSentTime() {
		return sentTime;
	}

	public void setSentTime(LocalDateTime sentTime) {
		this.sentTime = sentTime;
	}
}
