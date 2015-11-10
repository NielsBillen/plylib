package core.datatype;

import io.ParseException;
import io.PlyHandler;

import java.util.Iterator;

import core.Element;
import core.Property;

/**
 * Represents a single precision floating point value.
 * 
 * @author Niels Billen
 * @version 1.0
 */
public class Float32 extends Scalar<Float> {
	/**
	 * The singleton instance of {@link Float32}.
	 */
	public static final Float32 FLOAT32 = new Float32();

	/**
	 * Creates a new singleton instance of this {@link Float32}.
	 */
	private Float32() {
	}

	/**
	 * Returns the singleton instance of {@link Float32}.
	 * 
	 * @return the singleton instance of {@link Float32}.
	 */
	public static Float32 getFloat32() {
		return FLOAT32;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.Scalar#getMinimumValue()
	 */
	@Override
	public Float getMinimumValue() {
		return -Float.MAX_VALUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.Scalar#getMaximumValue()
	 */
	@Override
	public Float getMaximumValue() {
		return Float.MAX_VALUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.DataType#nbOfBytes()
	 */
	@Override
	public int nbOfBytes() {
		return 4;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.DataType#toPLY()
	 */
	@Override
	public String toPLY() {
		return "float32";
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
		Float value = Float.parseFloat(token);
		handler.plyElement(element, property, value);
	}
}
