package nl.workingtalent.backend.dto;

import java.util.Date;

public class ReservationDto {
	private long id;
	private CopyDto copy;
	private UserDto user;
	private Date startDate;
	private Date endDate;
	private String status;
	
	//Constructors
	public ReservationDto() {}

	public ReservationDto(CopyDto copy, UserDto user, Date startDate, Date endDate, String status) {
		this.copy = copy;
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
