package net.anthavio.joshi.client;

/**
 * 
 * @author martin.vanek
 *
 */
public class JoshiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JoshiException(String message, Throwable cause) {
		super(message, cause);
	}

	public JoshiException(String message) {
		super(message);
	}

	public JoshiException(Throwable cause) {
		super(cause);
	}

}
