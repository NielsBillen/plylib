package core.datatype;

import io.PlyHandler;

import java.io.IOException;

import util.PlyScanner;

import io.ParseException;

import core.Format;

/**
 * Abstract class representing all integer scalar types in the PLY File Format.
 * 
 * @author Niels Billen
 * @version 0.1
 */
public abstract class IntScalar extends Scalar<Long> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.DataType#parse(java.util.Scanner, core.Format)
	 */
	@Override
	public Long parse(PlyScanner scanner, Format format) throws IOException,
			NumberFormatException, ParseException {
		if (format.equals(Format.ASCII)) {
			String token = scanner.next();
			if (token == null)
				throw new ParseException(
						"unexpected end of file while parsing datatype '" + toPLY()
								+ "'");
			return Long.parseLong(token);
		}
		throw new IllegalStateException("unsupported format " + format);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.DataType#parseProperty(java.util.PlyFileReader,
	 * core.Format, java.lang.String, io.PlyHandler)
	 */
	@Override
	public void parseProperty(PlyScanner scanner, Format format,
			String propertyName, PlyHandler handler) throws IOException,
			NumberFormatException, ParseException {
		handler.plyProperty(propertyName, parse(scanner, format));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.Scalar#parseListProperty(java.util.PlyFileReader,
	 * java.lang.String, int, core.Format, io.PlyHandler)
	 */
	@Override
	public void parseListProperty(PlyScanner scanner, String propertyName,
			int size, Format format, PlyHandler handler) throws IOException,
			NumberFormatException, ParseException {
		Long[] result = new Long[size];
		for (int i = 0; i < size; ++i)
			result[i] = parse(scanner, format);
		handler.plyProperty(propertyName, result);
	}

	/**
	 * Returns the {@link IntScalar} which matches thg given string.
	 * 
	 * @param datatype
	 * @return
	 */
	public static IntScalar parseFromString(String scalarType) {
		if (scalarType == null)
			throw new NullPointerException("the given type definition is null!");
		if (scalarType.equals("int8") || scalarType.equals("char"))
			return Int8.INT8;
		else if (scalarType.equals("int16") || scalarType.equals("short"))
			return Int16.INT16;
		else if (scalarType.equals("int32") || scalarType.equals("int"))
			return Int32.INT32;
		else if (scalarType.equals("uint8") || scalarType.equals("uchar"))
			return UInt8.UINT8;
		else if (scalarType.equals("uint16") || scalarType.equals("short"))
			return UInt16.UINT16;
		else if (scalarType.equals("uint32") || scalarType.equals("uint"))
			return UInt32.UINT32;
		throw new IllegalArgumentException(
				String.format(
						"could not find an integer scalar type corresponding to \"%s\"",
						scalarType));
	}
}
