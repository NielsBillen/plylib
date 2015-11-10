package core.datatype;

import java.io.IOException;
import java.nio.ByteOrder;

import util.PlyScanner;
import core.Format;

/**
 * Represents a double precision float.
 * 
 * @author Niels Billen
 * @version 1.0
 */
public class Float64 extends FloatScalar {
	/**
	 * The singleton instance of {@link Float64}.
	 */
	public static final Float64 FLOAT64 = new Float64();

	/**
	 * Creates a new singleton instance of this {@link UInt16}.
	 */
	private Float64() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.FloatScalar#parse(util.PlyScanner, core.Format)
	 */
	@Override
	public Double parse(PlyScanner reader, Format format) throws IOException,
			NumberFormatException {
		if (format.equals(Format.BINARY_LITTLE_ENDIAN))
			return reader.nextDouble(ByteOrder.LITTLE_ENDIAN);
		else if (format.equals(Format.BINARY_BIG_ENDIAN))
			return reader.nextDouble(ByteOrder.BIG_ENDIAN);
		else
			return super.parse(reader, format);
	}

	/**
	 * Returns the singleton instance of {@link Float64}.
	 * 
	 * @return the singleton instance of {@link Float64}.
	 */
	public static Float64 getFloat64() {
		return FLOAT64;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.Scalar#getMinimumValue()
	 */
	@Override
	public Double getMinimumValue() {
		return -Double.MAX_VALUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.Scalar#getMaximumValue()
	 */
	@Override
	public Double getMaximumValue() {
		return Double.MAX_VALUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.DataType#nbOfBytes()
	 */
	@Override
	public int nbOfBytes() {
		return 8;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.DataType#toPLY()
	 */
	@Override
	public String toPLY() {
		return "float64";
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see core.datatype.DataType#parse(java.util.Iterator, core.Element,
	// * core.Property, io.PlyHandler)
	// */
	// @Override
	// public void parse(Iterator<String> tokens, Element element,
	// Property property, PlyHandler handler) throws NullPointerException,
	// ParseException {
	// if (!tokens.hasNext())
	// throw new ParseException(
	// "no more data on this line to parse the property \""
	// + property.getName() + "\" of element \""
	// + element.getName() + "\"");
	// String token = tokens.next();
	// Double value = Double.parseDouble(token);
	// handler.plyElement(element, property, value);
	// }
}
