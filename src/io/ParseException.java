package io;

import java.io.File;

/**
 * An exception which is thrown to indicate an error during the parsing of a
 * {@link File}.
 * 
 * The {@link ParseException} shows additional info concerning the position of
 * the error in the {@link File}.
 * 
 * @author Niels Billen
 * @version 0.1
 */
public class ParseException extends RuntimeException {
	private static final long serialVersionUID = 1406768068380278848L;

	/**
	 * The {@link File} where the exception occurs.
	 */
	private String filename = null;

	/**
	 * The line number where the exception occurs.
	 */
	private int row = -1;

	/**
	 * The column of the line where the exception occurs.
	 */
	private int column = -1;

	/**
	 * Create
	 * 
	 * @param message
	 */
	public ParseException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param e
	 */
	public ParseException(Exception e) {
		super(e);
	}

	/**
	 * Create
	 * 
	 * @param message
	 */
	public ParseException(String message, String filename, int row, int column) {
		super(message);

		setFilename(filename);
		setRow(row);
		setColumn(column);
	}

	/**
	 * Create
	 * 
	 * @param message
	 */
	public ParseException(Exception e, String filename, int row, int column) {
		super(e);

		setFilename(filename);
		setRow(row);
		setColumn(column);
	}

	/**
	 * 
	 * @return
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * 
	 * @param file
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * 
	 * @return
	 */
	public int getRow() {
		return row;
	}

	/**
	 * 
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * 
	 * @return
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * 
	 * @param column
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getName());
		builder.append(": ");
		builder.append(super.getLocalizedMessage());
		
		StringBuilder location = new StringBuilder();

		if (filename != null)
			location.append("in file '").append(filename).append("' ");
		if (row > -1)
			location.append("at line ").append(row).append(" ");
		if (column > -1)
			location.append("in column ").append(column).append(" ");

		if (location.length() > 0)
			builder.append("\n\t").append(location);
		else
			builder.append(" ");
		return builder.toString();
	}
}
