package core;

/**
 * The possible format in which the PLY File can be formatted.
 * 
 * @author Niels Billen
 * @version 1.0
 */
public enum Format {
	BINARY_LITTLE_ENDIAN, BINARY_BIG_ENDIAN, ASCII;

	/**
	 * Returns the {@link Format} which corresponds to the given string.
	 * 
	 * @param format
	 *            the string to parse the {@link Format} from.
	 * @throws IllegalArgumentException
	 *             when no {@link Format} corresponds to the given string.
	 * @return the {@link Format} corresponding to the given string.
	 */
	public static Format parseFromString(String format)
			throws IllegalArgumentException {
		if (format.equals("ascii"))
			return ASCII;
		else if (format.equals("binary_little_endian"))
			return BINARY_LITTLE_ENDIAN;
		else if (format.equals("binary_big_endian"))
			return BINARY_BIG_ENDIAN;
		throw new IllegalArgumentException(String.format(
				"no format corresponds to the given input \"%s\"", format));
	}
}
