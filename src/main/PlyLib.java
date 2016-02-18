package main;

import io.ParseException;
import io.PlyEchoHandler;
import io.PlyHandler;
import io.PlyReader;

import java.io.IOException;

import core.ElementDefinition;
import core.Format;

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
		if (args.length == 0) {
			System.out.println("usage: java -jar PlyLib.jar <filename>");
		}
		for (int i = 0; i < args.length; ++i)
			PlyReader.parse(args[i], new PlyHandler() {
				
				@Override
				public void plyProperty(String propertyName, Long... value) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void plyProperty(String propertyName, Double... value) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void plyProperty(String propertyName, Long value) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void plyProperty(String propertyName, Double value) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void plyHeaderFormat(Format format, int majorVersion,
						int minorVersion) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void plyHeaderEnd() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void plyHeaderComment(String comment) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void plyElementStart(String elementName) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void plyElementEnd() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void plyElementDefinition(ElementDefinition element) {
					// TODO Auto-generated method stub
					
				}
			});
	}
}
