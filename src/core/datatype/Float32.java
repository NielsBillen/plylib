package core.datatype;

import java.io.IOException;
import java.nio.ByteOrder;

import util.PlyScanner;
import core.Format;

/**
 * Represents a single precision floating point value.
 * 
 * @author Niels Billen
 * @version 1.0
 */
public class Float32 extends FloatScalar {
	/**
	 * The singleton instance of {@link Float32}.
	 */
	public static final Float32 FLOAT32 = new Float32();

	/**
	 * Creates a new singleton instance of this {@link Float32}.
	 */
	private Float32() {
	}

	/**
	 * Returns the singleton instance of {@link Float32}.
	 * 
	 * @return the singleton instance of {@link Float32}.
	 */
	public static Float32 getFloat32() {
		return FLOAT32;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.FloatScalar#parse(util.PlyScanner, core.Format)
	 */
	@Override
	public Double parse(PlyScanner reader, Format format) throws IOException,
			NumberFormatException {
		if (format.equals(Format.BINARY_LITTLE_ENDIAN))
			return (double) reader.nextFloat(ByteOrder.LITTLE_ENDIAN);
		else if (format.equals(Format.BINARY_BIG_ENDIAN))
			return (double) reader.nextFloat(ByteOrder.BIG_ENDIAN);
		else
			return super.parse(reader, format);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.Scalar#getMinimumValue()
	 */
	@Override
	public Double getMinimumValue() {
		return -(double) (Float.MAX_VALUE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.Scalar#getMaximumValue()
	 */
	@Override
	public Double getMaximumValue() {
		return (double) (Float.MAX_VALUE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.DataType#nbOfBytes()
	 */
	@Override
	public int nbOfBytes() {
		return 4;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.DataType#toPLY()
	 */
	@Override
	public String toPLY() {
		return "float32";
	}
}
