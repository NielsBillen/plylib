package core.datatype;

import io.PlyHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.ParseException;

import util.PlyScanner;
import core.Format;

/**
 * Represents a datatype which is a {@link PlyList}.
 * 
 * @author Niels Billen
 * @version 0.1
 */
public class PlyList<T extends Comparable<T>> extends DataType<List<T>> {
	/**
	 * An integer scalar data type which indicates the amount of data in this
	 * {@link PlyList}.
	 */
	private final IntScalar size;

	/**
	 * The {@link Scalar} data type for the content of this {@link PlyList}.
	 */
	private final Scalar<T> dataType;

	/**
	 * Creates a new {@link PlyList}
	 * 
	 * @param sizeType
	 * @param dataType
	 */
	public PlyList(IntScalar sizeType, Scalar<T> dataType) {
		this.size = sizeType;
		this.dataType = dataType;
	}

	/**
	 * Return the {@link IntScalar} data type of the size of this
	 * {@link PlyList}.
	 * 
	 * @return the {@link IntScalar} data type of the size of this
	 *         {@link PlyList}.
	 */
	public IntScalar getSizeType() {
		return size;
	}

	/**
	 * Returns the {@link Scalar} data type of the data in this {@link PlyList}.
	 * 
	 * @return the {@link Scalar} data type of the data in this {@link PlyList}.
	 */
	public Scalar<T> getDataType() {
		return dataType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.DataType#toPLY()
	 */
	@Override
	public String toPLY() {
		return "list ".concat(size.toPLY()).concat(" ")
				.concat(dataType.toPLY());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.DataType#parse(util.PlyFileReader, core.Format)
	 */
	@Override
	public List<T> parse(PlyScanner reader, Format format)
			throws IOException, NumberFormatException, ParseException {
		long longSize = size.parse(reader, format);
		if (longSize < 0)
			throw new IllegalStateException(
					"the length of a list cannot be smaller than zero!");
		if (longSize >= Integer.MAX_VALUE)
			throw new IllegalStateException(
					"the length of the list exceeds the maximum length of a Java list!");
		int intSize = (int) longSize;

		List<T> result = new ArrayList<T>(intSize);
		for (int i = 0; i < intSize; ++i)
			result.add(dataType.parse(reader, format));
		return result;
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
			NumberFormatException, ParseException {
		long longSize = size.parse(reader, format);
		if (longSize < 0)
			throw new IllegalStateException(
					"the length of a list cannot be smaller than zero!");
		if (longSize >= Integer.MAX_VALUE)
			throw new IllegalStateException(
					"the length of the list exceeds the maximum length of a Java list!");
		int intSize = (int) longSize;

		dataType.parseListProperty(reader, propertyName, intSize, format,
				handler);
	}
}
