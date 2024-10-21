package co.edu.uco.crosscutting.unittesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.uco.crosscutting.helpers.ObjectHelper;

class HelpersUnitTestCase {

	@Test
	void givenWantValidateIfObjectIsNullwhenObjectIsNullThenResultIsTrue() {
		//arrange: Initial Context 
		String object = null;
		
		//act
		boolean result = ObjectHelper.isNull(object);
		
		//Assert
		assertTrue(result);
		
	}
	@Test
	void givenWantValidateIfObjectIsNotNullwhenObjectIsNotNullThenResultIsFalse() {
		//arrange: Initial Context 
		var object = "";
		
		//act : execute action
		boolean result = ObjectHelper.isNull(object);
		
		//Assert validate result
		assertFalse(result);
   }
	@Test
	void givenWantGetOriginalValuewhenObjectIsNotNullThenResultIsTheSameObject() {
		//arrange: Initial Context 
		var object = "casa";
		var defaultValue = "Default";
		
		//act : execute action
		var result = ObjectHelper.getDefault(object, defaultValue);
		
		//Assert validate result
		assertEquals(object, result);
	}


@Test
void givenWantGetDefaultValuewhenObjectIsNotNullThenResultIsDefaultObject() {
	//arrange: Initial Context 
	String object = null;
	var defaultValue = "Default";
	
	//act : execute action
	var result = ObjectHelper.getDefault(object, defaultValue);
	
	//Assert validate result
	assertEquals(defaultValue, result);
  }
}