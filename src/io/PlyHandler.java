package io;

import core.ElementDefinition;
import core.Format;

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
	 * 
	 */
	public void plyHeaderEnd();

	/**
	 * Called when a new {@link ElementDefinition} has been parsed from the
	 * header.
	 * 
	 * @param element
	 *            the {@link ElementDefinition} which has been parsed.
	 */
	public void plyElementDefinition(ElementDefinition element);

	/**
	 * Called when a new element with the given name is being parsed.
	 * 
	 * @param elementName
	 *            the name of the element.
	 */
	public void plyElementStart(String elementName);

	/**
	 * Called when the parsing of an element is finished.
	 */
	public void plyElementEnd();

	/**
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void plyProperty(String propertyName, Double value);

	/**
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void plyProperty(String propertyName, Long value);

	/**
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void plyProperty(String propertyName, Double... value);

	/**
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void plyProperty(String propertyName, Long... value);
}
