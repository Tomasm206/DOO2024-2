package co.edu.uco.ucobet.crosscutting.exceptions;

import co.edu.uco.crosscutting.exceptions.enums.Layer;

public class DataUcoBetException extends UcoBetException {
	// Se recomienda no tener mas de 5 padres,
	// sin embargo hay una excepcion aqui ya que necesitamos
	// llegar a los datos

	private static final long serialVersionUID = 1L;

	public DataUcoBetException(final String userMessage, final String technicalMessage, final Exception rootException) {
		super(userMessage, technicalMessage, rootException, Layer.DATA); // Cambia ya que no manda Layer
	}

	public static final DataUcoBetException crear(final String userMessage, final String technicalMessage,
			final Exception rootException) {
		return new DataUcoBetException(userMessage, technicalMessage, rootException);
	}

	public static final DataUcoBetException crear(final String userMessage) {
		return new DataUcoBetException(userMessage, userMessage, new Exception());
	}

	public static final DataUcoBetException crear(final String userMessage, final String technicalMessage) {
		return new DataUcoBetException(userMessage, technicalMessage, new Exception());
	}
	// Los hijos usan el "final", lo que da a entender que son datos de escritura
	// final
	// En caso del papa "UcoBetException", no puede tener el final ya que esta clase
	// evidentemente sobreescribe.
}
