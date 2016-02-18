package io;

import java.io.PrintStream;

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
	private boolean printed = false;
	private PrintStream out = System.out;

	/**
	 * Creates a new {@link PlyEchoHandler} which prints all the parsed
	 * information back on the standard output stream.
	 */
	public PlyEchoHandler() {
	}

	/**
	 * Prints a string in the given format and with the given arguments.
	 * 
	 * @param format
	 *            the format of the string.
	 * @param arguments
	 *            the arguments for the string.
	 */
	private void print(String format, Object... arguments) {
		if (!printed) {
			printed = true;
			out.print("ply");
		}
		out.format(format, arguments);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyFormat(core.Format, int, int)
	 */
	@Override
	public void plyHeaderFormat(Format format, int majorVersion,
			int minorVersion) {
		print("\nformat %s %d.%d", format.toString().toLowerCase(),
				majorVersion, minorVersion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyComment(java.lang.String)
	 */
	@Override
	public void plyHeaderComment(String comment) {
		print("\ncomment %s", comment);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyElement(core.Element)
	 */
	@Override
	public void plyElementDefinition(ElementDefinition element) {
		print("\n%s", element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyElement(java.lang.String)
	 */
	@Override
	public void plyElementStart(String elementName) {
		print("\n");
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
		print("%d ", value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyProperty(java.lang.String, java.lang.Double)
	 */
	@Override
	public void plyProperty(String propertyName, Double value) {
		print("%s ", value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyProperty(java.lang.String, java.lang.Long[])
	 */
	@Override
	public void plyProperty(String propertyName, Long... value) {
		print("%d ", value.length);
		for (Long v : value)
			System.out.format("%d ", v);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyProperty(java.lang.String, java.lang.Double[])
	 */
	@Override
	public void plyProperty(String propertyName, Double... value) {
		print("%d ", value.length);
		for (Double v : value)
			System.out.format("%s ", v);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyHeaderEnd()
	 */
	@Override
	public void plyHeaderEnd() {
		print("\nend_header");
	}
}
