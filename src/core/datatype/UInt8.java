package core.datatype;

import java.io.IOException;

import io.ParseException;

import util.PlyScanner;
import core.Format;

/**
 * Represents a unsigned single byte integer.
 * 
 * @author Niels Billen
 * @version 1.0
 */
public class UInt8 extends IntScalar {
	/**
	 * The singleton instance of {@link UInt8}.
	 */
	public static final UInt8 UINT8 = new UInt8();

	/**
	 * Creates a new singleton instance of this {@link UInt8}.
	 */
	private UInt8() {
	}

	/**
	 * Returns the singleton instance of {@link UInt8}.
	 * 
	 * @return the singleton instance of {@link UInt8}.
	 */
	public static UInt8 getUInt8() {
		return UINT8;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.IntScalar#parse(util.PlyScanner, core.Format)
	 */
	@Override
	public Long parse(PlyScanner scanner, Format format) throws IOException,
			NumberFormatException, ParseException {
		long result;
		if (format.equals(Format.BINARY_LITTLE_ENDIAN)
				|| format.equals(Format.BINARY_BIG_ENDIAN))
			result = scanner.nextUnsignedByte();
		else
			result = super.parse(scanner, format);

		if (!isInRange(result))
			throw new IllegalStateException(
					String.format(
							"the parsed %s with value %d is not within the allowed range [%d, %d]",
							toPLY(), result, getMinimumValue(),
							getMaximumValue()));

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.Scalar#getMinimumValue()
	 */
	@Override
	public Long getMinimumValue() {
		return 0L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.Scalar#getMaximumValue()
	 */
	@Override
	public Long getMaximumValue() {
		return 255L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.DataType#nbOfBytes()
	 */
	@Override
	public int nbOfBytes() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.DataType#toPLY()
	 */
	@Override
	public String toPLY() {
		return "uint8";
	}
}
