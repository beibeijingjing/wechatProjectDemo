/**
 * 
 */
package core.exception;

import weixin.manager.wxentity.ResponBaseEntity;

public class WxBaseException extends Exception {

	private static final long serialVersionUID = -5181800588832010641L;
	private ResponBaseEntity error;

	public ResponBaseEntity getError() {
		return error;
	}

	public void setError(ResponBaseEntity error) {
		this.error = error;
	}

	/**
	 * 
	 */
	public WxBaseException() {
	}

	/**
	 * @param message
	 */
	public WxBaseException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public WxBaseException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public WxBaseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 */
	public WxBaseException(ResponBaseEntity errorJson) {
		super(errorJson.getErrmsg());
		this.error = errorJson;
	}

}
