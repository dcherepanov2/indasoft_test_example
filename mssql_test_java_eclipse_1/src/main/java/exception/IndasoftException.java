package exception;

public class IndasoftException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message = null;
	
	public IndasoftException(String error) {
		this.message = error;
	}
	
	public String getErrorMessage() {
		return message;
	}

}
