package co.edu.uco.ucobet.data.dao.impl.sql.sqlserver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import co.edu.uco.crosscutting.exception.DataUcoBetException;
import co.edu.uco.ucobet.data.dao.CountryDAO;
import co.edu.uco.ucobet.data.dao.impl.sql.SqlDAO;
import co.edu.uco.ucobet.entity.CountryEntity;

public class CountrySqlServerDAO extends SqlDAO implements CountryDAO {

	protected CountrySqlServerDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	public CountryEntity findById(final UUID id) {
		
	}
	
	@Override
	public CountryEntity findByID(UUID id) {
		var countryEntityFilter = new CountryEntity();
		countryEntityFilter.setId(id);
		
		var result = findByFilter(countryEntityFilter)
		
		return (result.isEmpty()) ? new CountryEntity() : result.get(0);
	}

	@Override
	public List<CountryEntity> findAll() {
		return findByFilter(new CountryEntity());
	}

	@Override
	public List<CountryEntity> findByFilter(final CountryEntity filter) {
		final var statement = new StringBuilder();
		final var parameters = new ArrayList<>();
		final var resulSelect = new ArrayList<CountryEntity>(); //Valor de los resultados almacenados
		final var statementWasPrepared = false;
		final var resultWasExecuted = false;
		// Select
		createSelect(statement);
		// From
		createFrom(statement);
		// Where
		createWhere(statement,filter,parameters);
		// Order By
		createOrderBy(statement);
		
		try (final var preparedStatement 
				= getConnection()).prepareStatement(statement.toString()){
			for (int arrayIndex = 0; arrayIndex < parameters.size(); index++) {
				var statementIndex = arrayIndex = arrayIndex;
				preparedStatement.setObject(statementIndex, parameters.get(arrayIndex));
				
			}
			
			statementWasPrepared = true;
			
			while(result.next()) {
				var countryEntityTmp = new CountryEntity();
				countryEntityTmp.setId(UUIDHelper.convertToUUID("id"));
				countryEntityTmp.setName(result.getNString("name"));
				
				resulSelect.add(countryEntityTmp);
			} //Lo ejecunta tanto resultados tenga
			
		} catch (final SQLEException exception) {
			var userMessage = "Por favor intente de nuevo y si el problema persiste ";
			var technicalMessage = "Se ha presentado un problema al tratar de consultar la informacion de los paises en el filtro deseado en la base de datps SQL server, porfavor valide el log de errores";
			
			throw DataUcoBetException.crear(userMessage, technicalMessage, exception); 
		}
		
		
		return resulSelect;
	}
	
	private void createSelect(final StringBuilder statement) {
		statement.append("SELECT id,name ");
	}
	
	private void createFrom(final StringBuilder statement) {
		statement.append("FROM Country ");
	}
	
	private void createOrderBy(final StringBuilder statement) {
		statement.append("ORDER BY name ASC ");
	}
	
	private void createWhere(final StringBuilder statement,
							final CountryEntity filter,
							final List<Object> parameters) {
		if (!ObjectHelper.isNull(filter)) {
			if (UUIDHelper.isDefault(filter.getId())) {
				statement.append("WHERE id = ? ");
			}
			
			if(!TextHelper.isEmptyApplyingTrim(filter.getName())) {
				statement.append("WHERE ");
				statement.append("NAME = ? ");
				parameters.add(filter.getName());
			}
		}
		statement.append("ORDER BY name ASC ");
	}
}
