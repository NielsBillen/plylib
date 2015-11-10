package core.datatype;

/**
 * Represents a signed single byte integer.
 * 
 * @author Niels Billen
 * @version 1.0
 */
public class Int8 extends IntScalar {
	/**
	 * The singleton instance of {@link Int8}.
	 */
	public static final Int8 INT8 = new Int8();

	/**
	 * Creates a new singleton instance of this {@link Int8}.
	 */
	private Int8() {
	}

	/**
	 * Returns the singleton instance of {@link Int8}.
	 * 
	 * @return the singleton instance of {@link Int8}.
	 */
	public static Int8 getInt8() {
		return INT8;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.Scalar#getMinimumValue()
	 */
	@Override
	public Long getMinimumValue() {
		return -128L;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datatype.Scalar#getMaximumValue()
	 */
	@Override
	public Long getMaximumValue() {
		return 127L;
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
		return "int8";
	}
}
