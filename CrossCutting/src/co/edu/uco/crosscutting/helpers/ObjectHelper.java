package co.edu.uco.crosscutting.helpers;

public class ObjectHelper {

	private ObjectHelper() {

	}

	public static <O> boolean isNull(final O Object) {
		return Object == null;

	}
	
	public static <O> O getDefault(final O Object,final O defaultObject) {
		return isNull(Object) ? defaultObject : Object;
					
		}		
}
