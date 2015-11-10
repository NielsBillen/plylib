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
	 * 
	 * @param string
	 * @return
	 */
	public static Format parseFromString(String string) {
		if (string.equals("ascii"))
			return ASCII;
		else if (string.equals("binary_little_endian"))
			return BINARY_LITTLE_ENDIAN;
		else if (string.equals("binary_big_endian"))
			return BINARY_BIG_ENDIAN;
		throw new IllegalArgumentException("unrecognized format option \""
				+ string + "\"");
	}
}
