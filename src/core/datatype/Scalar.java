package core.datatype;

/**
 * Abstract class representing all scalar datatypes in the PLY File Format.
 * 
 * @author Niels Billen
 * @version 0.1
 */
public abstract class Scalar<T extends Comparable<T>> extends DataType {
	/**
	 * Returns true when the given argument is within the range that this
	 * {@link Scalar} data type can represent. Returns false when the given
	 * value is <code>null</code>.
	 * 
	 * @param value
	 *            the value which we would like to test.
	 * @return true when the given argument is within the range that this
	 *         {@link Scalar} data type can represent.
	 */
	public boolean isInRange(T value) {
		return value != null && value.compareTo(getMinimumValue()) >= 0
				&& value.compareTo(getMaximumValue()) <= 0;
	}

	/**
	 * Returns the minimum value which this {@link Scalar} data type can
	 * represent.
	 * 
	 * @return the minimum value which this {@link Scalar} data type can
	 *         represent.
	 */
	public abstract T getMinimumValue();

	/**
	 * Returns the maximum value which this {@link Scalar} data type can
	 * represent.
	 * 
	 * @return the maximum value which this {@link Scalar} data type can
	 *         represent.
	 */
	public abstract T getMaximumValue();
	
	/**
	 * Returns the number of bytes of this {@link Scalar}.
	 * 
	 * @return the number of bytes of this {@link Scalar}.
	 */
	public abstract int nbOfBytes();
}
