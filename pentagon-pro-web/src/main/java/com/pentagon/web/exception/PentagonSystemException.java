package com.pentagon.web.exception;

/**
 * 系统异常
 * @author zhuxb
 *
 */
public class PentagonSystemException extends RuntimeException {

	private static final long serialVersionUID = -6210078319323680850L;

	public PentagonSystemException() {
		super();
	}

	public PentagonSystemException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PentagonSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public PentagonSystemException(String message) {
		super(message);
	}

	public PentagonSystemException(Throwable cause) {
		super(cause);
	}

}
