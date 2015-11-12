package core;

import io.ParseException;
import io.PlyHandler;

import java.io.IOException;

import util.PlyScanner;
import core.datatype.DataType;

/**
 * Represents a property of a PLY mesh.
 * 
 * @author Niels Billen
 * @version 0.1
 * @param <V>
 */
public class PropertyDefinition {
	private String name;
	private DataType<?> dataType;

	/**
	 * Creates a new {@link PropertyDefinition} which has the given name and
	 * datatype.
	 * 
	 * @param name
	 *            the name for this property.
	 * @param dataType
	 *            the data type for this property.
	 * @throws NullPointerException
	 */
	public PropertyDefinition(String name, DataType<?> dataType)
			throws NullPointerException {
		this.name = name;
		this.dataType = dataType;
	}

	/**
	 * Returns the name of this {@link PropertyDefinition}.
	 * 
	 * @return the name of this {@link PropertyDefinition}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns this {@link ElementDefinition} as a string in PLY format.
	 * 
	 * @return this {@link ElementDefinition} as a string in PLY format.
	 */
	public String toPLY() {
		return "property ".concat(dataType.toPLY()).concat(" ")
				.concat(getName());
	}

	/**
	 * 
	 * @param reader
	 * @param format
	 * @param handler
	 * @throws NullPointerException
	 * @throws ParseException
	 */
	public void parse(PlyScanner reader, Format format, PlyHandler handler)
			throws NullPointerException, ParseException, IOException,
			NumberFormatException {
		dataType.parseProperty(reader, format, getName(), handler);

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
