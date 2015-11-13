package br.com.pizzaservice.dao.repository;

import org.springframework.stereotype.Component;

//@Component
public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 5215793093479304795L;

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

}
