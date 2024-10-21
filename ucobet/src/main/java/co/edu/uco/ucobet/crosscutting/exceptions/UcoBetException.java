package co.edu.uco.ucobet.crosscutting.exceptions;

import co.edu.uco.crosscutting.exceptions.UcoAplicationException;
import co.edu.uco.crosscutting.exceptions.enums.Layer;

public class UcoBetException extends UcoAplicationException {

	private static final long serialVersionUID = 1L;

	public UcoBetException(final String userMessage, final String technicalMessage, final Exception rootException,
			final Layer layer) {
		super(userMessage, technicalMessage, rootException, layer);
	}

	public static UcoBetException crear(final String userMessage, final String technicalMessage,
			final Exception rootException, final Layer layer) {
		return new UcoBetException(userMessage, technicalMessage, rootException, layer);
	}
	// Cuando queremos mostrar mensajes con toda la informacion del sistema

	public static UcoBetException crear(final String userMessage) {
		return new UcoBetException(userMessage, userMessage, new Exception(), Layer.GENERAL);
	}
	// Que pasa cuando la app no envia todos los datos?
	// se realizan mas metodos con sobre carga, retornando valores null o por
	// defecto.

	public static UcoBetException crear(final String userMessage, final String technicalMessage) {
		return new UcoBetException(userMessage, technicalMessage, new Exception(), Layer.GENERAL);
	}
	// Sigue la misma logica
	
	// para que construir la base de excepciones?
	// identificar que puede suceder, como puede fallar el sistema.
}
