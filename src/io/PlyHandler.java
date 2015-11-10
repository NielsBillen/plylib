package io;

import core.Element;
import core.Format;
import core.Property;
import core.datatype.DataType;

/**
 * 
 * @author Niels Billen
 * @version 1.0
 */
public interface PlyHandler {
	/**
	 * Called when the format line is parsed in the header.
	 * 
	 * @param format
	 *            the encoding format of the PLY File.
	 * @param majorVersion
	 *            the major version number of the PLY File Format used to encode
	 *            the mesh.
	 * @param minorVersion
	 *            the minor version number of the PLY File Format used to encode
	 *            the mesh.
	 */
	public void plyHeaderFormat(Format format, int majorVersion,
			int minorVersion);

	/**
	 * Called when a comment is encountered in the header.
	 * 
	 * @param comment
	 *            the parsed comment.
	 */
	public void plyHeaderComment(String comment);

	/**
	 * Called when a new {@link Element} has been parsed from the header.
	 * 
	 * @param element
	 *            the {@link Element} which has been parsed.
	 */
	public void plyHeaderElement(Element element);
	
	/**
	 * 
	 * @param element
	 * @param property
	 * @param data
	 */
	public <T extends DataType> void plyElement(Element element, Property<T> property, T data);
}
