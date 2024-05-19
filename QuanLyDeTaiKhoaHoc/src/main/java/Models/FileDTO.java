package Models;

import java.time.LocalDateTime;
import DAO.AccountDAO;
import DAO.ProjectDAO;

public class FileDTO {
	private int fileId;
	private byte[] data;
	private LocalDateTime uploadTime;
	private Account uploader;
	private Project proj;

	public FileDTO() {
		super();
	}

	public FileDTO(int fileId, byte[] data, LocalDateTime uploadTime, String uploaderId, String projCode) {
		super();
		this.fileId = fileId;
		this.data = data;
		this.uploadTime = uploadTime;
		this.uploader = new AccountDAO().selectByUsername(uploaderId);
		this.proj = new ProjectDAO().selectProjectByProjectCode(projCode);
	}

	public FileDTO(byte[] data, String uploaderId, String projCode) {
		super();
		this.data = data;
		this.uploader = new AccountDAO().selectByUsername(uploaderId);
		this.proj = new ProjectDAO().selectProjectByProjectCode(projCode);
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public LocalDateTime getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(LocalDateTime uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Account getUploader() {
		return uploader;
	}

	public void setUploader(Account uploader) {
		this.uploader = uploader;
	}

	public Project getProj() {
		return proj;
	}

	public void setProj(Project proj) {
		this.proj = proj;
	}

	public int getFileId() {
		return fileId;
	}
}
