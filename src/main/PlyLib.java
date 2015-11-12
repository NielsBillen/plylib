package main;

import io.ParseException;
import io.PlyEchoHandler;
import io.PlyReader;

import java.io.IOException;

/**
 * 
 * @author Niels Billen
 * @version 1.0
 */
public class PlyLib {
	/**
	 * 
	 * @param args
	 * @throws ParseException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ParseException, IOException {
		PlyReader.parse("cube.ply", new PlyEchoHandler());
	}
}
