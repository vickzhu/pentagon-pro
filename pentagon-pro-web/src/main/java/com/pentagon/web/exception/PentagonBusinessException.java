package com.pentagon.web.exception;

/**
 * 业务异常
 * @author zhuxb
 *
 */
public class PentagonBusinessException extends RuntimeException {

	private static final long serialVersionUID = -6052361405731837734L;

	public PentagonBusinessException() {
		super();
	}

	public PentagonBusinessException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PentagonBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public PentagonBusinessException(String message) {
		super(message);
	}

	public PentagonBusinessException(Throwable cause) {
		super(cause);
	}

}
