package util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Implementation of a file reader which can read both binary and regular
 * characters.
 * 
 * @author Niels Billen
 * @version 0.1
 */
public class PlyScanner {
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

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public String nextLine() throws IOException {
		int b = stream.read();
		if (b == -1)
			return null;

		StringBuilder builder = new StringBuilder();

		do {
			char c = (char) b;
			if (c == '\n')
				return builder.toString();
			else
				builder.append(c);
		} while ((b = stream.read()) != -1);
		return builder.toString();
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public String next() throws IOException {
		int b = stream.read();
		if (b == -1)
			return null;

		boolean terminate = false;
		StringBuilder builder = new StringBuilder();

		do {
			char c = (char) b;
			if (c == ' ' || c == '\n') {
				if (terminate)
					return builder.toString();
			} else {
				terminate = true;
				builder.append(c);
			}
		} while ((b = stream.read()) != -1);

		if (terminate)
			return builder.toString();
		else
			return null;
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public int nextByte() throws IOException {
		int result = stream.read();
		if (result == -1)
			throw new IllegalStateException("no more data in this file!");
		return result;
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public int nextUnsignedByte() throws IOException {
		int result = stream.read() & 0xff;
		if (result == -1)
			throw new IllegalStateException("no more data left in this file!");
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
