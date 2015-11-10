package core.datatype;

import io.ParseException;
import io.PlyHandler;

import java.util.Iterator;

import core.Element;
import core.Property;

/**
 * Represents a datatype which is a {@link List}.
 * 
 * @author Niels Billen
 * @version 0.1
 */
public class List<T extends Comparable<T>> extends DataType {
	/**
	 * An integer scalar data type which indicates the amount of data in this
	 * {@link List}.
	 */
	private final IntScalar size;

	/**
	 * The {@link Scalar} data type for the content of this {@link List}.
	 */
	private final Scalar<T> dataType;

	/**
	 * Creates a new {@link List}
	 * 
	 * @param sizeType
	 * @param dataType
	 */
	public List(IntScalar sizeType, Scalar<T> dataType) {
		this.size = sizeType;
		this.dataType = dataType;
	}

	/**
	 * Return the {@link IntScalar} data type of the size of this {@link List}.
	 * 
	 * @return the {@link IntScalar} data type of the size of this {@link List}.
	 */
	public IntScalar getSizeType() {
		return size;
	}

	/**
	 * Returns the {@link Scalar} data type of the data in this {@link List}.
	 * 
	 * @return the {@link Scalar} data type of the data in this {@link List}.
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
	 * @see core.datatype.DataType#parse(java.util.Iterator, core.Element,
	 * core.Property, io.PlyHandler)
	 */
	@Override
	public void parse(Iterator<String> tokens, Element element,
			Property property, PlyHandler handler) throws NullPointerException,
			ParseException {
		if (!tokens.hasNext())
			throw new ParseException(
					"no more data on this line to parse the property \""
							+ property.getName() + "\" of element \""
							+ element.getName() + "\"");
		String token = tokens.next();
		Double value = Double.parseDouble(token);
		handler.plyElement(element, property, value);
	}
}
