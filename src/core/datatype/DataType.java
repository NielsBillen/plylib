package core.datatype;

import java.util.Iterator;

import core.Element;
import core.Property;
import io.ParseException;
import io.PlyHandler;

/**
 * Represents a {@link DataType} in the PLY File Format.
 * 
 * @author Niels Billen
 * @version 0.1
 */
public abstract class DataType {
	/**
	 * Returns this {@link DataType} as a string in the PLY File Format.
	 * 
	 * @return this {@link DataType} as a string in the PLY File Format.
	 */
	public abstract String toPLY();

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
	public abstract void parse(Iterator<String> tokens, Element element,
			Property<DataType> property, PlyHandler handler)
			throws NullPointerException, ParseException;
}
