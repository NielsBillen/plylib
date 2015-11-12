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
public class PlyUtil {
	/**
	 * 
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException, IOException {
		PlyReader.parse("bidir_0.ply", new PlyEchoHandler());
	}
}
