package nl.workingtalent.backend.dto;

public class LoginResponseDto {

	private boolean success;
	
	private String token;
	
	private boolean admin;
	
	private String name;
	
	// Constructor als niet gelukt is
	public LoginResponseDto() {
		this.success = false;
	}
	
	// Constructor als login wel gelukt is
	public LoginResponseDto(String token, boolean admin, String name) {
		super();
		this.success = true;
		this.token = token;
		this.admin = admin;
		this.name = name;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
