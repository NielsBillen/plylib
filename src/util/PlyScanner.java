package util;

import io.ParseException;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Implementation of a scanner which can read both binary and regular
 * characters.
 * 
 * @author Niels Billen
 * @version 0.1
 */
public class PlyScanner extends InputStream implements Closeable, AutoCloseable {
	private DataInputStream stream;

	/**
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 */
	public PlyScanner(File file) throws FileNotFoundException {
		FileInputStream fin = new FileInputStream(file);
		stream = new DataInputStream(fin);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.InputStream#read()
	 */
	@Override
	public int read() throws IOException {
		return stream.read();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		stream.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException {
		stream.close();
	}

	/**
	 * Advances this scanner past the current line and returns the input that
	 * was skipped.
	 * 
	 * @throws IOException
	 *             when an I/O exception occurs during the reading.
	 * @return the input that was skipped.
	 */
	public String nextLine() throws IOException {
		int b = read();
		if (b == -1)
			return null;

		StringBuilder builder = new StringBuilder();

		do {
			char c = (char) b;
			if (c == '\n')
				return builder.toString();
			else
				builder.append(c);
		} while ((b = read()) != -1);
		return builder.toString();
	}

	/**
	 * Finds and returns the next complete token from this scanner or null when
	 * no such token can be found.
	 * 
	 * @throws IOException
	 *             when an I/O exception occurs.
	 * @return the next complete token from this scanner.
	 */
	public String next() throws IOException {
		int b = read();
		if (b == -1)
			return null;

		StringBuilder builder = new StringBuilder();

		// check whether the token is valid (i.e, not empty)
		boolean validToken = false;
		do {
			char c = (char) b;
			if (c == ' ' || c == '\n') {
				if (validToken)
					return builder.toString();
			} else {
				validToken = true;
				builder.append(c);
			}
		} while ((b = read()) != -1);

		if (validToken)
			return builder.toString();
		else
			return null;
	}

	/**
	 * Returns the next byte seen by this scanner.
	 * 
	 * @throws IOException
	 *             when an I/O exception occurs.
	 * @return the next byte seen by this scanner.
	 */
	public int nextByte() throws IOException {
		int result = read();
		if (result == -1)
			throw new ParseException("no more data in this file!");
		return result;
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public int nextUnsignedByte() throws IOException {
		int result = read() & 0xff;
		if (result == -1)
			throw new ParseException("no more data in this file!");
		return result;
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public float nextFloat(ByteOrder order) throws IOException {
		byte[] bytes = new byte[4];
		for (int i = 0; i < 4; ++i)
			bytes[i] = (byte) nextByte();
		return ByteBuffer.wrap(bytes).order(order).getFloat();
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public double nextDouble(ByteOrder order) throws IOException {
		byte[] bytes = new byte[8];
		for (int i = 0; i < 8; ++i)
			bytes[i] = (byte) nextByte();
		return ByteBuffer.wrap(bytes).order(order).getDouble();
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public int nextInteger(ByteOrder order) throws IOException {
		byte[] bytes = new byte[4];
		for (int i = 0; i < 4; ++i)
			bytes[i] = (byte) nextByte();
		return ByteBuffer.wrap(bytes).order(order).getInt();
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public long nextUnsignedInteger(ByteOrder order) throws IOException {
		return ((long) nextInteger(order)) & 0xffffffffL;
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public short nextShort(ByteOrder order) throws IOException {
		byte[] bytes = new byte[2];
		for (int i = 0; i < 2; ++i)
			bytes[i] = (byte) nextByte();
		return ByteBuffer.wrap(bytes).order(order).getShort();
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public int nextUnsignedShort(ByteOrder order) throws IOException {
		byte[] bytes = new byte[4];
		for (int i = 0; i < 4; ++i)
			bytes[i] = (byte) nextByte();
		return ((int) nextShort(order)) & 0xffff;
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public long nextLong(ByteOrder order) throws IOException {
		byte[] bytes = new byte[8];
		for (int i = 0; i < 8; ++i)
			bytes[i] = (byte) nextByte();
		return ByteBuffer.wrap(bytes).order(order).get();
	}
}
