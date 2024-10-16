package co.edu.uco.ucobet.data.dao.impl.sql;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import co.edu.uco.crosscutting.exception.DataUcoBetException;
import co.edu.uco.ucobet.data.dao.CountryDAO;
import co.edu.uco.ucobet.entity.CountryEntity;

public class SqlDAO {
	//Good
	
	private Connection connection;
	
	protected SqlDAO (final Connection connection) {
		setConnection(connection);
	}
	
	
	
	protected Connection getConnection() {
		return connection;
	}

	private void setConnection(final Connection connection) {
		validateIfConnectionIsOpen(connection);		
		this.connection = connection;
	}
	
	private void validateIfConnectionIsOpen(final Connection connection) {
		if(!SqlConnectionHelper.connectionIsOpen(connection)) {
			var userMessage = "Se ha presentado un problema inesperado, tratando de llevar a cabo la operación deseada...";
			var technicalMessage = "No es posible crear un acceso a datos de tipo sql con una conexión nula o cerrada...";
			
			throw DataUcoBetException.crear(userMessage, technicalMessage);
		}
	}
}
