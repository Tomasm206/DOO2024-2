package co.edu.uco.ucobet.businesslogic.usecase.city;

import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcobetException;

public class CityNameConsistencyIsValidImpl implements CityNameConsistencyIsValid{

	@Override
	public void execute(final String data) {
		
		
	}
	
	private void validateLen(final String data) {
		if (!TextHelper.maxLenIsValid(data, 50)) {
			var userMessage = "El nombre de la ciudad puede contener máximo 50 carácteres....";
			throw BusinessLogicUcobetException.crear(userMessage);
		}
	}
	
	private void validateFormat(final String data) {
		if (!TextHelper.containOnlyLettersAndSpaces(data)) {
			var userMessage = "El nombre de la ciudad solo puede contener letras y espacios....";
			throw BusinessLogicUcobetException.crear(userMessage);
		}
	}
	
	private void validateNotNull(final String data) {
		if (!TextHelper.isEmpty(data)) {
			var userMessage = "El nombre de la ciudad puede estar vacio....";
			throw BusinessLogicUcobetException.crear(userMessage);
		}
	}
}
