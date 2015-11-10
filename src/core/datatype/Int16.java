package core.datatype;

/**
 * Represents a signed short integer.
 * 
 * @author Niels Billen
 * @version 1.0
 */
public class Int16 extends IntScalar {
	/**
	 * The singleton instance of {@link Int16}.
	 */
	public static final Int16 INT16 = new Int16();

	/**
	 * Creates a new singleton instance of this {@link Int16}.
	 */
	private Int16() {
	}

	/**
	 * Returns the singleton instance of {@link Int16}.
	 * 
	 * @return the singleton instance of {@link Int16}.
	 */
	public static Int16 getInt16() {
		return INT16;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.Scalar#getMinimumValue()
	 */
	@Override
	public Long getMinimumValue() {
		return -32768L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.Scalar#getMaximumValue()
	 */
	@Override
	public Long getMaximumValue() {
		return 32767L;
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
		return "int16";
	}
}
