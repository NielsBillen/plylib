package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * An {@link ElementDefinition} of a PLY file.
 * 
 * @author Niels Billen
 * @version 0.1
 */
public class ElementDefinition implements Iterable<PropertyDefinition> {
	/**
	 * The name of this {@link ElementDefinition}.
	 */
	private final String name;

	/**
	 * The number of {@link ElementDefinition}s in the PLY file.
	 */
	private final int count;

	/**
	 * The properties of this {@link ElementDefinition}.
	 */
	private final List<PropertyDefinition> properties = new ArrayList<PropertyDefinition>();

	/**
	 * A {@link Map} to access the properties by name.
	 */
	private final HashMap<String, PropertyDefinition> propertyMap = new HashMap<String, PropertyDefinition>();

	/**
	 * Creates a new {@link ElementDefinition} with the given name. The {@link ElementDefinition}
	 * will appear the given amount of times inside the PLY file.
	 * 
	 * @param name
	 *            the name of this {@link ElementDefinition}.
	 * @param count
	 *            the number of times it appears in the PLY file.
	 * @throws NullPointerException
	 *             when the given name is null.
	 * @throws IllegalArgumentException
	 *             when the given count is less than zero.
	 */
	public ElementDefinition(String name, int count) throws NullPointerException,
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
	 * Returns the name of this {@link ElementDefinition}. The name is never
	 * <code>null</code>.
	 * 
	 * @return the name of this {@link ElementDefinition} (never <code>null</code>).
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the number of times this {@link ElementDefinition} occurs in the PLY file.
	 * 
	 * @return the number of times this {@link ElementDefinition} occurs in the PLY file.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Adds the given {@link PropertyDefinition} to this {@link ElementDefinition}.
	 * 
	 * @param property
	 *            the {@link PropertyDefinition} to add to this {@link ElementDefinition}.
	 * @throws NullPointerException
	 *             when the given {@link PropertyDefinition} is null.
	 * @throws IllegalArgumentException
	 *             when another {@link PropertyDefinition} with the same name is already
	 *             present.
	 */
	public void addProperty(PropertyDefinition property) throws NullPointerException,
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
	 * Returns the number of properties present in this {@link ElementDefinition}.
	 * 
	 * @return the number of properties present in this {@link ElementDefinition}.
	 */
	public int nbOfProperties() {
		return properties.size();
	}

	/**
	 * Removes the given {@link PropertyDefinition} from this {@link ElementDefinition}. Nothing
	 * happens when the given {@link PropertyDefinition} is null or when the given
	 * {@link PropertyDefinition} is not contained within this {@link ElementDefinition}.
	 * 
	 * @param property
	 *            the {@link PropertyDefinition} to remove.
	 */
	public void removeProperty(PropertyDefinition property) {
		if (property == null)
			return;
		if (propertyMap.remove(property) != null)
			properties.remove(property);
	}

	/**
	 * Returns the {@link PropertyDefinition} at the specified position in this
	 * {@link ElementDefinition}.
	 * 
	 * @param index
	 *            index of the {@link PropertyDefinition} to return.
	 * @return the {@link PropertyDefinition} at the specified position in this list.
	 * @throws IndexOutOfBoundsException
	 *             when the index is out of range
	 *             <code>(index < 0 || index >= getNbOfProperties())</code>.
	 */
	public PropertyDefinition getProperty(int index) throws IndexOutOfBoundsException {
		return properties.get(index);
	}

	/**
	 * Returns the {@link PropertyDefinition} in this {@link ElementDefinition} with the given name.
	 * Returns null when no {@link PropertyDefinition} with the given name is present in
	 * this {@link ElementDefinition}.
	 * 
	 * @param name
	 *            the name of the {@link PropertyDefinition} to retrieve.
	 * @throws NullPointerException
	 *             when the given name is null.
	 * @return the {@link PropertyDefinition} with the given name or null when no
	 *         {@link PropertyDefinition} with the given name is present in this
	 *         {@link ElementDefinition}.
	 */
	public PropertyDefinition getProperty(String name) {
		return propertyMap.get(name);
	}

	/**
	 * Returns a copy of the {@link List} of properties of this {@link ElementDefinition}.
	 * The {@link List} is not backed by this {@link ElementDefinition}, so modifications
	 * to the {@link List} will not be reflected in this {@link ElementDefinition}.
	 * 
	 * @return a copy of the {@link List} of properties of this {@link ElementDefinition}.
	 *         The {@link List} is not backed by this {@link ElementDefinition}, so
	 *         modifications to the {@link List} will not be reflected in this
	 *         {@link ElementDefinition}.
	 */
	public List<PropertyDefinition> getProperties() {
		return new ArrayList<PropertyDefinition>(properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<PropertyDefinition> iterator() {
		return new Iterator<PropertyDefinition>() {
			private final Iterator<PropertyDefinition> iterator = properties
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
			public PropertyDefinition next() {
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
	 * Returns this {@link ElementDefinition} as a string in PLY format.
	 * 
	 * @return this {@link ElementDefinition} as a string in PLY format.
	 */
	public String toPLY() {
		StringBuilder builder = new StringBuilder();
		builder.append("element ").append(getName()).append(" ")
				.append(getCount());

		for (PropertyDefinition property : this)
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
		ElementDefinition other = (ElementDefinition) obj;
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
