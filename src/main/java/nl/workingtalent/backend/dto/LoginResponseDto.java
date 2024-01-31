package nl.workingtalent.backend.dto;

public class LoginResponseDto {

	private boolean success;
	
	private String token;
	
	private boolean admin;
	
	private String name;
	
	private Long id;
	
	
	// Constructor als niet gelukt is
	public LoginResponseDto() {
		this.success = false;
	}
	
	// Constructor als login wel gelukt is
	public LoginResponseDto(String token, boolean admin, String name, Long id) {
		super();
		this.success = true;
		this.token = token;
		this.admin = admin;
		this.name = name;
		this.id = id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
