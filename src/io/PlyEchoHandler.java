package io;

import core.ElementDefinition;
import core.Format;

/**
 * Implementation of a {@link PlyHandler} which outputs all content back on the
 * standard output.
 * 
 * @author Niels Billen
 * @version 0.1
 */
public class PlyEchoHandler implements PlyHandler {
	/**
	 * 
	 */
	public PlyEchoHandler() {
		System.out.print("ply");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyFormat(core.Format, int, int)
	 */
	@Override
	public void plyHeaderFormat(Format format, int majorVersion,
			int minorVersion) {
		System.out.format("\nformat %s %d.%d", format.toString().toLowerCase(),
				majorVersion, minorVersion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyComment(java.lang.String)
	 */
	@Override
	public void plyHeaderComment(String comment) {
		System.out.format("\ncomment %s", comment);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyElement(core.Element)
	 */
	@Override
	public void plyElementDefinition(ElementDefinition element) {
		System.out.format("\n%s", element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyElement(java.lang.String)
	 */
	@Override
	public void plyElementStart(String elementName) {
		System.out.println();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyElementEnd()
	 */
	@Override
	public void plyElementEnd() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyProperty(java.lang.String, java.lang.Long)
	 */
	@Override
	public void plyProperty(String propertyName, Long value) {
		System.out.format("%d ", value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyProperty(java.lang.String, java.lang.Double)
	 */
	@Override
	public void plyProperty(String propertyName, Double value) {
		System.out.format("%f ", value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyProperty(java.lang.String, java.lang.Long[])
	 */
	@Override
	public void plyProperty(String propertyName, Long... value) {
		System.out.format("%d", value.length);
		for (Long v : value)
			System.out.format(" %d", v);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyProperty(java.lang.String, java.lang.Double[])
	 */
	@Override
	public void plyProperty(String propertyName, Double... value) {
		System.out.format("%d", value.length);
		for (Double v : value)
			System.out.format(" %f", v);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyHeaderEnd()
	 */
	@Override
	public void plyHeaderEnd() {
		System.out.print("\nend_header");
	}
}
