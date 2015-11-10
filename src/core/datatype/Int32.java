package core.datatype;

/**
 * Represents a signed short integer.
 * 
 * @author Niels Billen
 * @version 1.0
 */
public class Int32 extends IntScalar {
	/**
	 * The singleton instance of {@link Int32}.
	 */
	public static final Int32 INT32 = new Int32();

	/**
	 * Creates a new singleton instance of this {@link Int32}.
	 */
	private Int32() {
	}

	/**
	 * Returns the singleton instance of {@link Int32}.
	 * 
	 * @return the singleton instance of {@link Int32}.
	 */
	public static Int32 getInt32() {
		return INT32;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.Scalar#getMinimumValue()
	 */
	@Override
	public Long getMinimumValue() {
		return -2147483648L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.Scalar#getMaximumValue()
	 */
	@Override
	public Long getMaximumValue() {
		return 2147483647L;
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
		return "int32";
	}
}
