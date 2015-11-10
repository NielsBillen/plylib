package core.datatype;

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
