package exceptions;

@SuppressWarnings("serial")
public class WrongArgumentsException extends Exception {
	public WrongArgumentsException(String msg) {
		super(msg);
	}
}
