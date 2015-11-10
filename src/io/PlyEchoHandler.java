package io;

import core.Element;
import core.Format;
import core.Property;
import core.datatype.DataType;

/**
 * Implementation of a {@link PlyHandler} which outputs all content back on the
 * standard output.
 * 
 * @author Niels Billen
 * @version 0.1
 */
public class PlyEchoHandler implements PlyHandler {
	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyFormat(core.Format, int, int)
	 */
	@Override
	public void plyHeaderFormat(Format format, int majorVersion,
			int minorVersion) {
		System.out.format("format %s %d.%d\n", format.toString().toLowerCase(),
				majorVersion, minorVersion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyComment(java.lang.String)
	 */
	@Override
	public void plyHeaderComment(String comment) {
		System.out.format("comment %s\n", comment);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyElement(core.Element)
	 */
	@Override
	public void plyHeaderElement(Element element) {
		System.out.println(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.PlyHandler#plyElement(core.Element, core.Property,
	 * core.datatype.DataType)
	 */
	@Override
	public <T extends DataType> void plyElement(Element element,
			Property<T> property, T data) {
		// TODO Auto-generated method stub

	}

}
