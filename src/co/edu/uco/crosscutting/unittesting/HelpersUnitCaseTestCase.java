package co.edu.uco.crosscutting.unittesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.uco.crosscutting.helpers.ObjectHelper;

class HelpersUnitTestCase {

	@Test
	void givenWantValidateObjectIsNullWhenObjectIsNullThenResultIsTrue() {
		//Arrange: Initial Context
		String object = null;
		//Act: Execute action
		boolean result = ObjectHelper.isNull(object);
		//Assert
		assertTrue(result);
	}

	@Test
	void givenWantValidateObjectIsNotNullWhenObjectIsNotNullThenResultIsFalse() {
		//Arrange
		var object = "";
		//Act
		boolean result = ObjectHelper.isNull(object);
		//Assert
		assertFalse(result);
	}
	@Test
	void givenWantGetOriginalValidateObjectIsNotNullWhenObjectIsNotNullThenResultIsFalse() {
		//Arrange
		var object = "";
		
		//Act
		boolean result = ObjectHelper.isNull(object);
		//Assert
		assertFalse(result);
	}
	
}