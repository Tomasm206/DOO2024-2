package co.edu.uco.crosscutting.helpers;

public class TextHelper {

	public static final String EMPTY ="";
	public static final String ONLY_LETTERS_SPACES = "[A-Za-záÁéÉíÍóÓúÚñÑ]"; 	
	
	private TextHelper() {
		
	}
	
	public static boolean patternIsValid(final String string, final String pattern) {
//		return applyTrim(string).matches("^".concat(pattern).concat("$"));
		return applyTrim(string).matches(concat("^",pattern,"$"));
	}
	
	public static boolean containOnlyLettersAndSpaces(final String string) {
		return patternIsValid(string, ONLY_LETTERS_SPACES);
	}
	
	public static String concat(final String... string) {
		var sb = new StringBuilder(EMPTY);
		
		for(String string1 : string) {
			sb.append(getDefault(string1));
		}
		
		return sb.toString();
	}
	
	public static boolean isNull(final String string) {
		return ObjectHelper.isNull(string);
	}
//	public static String getDefault(final String string, String string) {
//		return ObjectHelper.getDefault(string, EMPTY);
//	}
//	
	///////////////////
	
	public static String getDefault(final String string, final String  defaultValue) {
		return ObjectHelper.getDefault(string, defaultValue);
	}
	
	public static String getDefault (final String string) {
		return getDefault(string);
		
	}
	
	///////////////////
	
	public static boolean isEmpty(final String string) {
		return EMPTY.equals(getDefault(string, null));
	}
	public static boolean isEmptyapplyingTrim(final String string) {
		return isEmpty(applyTrim(string));
	}
	public static String applyTrim(final String string) {
		return getDefault(string, null).trim();
	}
	public static int len(final String string) {
		return applyTrim(string).length();
	}
	public static boolean lenIsValid(final String string, final int min, final int max) {
		return minLenIsValid(string,min) && maxLenIsValid(string,max);
	}
	public static boolean minLenIsValid(final String string, final int min) {
		return NumericHelper.isGreatOrEqual(len(string), min);
	}
	public static boolean maxLenIsValid(final String string, final int max) {
		return NumericHelper.isLessOrEqual(len(string), max);
	}
//	buscamos verificar los rangos de caracteres de lpos textos
}
