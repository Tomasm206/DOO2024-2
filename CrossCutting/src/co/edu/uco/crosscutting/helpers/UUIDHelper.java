package co.edu.uco.crosscutting.helpers;

import java.util.UUID;

public final class UUIDHelper {

	private static final String DEFAULT_UUID_STRING = "00000000-0000-0000-0000-000000000000";
	private UUIDHelper() {
		super();
	}

	public static final UUID convertToUUID(final String uuidAsString) {
		return UUID.fromString(uuidAsString);
	}

	public static final UUID getDefault(final UUID value, final UUID defaultValue) {
		return ObjectHelper.getDefault(value, defaultValue);
	}

	public static final UUID getDefault() {
		return convertToUUID(DEFAULT_UUID_STRING);
	}

	public static final UUID generate() {
		return UUID.randomUUID();
	}

	public static final boolean isDefault(final UUID value) {
		return isEqual(value, getDefault());
//		return getDefault(value, getDefault()).equals(getDefault());
//		Elaboramos codigo mas verboso
	}

	public static final boolean isDefault(final String uuidAsString) {
//		return getDefault(convertToUUID(uuidAsString), getDefault()).equals(getDefault());
//		clean code (refactor)
		return isEqual(convertToUUID(uuidAsString), getDefault());
	}

	public static String getDefaultAsString() {
		return DEFAULT_UUID_STRING;
	}
	public static final boolean isEqual(final UUID valueOne, final UUID valueTwo) {
		return getDefault(valueOne,getDefault()).compareTo(getDefault(valueTwo,getDefault())) == 0;
	}
}