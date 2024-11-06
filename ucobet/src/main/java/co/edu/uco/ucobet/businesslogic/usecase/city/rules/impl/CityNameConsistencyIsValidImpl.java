package co.edu.uco.ucobet.businesslogic.usecase.city.rules.impl;

import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.ucobet.businesslogic.usecase.city.rules.CityNameConsistencyIsValid;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcobetException;

public final class CityNameConsistencyIsValidImpl implements CityNameConsistencyIsValid{

	@Override
	public void execute(final String data) {
		validateNotNull(data);
		validateLen(data);
		validateFormat(data);
		
	}
	
	private void validateLen(final String data) {
		if (TextHelper.maxLenIsValid(data, 50)){
			var userMessage = "El nombre de la ciudad puede tener máximo 50 caracteres...";
			throw BusinessLogicUcobetException.crear(userMessage);
		}
	}
	
	private void validateFormat(final String data) {
		if (TextHelper.containOnlyLettersAndSpaces(data)) {
			var userMessage = "El nombre de la ciudad solo puede contener letras y espacios...";
			throw BusinessLogicUcobetException.crear(userMessage);
		}
	}
	
	private void validateNotNull(final String data) {
		if (TextHelper.isEmpty(data)) {
			var userMessage = "El nombre de la ciudad no puede estar vacío...";
			throw BusinessLogicUcobetException.crear(userMessage);
		}
	}
}
