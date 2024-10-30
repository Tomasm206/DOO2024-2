package co.edu.uco.ucobet.businesslogic.usecase.city.rules.impl;

import co.edu.uco.ucobet.businesslogic.usecase.city.impl.CityNameDoesNotExistsForState;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcobetException;
import co.edu.uco.ucobet.data.dao.DAOFactory;
import co.edu.uco.ucobet.domain.CityDomain;
import co.edu.uco.ucobet.entity.CityEntity;
import co.edu.uco.ucobet.entity.StateEntity;

public class CityNameDoesNotExistsForStateImpl implements CityNameDoesNotExistsForState{

	@Override
	public void execute(final CityDomain data, final DAOFactory factory) {
		
		final var city = new CityEntity();
		city.setName(data.getName());
		
		final var state = new StateEntity();
		state.setId(data.getState().getId());
		
		city.setState(state);
		
		var results = factory.getCityDAO().findByFilter(city);
		
		if (!results.isEmpty()) {
			var userMessage = "Ya existe una ciudad llamada " + results.get(0).getName() + " para el departamento" + results.get(0).getState();
			throw BusinessLogicUcobetException.crear(userMessage);
		}
	}
}
