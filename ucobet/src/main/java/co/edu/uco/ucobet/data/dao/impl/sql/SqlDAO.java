package co.edu.uco.ucobet.data.dao.impl.sql;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import co.edu.uco.crosscutting.helpers.SqlConnectionHelper;
import co.edu.uco.ucobet.crosscutting.exceptions.DataUcoBetException;
import co.edu.uco.ucobet.data.dao.CountryDAO;
import co.edu.uco.ucobet.entity.CountryEntity;

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
		validateIfConnectionIsOpen(connection);
		this.connection = connection;
	}

	private void validateIfConnectionIsOpen(final Connection connection) {
		if (!SqlConnectionHelper.connectionIsOpen(connection)) {
			var userMessage = "Se ha presentado un problema inesperado, tratando de llevar a cabo la operación deseada...";
			var technicalMessage = "No es posible crear un acceso a datos de tipo sql con una conexión nula o cerrada...";

			throw DataUcoBetException.crear(userMessage, technicalMessage);
		}
	}
	// validamos si la conexion esta cerrada "null"
	// seguido de eso, unicamente necesitamos conexiones abiertas
	// se crea un metodo que nos valide eso.
}
