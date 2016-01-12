package core.datatype;

import io.PlyHandler;

import java.io.IOException;

import io.ParseException;

import util.PlyScanner;
import core.Format;

/**
 * Represents a {@link DataType} in the Ply File Format.
 * 
 * @author Niels Billen
 * @version 0.1
 */
public abstract class DataType<T> {
	/**
	 * Parses this {@link DataType} from the given scanner.
	 * 
	 * @param reader
	 *            the reader where the data is parsed from.
	 * @param format
	 *            the format the file is written in.
	 * @return
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws ParseException 
	 */
	public abstract T parse(PlyScanner reader, Format format)
			throws IOException, NumberFormatException, ParseException;

	/**
	 * 
	 * @param reader
	 * @param format
	 * @param propertyName
	 * @param handler
	 * @throws ParseException 
	 */
	public abstract void parseProperty(PlyScanner reader, Format format,
			String propertyName, PlyHandler handler) throws IOException,
			NumberFormatException, ParseException;

	/**
	 * Returns this {@link DataType} as a string in the PLY File Format.
	 * 
	 * @return this {@link DataType} as a string in the PLY File Format.
	 */
	public abstract String toPLY();

	/**
	 * 
	 * @param typeDefinition
	 * @return
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public static DataType<?> parseFromString(String... typeDefinition)
			throws NullPointerException, IllegalArgumentException {
		if (typeDefinition == null)
			throw new NullPointerException("the given type definition is null!");
		if (typeDefinition.length == 0)
			throw new IllegalArgumentException(
					"the length of the type definition must be longer than null!");

		if (typeDefinition[0].startsWith("list")) {
			if (typeDefinition.length != 3)
				throw new IllegalStateException(
						"missing type definitions for the size and content of a list");
			IntScalar sizeType = IntScalar.parseFromString(typeDefinition[1]);
			Scalar<?> dataType = Scalar.parseFromString(typeDefinition[2]);
			return new PlyList<>(sizeType, dataType);
		} else
			return Scalar.parseFromString(typeDefinition[0]);
	}
}
