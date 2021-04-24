package com.employee.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundException() {
	}

	public NotFoundException(String s) {
		super(s);
	}

	public NotFoundException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public NotFoundException(Throwable throwable) {
		super(throwable);
	}

	protected NotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
	}
}
