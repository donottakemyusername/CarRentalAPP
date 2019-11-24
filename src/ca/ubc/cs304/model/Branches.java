package ca.ubc.cs304.model;

import java.util.Objects;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class Branches {
	private String location;
	private String city;

	public Branches(String location, String city) {
		this.location = location;
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public String getCity() {
		return city;
	}

    public void setCity(String city) {
        this.city = city;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Branches that = (Branches) o;
		return location.equals(that.location) &&
				city.equals(that.city);
	}

	@Override
	public int hashCode() {
		return Objects.hash(location, city);
	}
}
