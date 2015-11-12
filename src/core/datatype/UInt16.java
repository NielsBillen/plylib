package core.datatype;

import java.io.IOException;
import java.nio.ByteOrder;

import util.PlyScanner;
import core.Format;

/**
 * Represents an unsigned short integer.
 * 
 * @author Niels Billen
 * @version 1.0
 */
public class UInt16 extends IntScalar {
	/**
	 * The singleton instance of {@link UInt16}.
	 */
	public static UInt16 UINT16 = new UInt16();

	/**
	 * Creates a new singleton instance of this {@link UInt16}.
	 */
	private UInt16() {
	}

	/**
	 * Returns the singleton instance of {@link UInt16}.
	 * 
	 * @return the singleton instance of {@link UInt16}.
	 */
	public static UInt16 getUInt16() {
		return UINT16;
	}

	@Override
	public Long parse(PlyScanner scanner, Format format) throws IOException,
			NumberFormatException {
		long result;
		if (format.equals(Format.BINARY_LITTLE_ENDIAN))
			result = (long) scanner.nextUnsignedShort(ByteOrder.LITTLE_ENDIAN);
		else if (format.equals(Format.BINARY_BIG_ENDIAN))
			result = (long) scanner.nextUnsignedShort(ByteOrder.BIG_ENDIAN);
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
		return 65535L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.DataType#nbOfBytes()
	 */
	@Override
	public int nbOfBytes() {
		return 2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.DataType#toPLY()
	 */
	@Override
	public String toPLY() {
		return "uint16";
	}
}
