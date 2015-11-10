package core.datatype;

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
	private static UInt8 UINT8 = new UInt8();

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
