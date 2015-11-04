package be.kroma.exceptions;

public class Rome2RioBadRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private final String message;
	
	public Rome2RioBadRequestException(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
