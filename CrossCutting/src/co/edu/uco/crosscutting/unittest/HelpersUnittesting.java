package co.edu.uco.crosscutting.unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.uco.crosscutting.helpers.ObjectHelper;

class HelpersUnittesting {

	@Test
	void givenWantValidateIfObjectIsNullWhenObjectIsNullThenResultsIsFalse() {
		// Arrange: Initial Context
		var Object="";
	
		//Act
		boolean result = ObjectHelper.isNull(Object);
		
		//Assert
	
		assertFalse(result);
	}
	@Test
	void givenWantValidateIfObjectIsNotNullWhenObjectIsNullThenResultsIsTrue() {
		// Arrange: Initial Context
		String Object=null;
		
	    //Act
		boolean result = ObjectHelper.isNull(Object);
		
		//Assert
	
		assertTrue(result);
	}
	
	@Test
	void givenWantGetOriginalWhenObjectIsNullThenResultsIsTheSameObject() {
		// Arrange: Initial Context
		var Object = "Casa";
		var DefaultValue = "Default";
		
	    //Act
		var result = ObjectHelper.getDefault(Object, DefaultValue);
		
		//Assert
		assertEquals(Object, result);
	}
	@Test
	void givenWantGetDefaultValueWhenObjectIsNullThenResultsIsDefaultObject() {
		// Arrange: Initial Context
		String object = null;
		var DefaultValue = "Default";
		
	    //Act
		var result = ObjectHelper.getDefault(object, DefaultValue);
		
		//Assert
		assertgetDefault(object, result);
	}
	private void assertgetDefault(String object, String result) {
		// TODO Auto-generated method stub
		
	}
}