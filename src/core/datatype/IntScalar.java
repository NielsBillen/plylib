package core.datatype;

import java.util.Iterator;

import io.ParseException;
import io.PlyHandler;
import core.Element;
import core.Property;

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
	 * @see core.datatype.DataType#parse(java.util.Iterator, core.Element,
	 * core.Property, io.PlyHandler)
	 */
	@Override
	public void parse(Iterator<String> tokens, Element element,
			Property<IntScalar> property, PlyHandler handler) throws NullPointerException,
			ParseException {
		if (!tokens.hasNext())
			throw new ParseException(
					"no more data on this line to parse the property \""
							+ property.getName() + "\" of element \""
							+ element.getName() + "\"");
		String token = tokens.next();
		Long value = Long.parseLong(token);
		handler.plyElement(element, property, value);
	}
}
