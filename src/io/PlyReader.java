package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import core.Element;
import core.Format;
import core.Property;

/**
 * A parser for the PLY File Format.
 * 
 * @author Niels Billen
 * @version 1.0
 */
public class PlyReader {
	/**
	 * The format in which the PLY File is formatted in.
	 */
	private Format format;

	/**
	 * The {@link Path} to the PLY File which we are parsing.
	 */
	private Path path;

	/**
	 * The {@link PlyHandler} to which the parsed information is passed.
	 */
	private PlyHandler handler;

	/**
	 * List with {@link Element} definitions.
	 */
	private List<Element> elements = new ArrayList<Element>();

	/**
	 * The line which is currently parsed.
	 */
	private int lineIndex = 1;

	/**
	 * 
	 */
	private PlyReader(Path path, PlyHandler handler)
			throws NullPointerException, IOException, ParseException {
		this.path = path;
		this.handler = handler;

		try (InputStream stream = Files.newInputStream(path)) {
			try (InputStreamReader inputReader = new InputStreamReader(stream)) {
				try (BufferedReader reader = new BufferedReader(inputReader)) {
					parseHeader(reader);
				}
			}
		}
	}

	/**
	 * 
	 * @param filename
	 * @param handler
	 * @throws IOException
	 */
	public static void parse(String filename, PlyHandler handler)
			throws NullPointerException, IOException, ParseException {
		parse(new File(filename), handler);
	}

	/**
	 * 
	 * @param file
	 * @param handler
	 * @throws IOException
	 */
	public static void parse(File file, PlyHandler handler) throws IOException,
			ParseException {
		parse(file.toPath(), handler);
	}

	/**
	 * 
	 * @param path
	 * @param handler
	 * @throws IOException
	 */
	public static void parse(Path path, PlyHandler handler) throws IOException,
			ParseException {
		new PlyReader(path, handler);
	}

	/**
	 * 
	 * @param reader
	 * @throws IOException
	 * @throws ParseException
	 */
	private void parseHeader(BufferedReader reader) throws IOException,
			ParseException {
		String line;
		String filename = path.toFile().getName();
		boolean headerEnd = false;
		Element element = null;

		/*---------------------------------------------------------
		 * check the magic number
		 *-------------------------------------------------------*/

		line = reader.readLine();
		if (line == null)
			throw new ParseException("the file is empty!", filename, 1, 1);
		if (line.matches("ply +"))
			throw new ParseException(
					"unexpected whitespace after magic number!", filename, 1, 4);
		else if (!line.equals("ply"))
			throw new ParseException("unrecognized magic number!", filename, 1,
					1);

		/*---------------------------------------------------------
		 * parse the header
		 *-------------------------------------------------------*/
		while ((line = reader.readLine()) != null) {
			++lineIndex;

			String[] split = line.split(" +");

			if (split[0].equals("format")) {
				// extract the version number.
				format = Format.parseFromString(split[1]);
				String version = split[2];
				int dot = version.indexOf('.');
				int majorVersion = Integer.parseInt(version.substring(0, dot));
				int minorVersion = Integer.parseInt(version.substring(dot + 1));

				handler.plyHeaderFormat(format, majorVersion, minorVersion);
			} else if (split[0].equals("comment")) {
				// extract the comment
				StringBuilder comment = new StringBuilder();
				for (int i = 1; i < split.length; ++i) {
					comment.append(split[i]);
					if (i < split.length - 1)
						comment.append(" ");
				}

				handler.plyHeaderComment(comment.toString());
			} else if (split[0].equals("element")) {
				if (element != null) {
					elements.add(element);
					handler.plyHeaderElement(element);
				}
				String name = split[1];
				int count = Integer.parseInt(split[2]);
				element = new Element(name, count);
			} else if (split[0].equals("end_header")) {
				// end of the header found
				headerEnd = true;
				if (element != null) {
					elements.add(element);
					handler.plyHeaderElement(element);
				}
				break;
			}
		}

		/*--------------------------------------------------------
		 * check the state
		 *-------------------------------------------------------*/

		if (format == null) {
			throw new ParseException("no format specified in the header!",
					filename, lineIndex, 1);
		}
		if (!headerEnd)
			throw new ParseException("no end_header deliniater specified!",
					filename, lineIndex, 1);
	}

	/**
	 * 
	 * @param reader
	 * @throws IOException
	 */
	private void parseASCIIBody(BufferedReader reader) throws IOException,
			ParseException {
		String filename = path.toFile().getName();

		for (Element element : elements) {
			for (int i = 0; i < element.getCount(); ++i) {
				++lineIndex;
				String line = reader.readLine();

				if (line == null)
					throw new ParseException(
							"expected another line containing an element with name \""
									+ element.getName()
									+ "\", but the end of the file has been reached!",
							filename, lineIndex, 1);

				String[] split = line.split(" +");
				Stack<String> stack = new Stack<String>();
				for (String string : split)
					stack.add(string);

				for (Property property : element) {
					property.parse(stack.iterator(),element, handler);
				}
			}
		}
	}
}
