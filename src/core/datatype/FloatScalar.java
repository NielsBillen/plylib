package core.datatype;

import io.PlyHandler;

import java.io.IOException;

import util.PlyScanner;
import core.Format;

/**
 * Abstract class representing all integer scalar types in the PLY File Format.
 * 
 * @author Niels Billen
 * @version 0.1
 */
public abstract class FloatScalar extends Scalar<Double> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.DataType#parse(util.PlyFileReader, core.Format)
	 */
	@Override
	public Double parse(PlyScanner reader, Format format)
			throws IOException, NumberFormatException {
		if (format.equals(Format.ASCII)) {
			return Double.parseDouble(reader.next());
		}
		throw new IllegalStateException("unsupported format " + format);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.DataType#parseProperty(util.PlyFileReader,
	 * core.Format, java.lang.String, io.PlyHandler)
	 */
	@Override
	public void parseProperty(PlyScanner reader, Format format,
			String propertyName, PlyHandler handler) throws IOException,
			NumberFormatException {
		handler.plyProperty(propertyName, parse(reader, format));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.Scalar#parseListProperty(util.PlyFileReader,
	 * java.lang.String, int, core.Format, io.PlyHandler)
	 */
	@Override
	public void parseListProperty(PlyScanner reader, String propertyName,
			int size, Format format, PlyHandler handler) throws IOException,
			NumberFormatException {
		Double[] result = new Double[size];
		for (int i = 0; i < size; ++i)
			result[size] = parse(reader, format);
		handler.plyProperty(propertyName, result);
	}

	/**
	 * 
	 * @param datatype
	 * @return
	 */
	public static FloatScalar parseFromString(String scalarType) {
		if (scalarType == null)
			throw new NullPointerException("the given type definition is null!");
		if (scalarType.equals("float32") || scalarType.equals("float"))
			return Float32.FLOAT32;
		else if (scalarType.equals("float64"))
			return Float64.FLOAT64;
		throw new IllegalArgumentException(String.format(
				"could not find an float scalar type corresponding to \"%s\"",
				scalarType));
	}
}
