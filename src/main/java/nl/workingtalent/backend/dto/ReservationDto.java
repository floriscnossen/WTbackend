package nl.workingtalent.backend.dto;

import java.util.Date;

public class ReservationDto {
	private long id;
	private CopyDto copy;
	private UserDto user;
	private Date date;
	private String status;

	//Getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CopyDto getCopy() {
		return copy;
	}

	public void setCopy(CopyDto copy) {
		this.copy = copy;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUserId(UserDto user){
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
