package core;

import io.ParseException;
import io.PlyHandler;

import java.util.Iterator;

import core.datatype.DataType;

/**
 * Represents a property of a PLY mesh.
 * 
 * @author Niels Billen
 * @version 0.1
 */
public class Property<T extends DataType>{
	private String name;
	private T dataType;

	/**
	 * Creates a new {@link Property} which has the given name and datatype.
	 * 
	 * @param name
	 *            the name for this property.
	 * @param dataType
	 *            the data type for this property.
	 * @throws NullPointerException
	 */
	public Property(String name, T dataType) throws NullPointerException {
		this.name = name;
		this.dataType = dataType;
	}

	/**
	 * Returns the name of this {@link Property}.
	 * 
	 * @return the name of this {@link Property}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns this {@link Element} as a string in PLY format.
	 * 
	 * @return this {@link Element} as a string in PLY format.
	 */
	public String toPLY() {
		return "property ".concat(dataType.toPLY()).concat(" ")
				.concat(getName());
	}

	/**
	 * Parses the value of this {@link Property} and notifies the
	 * {@link PlyHandler}.
	 * 
	 * @param tokens
	 *            the tokens to parse this {@link Property} from.
	 * @param element
	 *            the element to which this property belongs.
	 * @throws NullPointerException
	 *             when given {@link Iterable} is null.
	 * @throws NullPointerException
	 *             when one of the tokens supplied by the iterable is null.
	 * @throws ParseException
	 *             when the property cannot be parsed from the given set of
	 *             tokens.
	 */
	public void parse(Iterator<String> tokens, Element element,
			PlyHandler handler) throws NullPointerException, ParseException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return toPLY();
	}
}
