package co.edu.uco.ucobet.businesslogic.usecase.state.rule.impl;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.ucobet.businesslogic.usecase.state.rule.StateExists;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcobetException;
import co.edu.uco.ucobet.data.dao.DAOFactory;
import co.edu.uco.ucobet.entity.StateEntity;

public final class StateExistsImpl implements StateExists {

	@Override
	public void execute(final UUID data, final DAOFactory factory) {
		var state = ObjectHelper.getDefault(factory.getStateDAO().findByID(data), new StateEntity());
		
		if (data.compareTo(state.getId()) != 0) {
			var userMessage = "No existe un departamento con el identificador " + data.toString();
			throw BusinessLogicUcobetException.crear(userMessage);
		}
		
	}

}
