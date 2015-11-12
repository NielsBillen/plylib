package io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.PlyScanner;
import core.ElementDefinition;
import core.Format;
import core.PropertyDefinition;
import core.datatype.DataType;

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
	 * List with {@link ElementDefinition} definitions.
	 */
	private List<ElementDefinition> elements = new ArrayList<ElementDefinition>();

	/**
	 * The line which is currently parsed.
	 */
	private int lineIndex = 1;

	/**
	 * Creates a new {@link PlyReader} which reads the file specified by the
	 * given path and hands the parsed data over to the given {@link PlyHandler}
	 * .
	 * 
	 * @param path
	 *            the path of the file to read.
	 * @param handler
	 *            the handler to which the parsed data will be passed.
	 * @throws NullPointerException
	 *             when the given path is null.
	 * @throws NullPointerException
	 *             when the given {@link PlyHandler} is null.
	 * @throws IOException
	 *             when an exception occurs during the reading of the file.
	 * @throws ParseException
	 *             when an exception occurs during the parsing of the file.
	 */
	private PlyReader(Path path, PlyHandler handler)
			throws NullPointerException, IOException, ParseException {
		this.path = path;
		this.handler = handler;

		PlyScanner reader = new PlyScanner(path.toFile());
		parseHeader(reader);
		parseBody(reader, format, handler);
		reader.close();
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
	private void parseHeader(PlyScanner reader) throws IOException,
			ParseException {
		String line;
		String filename = path.toFile().getAbsolutePath();
		boolean headerEnd = false;
		ElementDefinition element = null;

		/*---------------------------------------------------------
		 * check the magic number
		 *-------------------------------------------------------*/

		line = reader.nextLine();
		if (line == null)
			throw new ParseException(
					"the ply file is empty! it should start with the 'ply' magic number!",
					filename, 1, 1);
		if (line.matches("ply +"))
			throw new ParseException(
					"unexpected whitespace after 'ply' magic number!",
					filename, 1, 4);
		else if (!line.equals("ply"))
			throw new ParseException("unrecognized magic number '" + line
					+ "'!", filename, 1, 1);

		/*---------------------------------------------------------
		 * parse the header
		 *-------------------------------------------------------*/
		while ((line = reader.nextLine()) != null) {
			++lineIndex;

			String[] split = line.split(" +");

			try {
				if (split[0].equals("format")) {
					// extract the version number
					format = Format.parseFromString(split[1]);
					String version = split[2];
					int dot = version.indexOf('.');
					int majorVersion = Integer.parseInt(version.substring(0,
							dot));
					int minorVersion = Integer.parseInt(version
							.substring(dot + 1));
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
						handler.plyElementDefinition(element);
					}
					if (split.length < 3)
						throw new ParseException(
								"element requires a name and a count!",
								filename, lineIndex, 1);
					else if (split.length > 3)
						throw new ParseException(
								"to many arguments for an element definition!",
								filename, lineIndex, 1);
					String name = split[1];
					int count = Integer.parseInt(split[2]);
					element = new ElementDefinition(name, count);
				} else if (split[0].equals("end_header")) {
					// end of the header found
					headerEnd = true;
					if (element != null) {
						elements.add(element);
						handler.plyElementDefinition(element);
					}
					break;
				} else if (split[0].equals("property")) {
					String name = split[split.length - 1];
					String[] typeDefinition = Arrays.copyOfRange(split, 1,
							split.length - 1);

					DataType<?> dataType = DataType
							.parseFromString(typeDefinition);

					PropertyDefinition property = new PropertyDefinition(name,
							dataType);
					element.addProperty(property);
				} else {
					throw new ParseException("unknown ply definition '"
							+ split[0] + "'", filename, lineIndex, 1);
				}
			} catch (ParseException e) {
				throw e;
			} catch (Exception e) {
				throw new ParseException("\n\t" + e.toString(), filename,
						lineIndex, -1);
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
		handler.plyHeaderEnd();
	}

	/**
	 * 
	 * @param reader
	 * @throws IOException
	 */
	private void parseBody(PlyScanner reader, Format format, PlyHandler handler)
			throws IOException, ParseException {
		// String filename = path.toFile().getName();

		// iterate over the elements
		for (ElementDefinition element : elements) {
			// iterate over the element occurrences
			for (int i = 0; i < element.getCount(); ++i) {
				++lineIndex;

				// notify that a new element is started
				handler.plyElementStart(element.getName());

				// split the line
				for (PropertyDefinition definition : element) {
					definition.parse(reader, format, handler);
				}

				// notify that a new element ends
				handler.plyElementEnd();
			}
		}
	}
}
