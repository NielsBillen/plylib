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
		builder.append(":");
		if (filename != null)
			builder.append(" in file '").append(filename).append("'");
		if (row > -1)
			builder.append(" at line ").append(row);
		if (column > -1)
			builder.append(" in column ").append(column);

		if (filename != null || row > -1 || column > -1)
			builder.append(": ");
		else
			builder.append(" ");
		builder.append(super.getLocalizedMessage());
		return builder.toString();
	}
}
