package main;

import java.io.IOException;

import io.ParseException;
import io.PlyEchoHandler;
import io.PlyReader;

/**
 * 
 * @author Niels Billen
 * @version 1.0
 */
public class PLYUtil {
	/**
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException, IOException {
		PlyReader.parse("ant.ply", new PlyEchoHandler());
	}
}
