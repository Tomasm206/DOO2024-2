package co.edu.uco.crosscutting.exception;

import co.edu.uco.crosscutting.exceptions.enums.Layer;

public class BusinessLogicUcobetException extends UcoBetException {

	private static final long serialVersionUID = 1L;

	public BusinessLogicUcobetException(final String userMessage, final String technicalMessage,
			final Exception rootException) {
		super(userMessage, technicalMessage, rootException, Layer.BUSINESSLOGIC);
	}

	public static final BusinessLogicUcobetException crear(final String userMessage, final String technicalMessage,
			final Exception rootException) {
		return new BusinessLogicUcobetException(userMessage, technicalMessage, rootException);
	}

	public static final BusinessLogicUcobetException crear(final String userMessage) {
		return new BusinessLogicUcobetException(userMessage, userMessage, new Exception());
	}

	public static final BusinessLogicUcobetException crear(final String userMessage, final String technicalMessage) {
		return new BusinessLogicUcobetException(userMessage, technicalMessage, new Exception());
	}

}
