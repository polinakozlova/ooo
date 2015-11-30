package domain;

import java.util.ArrayList;

public class DomainException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	ArrayList<String> errors = new ArrayList<String>();

	public DomainException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DomainException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DomainException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DomainException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DomainException() {
		// TODO Auto-generated constructor stub
	}

	public DomainException(ArrayList<String> errors) {
		this.errors = errors;
	}
	
	public ArrayList<String> getErrors(){
		return errors;
	}

}
