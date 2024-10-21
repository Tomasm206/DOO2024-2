package co.edu.uco.crosscutting.helpers;

public class TextHelper {

	public static final String EMPTY ="";
	private TextHelper() {
		
	}
	public static boolean isNull(final String string) {
		return ObjectHelper.isNull(string);
	}
	public static String getDefault(final String string, String string2) {
		return ObjectHelper.getDefault(string, EMPTY);
	}
	
	public static boolean isEmpty(final String string) {
		return EMPTY.equals(getDefault(string, null));
	}
	public static boolean isEmptyapplyingTrim(final String string) {
		return isEmpty(applyTrim(string));
	}
	public static String applyTrim(final String string) {
		return getDefault(string, null).trim();
	}
}
