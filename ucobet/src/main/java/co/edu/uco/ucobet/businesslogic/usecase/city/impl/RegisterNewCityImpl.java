package co.edu.uco.ucobet.businesslogic.usecase.city.impl;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;
import co.edu.uco.ucobet.businesslogic.adapter.entity.CityEntityAdapter;
import co.edu.uco.ucobet.businesslogic.usecase.city.RegisterNewCity;
import co.edu.uco.ucobet.businesslogic.usecase.city.rules.CityNameConsistencyIsValid;
import co.edu.uco.ucobet.businesslogic.usecase.city.rules.CityNameDoesNotExistsForState;
import co.edu.uco.ucobet.businesslogic.usecase.city.rules.impl.CityNameConsistencyIsValidImpl;
import co.edu.uco.ucobet.businesslogic.usecase.city.rules.impl.CityNameDoesNotExistsForStateImpl;
import co.edu.uco.ucobet.businesslogic.usecase.state.rule.StateExists;
import co.edu.uco.ucobet.businesslogic.usecase.state.rule.impl.StateExistsImpl;
import co.edu.uco.ucobet.crosscutting.exceptions.DataUcoBetException;
import co.edu.uco.ucobet.data.dao.DAOFactory;
import co.edu.uco.ucobet.domain.CityDomain;

public final class RegisterNewCityImpl implements RegisterNewCity{
	
	private DAOFactory daoFactory;
	private CityNameDoesNotExistsForState cityNameDoesNotExistsForState = new CityNameDoesNotExistsForStateImpl();
	private StateExists stateExists = new StateExistsImpl();
	private CityNameConsistencyIsValid cityNameConsistencyIsValid = new CityNameConsistencyIsValidImpl();
	
	@Override
	public void execute(final CityDomain data) {
		cityNameConsistencyIsValid.execute(data.getName());
		cityNameDoesNotExistsForState.execute(data, daoFactory);
		stateExists.execute(data.getState().getId(), daoFactory);
		
		var cityDomainToMap = CityDomain.create(generateId(), data.getName(), data.getState());
		var cityEntity = CityEntityAdapter.getCityEntityAdapter().adaptSource(cityDomainToMap);
		daoFactory.getCityDAO().create(cityEntity);
	}
	
	private UUID generateId() {
		var id = UUIDHelper.generate();
		var cityEntity = daoFactory.getCityDAO().findByID(id);
		
		if(UUIDHelper.isEqual(cityEntity.getId(), id)) {
			id = generateId();
		}
		
		return id;
//		Generar el id ya sea que exista o no
	}
	
	private void setDAOFactory(final DAOFactory daoFactory) {
		if (ObjectHelper.isNull(daoFactory)) {
			var userMessage = "Se presentado un problema tratendo de realizar el registro de la nueva ciudad";
			var technicalMessage = "El DAOFactory requerido para crear la clase que actualiza la ciudad llego nula...";
			throw DataUcoBetException.crear(userMessage, technicalMessage);
		}
		
		this.daoFactory = daoFactory;	
	}
}
