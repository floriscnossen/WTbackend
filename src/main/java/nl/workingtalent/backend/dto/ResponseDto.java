package nl.workingtalent.backend.dto;

public class ResponseDto {

	private boolean success;
	
	private String validationError;

	public ResponseDto(boolean success, String validationError) {
		super();
		this.success = success;
		this.validationError = validationError;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getValidationError() {
		return validationError;
	}

	public void setValidationError(String validationError) {
		this.validationError = validationError;
	}
	
}
