package co.edu.uco.ucobet.data.dao.impl.sql;

import java.sql.Connection;
import co.edu.uco.crosscutting.helpers.SqlConnectionHelper;
import co.edu.uco.ucobet.crosscutting.exceptions.DataUcoBetException;

public class SqlDAO {
	// Good

	private Connection connection;

	protected SqlDAO(final Connection connection) {
		setConnection(connection);
	}
	// Busca que unicamente los que heredan puedan acceder al setConection

	protected Connection getConnection() {
		return connection;
	}

	private void setConnection(final Connection connection) {
		SqlConnectionHelper.validateIfConnectionIsClosed(connection);
		this.connection = connection;
	}

	private void validateIfConnectionIsOpen(final Connection connection) {
		SqlConnectionHelper.validateIfConnectionIsClosed(connection);

//		if (!SqlConnectionHelper.connectionIsOpen(connection)) {
//			var userMessage = "Se ha presentado un problema inesperado, tratando de llevar a cabo la operación deseada...";
//			var technicalMessage = "No es posible crear un acceso a datos de tipo sql con una conexión nula o cerrada...";
//
//			throw DataUcoBetException.crear(userMessage, technicalMessage);
//		} // ************** se vuelve inecesario por la primera validacion **************
	}
	// validamos si la conexion esta cerrada "null"
	// seguido de eso, unicamente necesitamos conexiones abiertas
	// se crea un metodo que nos valide eso.
}
