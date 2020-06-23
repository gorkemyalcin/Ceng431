package exceptions;

public class IncorrectButtonException extends Exception {
	
	private static final long serialVersionUID = 1137844584004322858L;

	public IncorrectButtonException(String errorMessage) {
		super(errorMessage);
	}
}
