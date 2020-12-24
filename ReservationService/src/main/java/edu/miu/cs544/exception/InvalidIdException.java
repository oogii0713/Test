package edu.miu.cs544.exception;

public class InvalidIdException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvalidIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidIdException(String message) {
        super(message);
    }

}
