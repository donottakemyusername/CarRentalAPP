package ca.ubc.cs304.model;

import java.util.Objects;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class Branch {
	private final String location;
	private final String city;

	public Branch(String location, String city) {
		this.location = location;
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public String getCity() {
		return city;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Branch that = (Branch) o;
		return location.equals(that.location) &&
				city.equals(that.city);
	}

	@Override
	public int hashCode() {
		return Objects.hash(location, city);
	}
}
