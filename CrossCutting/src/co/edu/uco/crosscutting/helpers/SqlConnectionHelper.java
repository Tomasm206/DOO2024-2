package co.edu.uco.crosscutting.helpers;

import java.sql.Connection;
import java.sql.SQLException;

import co.edu.uco.crosscutting.exceptions.UcoApplicationException;
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
			throw new UcoApplicationException(userMessage, technicalMessage, exception, Layer.DATA);
		}
	}
	// inicialmente se plantea lo que está en el try
	// cambia porque puede lanzar un SQLException.
	// no es recomendable usar "throw exception;"
	// Se lanza un mensaje en lenguaje obvicuo
	
	public static void initTransaction(final Connection connection) {

		validateIfConnectionIsClosed(connection);

		try { //Comprobar si hay conexion abierta

			if (!connection.getAutoCommit()) {
				var userMessage = "Se ha presentado un problema inesperado, tratando de llevar a cabo la operación deseada...";
				var technicalMessage = "No es posible iniciar una transacción que ya ha sido iniciada previamente en la base de datos SQL deseada...";
				throw new UcoApplicationException(userMessage, technicalMessage, new Exception(), Layer.DATA);
			}

			connection.setAutoCommit(false);
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada...";
			var technicalMessage = "Se ha presentado una excepción de tipo SQLException tratando de iniciar la transacción. Por favor revise el log de errores para tener más detalles del error presentado...";
			throw new UcoApplicationException(userMessage, technicalMessage, exception, Layer.DATA);
		}
	}
	
	public static void commitTransaction(final Connection connection) {

		validateIfConnectionIsClosed(connection);
		validateIfTransactionWasNotInitiated(connection);

		try {
			connection.commit();
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada...";
			var technicalMessage = "Se ha presentado una excepción de tipo SQLException tratando de confirmar la transacción. Por favor revise el log de errores para tener más detalles del error presentado...";
			throw new UcoApplicationException(userMessage, technicalMessage, exception, Layer.DATA);
		}
	}
//	
	public static void rollbackTransaction(final Connection connection) {

		validateIfConnectionIsClosed(connection);
		validateIfTransactionWasNotInitiated(connection);

		try {
			connection.rollback();
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada...";
			var technicalMessage = "Se ha presentado una excepción de tipo SQLException tratando de cancelar la transacción. Por favor revise el log de errores para tener más detalles del error presentado...";
			throw new UcoApplicationException(userMessage, technicalMessage, exception, Layer.DATA);
		}
	}

	public static void validateIfConnectionIsOpen(final Connection connection) {
		if (SqlConnectionHelper.connectionIsOpen(connection)) {
			var userMessage = "Se ha presentado un problema inesperado, tratando de llevar a cabo la operación deseada...";
			var technicalMessage = "No es posible tratar de abrir una conexión hacia la base de datos SQL que ya está abierta...";
			throw new UcoApplicationException(userMessage, technicalMessage, new Exception(), Layer.DATA);
		}
	}
//	Buscar validar si la conexion está abierta, asi lograr la reutilizacion

	public static void validateIfConnectionIsClosed(final Connection connection) {
		if (!SqlConnectionHelper.connectionIsOpen(connection)) {
			var userMessage = "Se ha presentado un problema inesperado, tratando de llevar a cabo la operación deseada...";
			var technicalMessage = "La conexión hacia la base de datos SQL está cerrada. Por tanto no es posible llevar a cabo la operación deseada...";
			throw new UcoApplicationException(userMessage, technicalMessage, new Exception(), Layer.DATA);
		}
	}

	public static void validateIfTransactionWasNotInitiated(final Connection connection) {
		try {
			if (connection.getAutoCommit()) {
				var userMessage = "Se ha presentado un problema inesperado, tratando de llevar a cabo la operación deseada...";
				var technicalMessage = "La transacción no ha sido iniciada previamente para llevar a cabo la operación deseada en la base de datos SQL deseada...";
				throw new UcoApplicationException(userMessage, technicalMessage, new Exception(), Layer.DATA);
			}
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada...";
			var technicalMessage = "Se ha presentado una excepción de tipo SQLException tratando de validar si la transacción fue iniciada con la fuente de datos SQL deseada. Por favor revise el log de errores para tener más detalles del error presentado...";
			throw new UcoApplicationException(userMessage, technicalMessage, exception, Layer.DATA);
		}
	}

	public static void closeConnection(final Connection connection) {

		validateIfConnectionIsClosed(connection);

		try {
			connection.close();
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada...";
			var technicalMessage = "Se ha presentado una excepción de tipo SQLException tratando de cerrar la conexión con la fuente de datos SQL deseada. Por favor revise el log de errores para tener más detalles del error presentado...";
			throw new UcoApplicationException(userMessage, technicalMessage, exception, Layer.DATA);
		}
	}
//	
	public static Connection openConnection(final String connectionString) {

		try {
			return DriverManager.getConnection(connectionString);
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada...";
			var technicalMessage = "Se ha presentado una excepción de tipo SQLException tratando de obtener la conexión con la fuente de datos SQL deseada. Por favor revise el log de errores para tener más detalles del error presentado...";
			throw new UcoApplicationException(userMessage, technicalMessage, exception, Layer.DATA);
		}
	}
}
