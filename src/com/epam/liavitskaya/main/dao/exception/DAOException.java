package com.epam.liavitskaya.main.dao.exception;

public class DAOException extends Exception {
	
	private static final long serialVersionUID = -3544930211968158628L;

	public DAOException() {
		super();
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

	public DAOException(String message) {
		super(message);
	}

}
