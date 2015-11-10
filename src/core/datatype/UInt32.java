package core.datatype;

/**
 * Represents a signed short integer.
 * 
 * @author Niels Billen
 * @version 1.0
 */
public class UInt32 extends IntScalar {
	/**
	 * The singleton instance of {@link UInt32}.
	 */
	public static final UInt32 UINT32 = new UInt32();

	/**
	 * Creates a new singleton instance of this {@link UInt16}.
	 */
	private UInt32() {
	}

	/**
	 * Returns the singleton instance of {@link UInt16}.
	 * 
	 * @return the singleton instance of {@link UInt16}.
	 */
	public static UInt32 getUInt32() {
		return UINT32;
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
		return 4294967295L;
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
		return "uint32";
	}
}
