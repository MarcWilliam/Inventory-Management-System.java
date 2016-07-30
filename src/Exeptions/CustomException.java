package Exeptions;

/**
 *
 * @author marcw
 */
public class CustomException extends Exception {

	/**
	 *
	 * @param message
	 */
	public CustomException(String message) {
		super(message);
	}

	/**
	 *
	 * @param message
	 * @param cause
	 */
	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 *
	 * @param cause
	 */
	public CustomException(Throwable cause) {
		super(cause);
	}

	/**
	 *
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
