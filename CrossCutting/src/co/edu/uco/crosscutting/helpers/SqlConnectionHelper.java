package co.edu.uco.crosscutting.helpers;

import java.sql.Connection;
import java.sql.SQLException;

import co.edu.uco.crosscutting.exceptions.UcoAplicationException;
import co.edu.uco.crosscutting.exceptions.enums.Layer;

public class SqlConnectionHelper {
	private SqlConnectionHelper() {

	}

	// Buscamos validar las conexiones al servidor
	public static boolean conectionIsNull(final Connection connection) {
		return ObjectHelper.isNull(connection);
	}

	public static boolean connectionIsOpen(final Connection connection) {
		try {
			return !conectionIsNull(connection) && !connection.isClosed();
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema inesperado" 
					+ " tratando de llevar a cabo la operacion deseada...";
			var technicalMessage = "Se ha presentado un problema de tipo SQLException "
					+ "tratando de llevar a cabo la validacion de si la conexion "
					+ "estaba o no abierta. Por favor revise el log de errores para tener "
					+ "mas detalles del error presentado...";
			throw new UcoAplicationException(userMessage, technicalMessage, exception, Layer.DATA);
		}
	}
	// inicialmente se plantea lo que está en el try
	// cambia porque puede lanzar un SQLException.
	// no es recomendable usar "throw exception;"
	// Se lanza un mensaje en lenguaje obvicuo
}
