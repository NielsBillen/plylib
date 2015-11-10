package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * An {@link Element} of a PLY file.
 * 
 * @author Niels Billen
 * @version 0.1
 */
public class Element implements Iterable<Property<?>> {
	/**
	 * The name of this {@link Element}.
	 */
	private final String name;

	/**
	 * The number of {@link Element}s in the PLY file.
	 */
	private final int count;

	/**
	 * The properties of this {@link Element}.
	 */
	private final List<Property<?>> properties = new ArrayList<Property<?>>();

	/**
	 * A {@link Map} to access the properties by name.
	 */
	private final HashMap<String, Property<?>> propertyMap = new HashMap<String, Property<?>>();

	/**
	 * Creates a new {@link Element} with the given name. The {@link Element}
	 * will appear the given amount of times inside the PLY file.
	 * 
	 * @param name
	 *            the name of this {@link Element}.
	 * @param count
	 *            the number of times it appears in the PLY file.
	 * @throws NullPointerException
	 *             when the given name is null.
	 * @throws IllegalArgumentException
	 *             when the given count is less than zero.
	 */
	public Element(String name, int count) throws NullPointerException,
			IllegalArgumentException {
		if (name == null)
			throw new NullPointerException("the name cannot be null!");
		if (count < 0)
			throw new IllegalArgumentException(
					"the count cannot be smaller than zero!");

		this.name = name;
		this.count = count;
	}

	/**
	 * Returns the name of this {@link Element}. The name is never
	 * <code>null</code>.
	 * 
	 * @return the name of this {@link Element} (never <code>null</code>).
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the number of times this {@link Element} occurs in the PLY file.
	 * 
	 * @return the number of times this {@link Element} occurs in the PLY file.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Adds the given {@link Property} to this {@link Element}.
	 * 
	 * @param property
	 *            the {@link Property} to add to this {@link Element}.
	 * @throws NullPointerException
	 *             when the given {@link Property} is null.
	 * @throws IllegalArgumentException
	 *             when another {@link Property} with the same name is already
	 *             present.
	 */
	public void addProperty(Property<?> property) throws NullPointerException,
			IllegalArgumentException {
		if (property == null)
			throw new NullPointerException("a property cannot be null!");
		String name = property.getName();

		if (propertyMap.containsKey(name))
			throw new IllegalArgumentException(
					"cannot add properties with the same name!");
		properties.add(property);
		propertyMap.put(property.getName(), property);
	}

	/**
	 * Returns the number of properties present in this {@link Element}.
	 * 
	 * @return the number of properties present in this {@link Element}.
	 */
	public int nbOfProperties() {
		return properties.size();
	}

	/**
	 * Removes the given {@link Property} from this {@link Element}. Nothing
	 * happens when the given {@link Property} is null or when the given
	 * {@link Property} is not contained within this {@link Element}.
	 * 
	 * @param property
	 *            the {@link Property} to remove.
	 */
	public void removeProperty(Property<?> property) {
		if (property == null)
			return;
		if (propertyMap.remove(property) != null)
			properties.remove(property);
	}

	/**
	 * Returns the {@link Property} at the specified position in this
	 * {@link Element}.
	 * 
	 * @param index
	 *            index of the {@link Property} to return.
	 * @return the {@link Property} at the specified position in this list.
	 * @throws IndexOutOfBoundsException
	 *             when the index is out of range
	 *             <code>(index < 0 || index >= getNbOfProperties())</code>.
	 */
	public Property<?> getProperty(int index) throws IndexOutOfBoundsException {
		return properties.get(index);
	}

	/**
	 * Returns the {@link Property} in this {@link Element} with the given name.
	 * Returns null when no {@link Property} with the given name is present in
	 * this {@link Element}.
	 * 
	 * @param name
	 *            the name of the {@link Property} to retrieve.
	 * @throws NullPointerException
	 *             when the given name is null.
	 * @return the {@link Property} with the given name or null when no
	 *         {@link Property} with the given name is present in this
	 *         {@link Element}.
	 */
	public Property<?> getProperty(String name) {
		return propertyMap.get(name);
	}

	/**
	 * Returns a copy of the {@link List} of properties of this {@link Element}.
	 * The {@link List} is not backed by this {@link Element}, so modifications
	 * to the {@link List} will not be reflected in this {@link Element}.
	 * 
	 * @return a copy of the {@link List} of properties of this {@link Element}.
	 *         The {@link List} is not backed by this {@link Element}, so
	 *         modifications to the {@link List} will not be reflected in this
	 *         {@link Element}.
	 */
	public List<Property<?>> getProperties() {
		return new ArrayList<Property<?>>(properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Property<?>> iterator() {
		return new Iterator<Property<?>>() {
			private final Iterator<Property<?>> iterator = properties
					.iterator();

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Iterator#hasNext()
			 */
			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Iterator#next()
			 */
			@Override
			public Property<?> next() {
				return iterator.next();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Iterator#remove()
			 */
			@Override
			public void remove() {
				throw new UnsupportedOperationException(
						"cannot remove properties from this element using this Iterator!");
			}
		};
	}

	/**
	 * Returns this {@link Element} as a string in PLY format.
	 * 
	 * @return this {@link Element} as a string in PLY format.
	 */
	public String toPLY() {
		StringBuilder builder = new StringBuilder();
		builder.append("element ").append(getName()).append(" ")
				.append(getCount());

		for (Property<?> property : this)
			builder.append("\n").append(property);

		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return properties.hashCode() + 31 * (name.hashCode() + 31 * count);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Element other = (Element) obj;
		if (getCount() != other.getCount())
			return false;
		if (getName() != other.getName())
			return false;

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return toPLY();
	}
}
