package net.anthavio.joshi.client;

import java.util.List;

import net.anthavio.joshi.client.JoshiResponse.Message;

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

	public JoshiException(String status, List<Message> messages) {
		super(status + " " + messages);
	}
}
