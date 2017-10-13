package entity;

import java.io.File;
import java.util.Date;
import java.util.List;

public class Photo {
	List<Integer> photoId;
	List<File> photo;
	String description;
	Date created_time;
	String privacy;
	
	public List<Integer> getPhotoId() {
		return photoId;
	}

	public void setPhotoId(List<Integer> photoId) {
		this.photoId = photoId;
	}

	public List<File> getPhoto() {
		return photo;
	}

	public void setPhoto(List<File> photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

}
