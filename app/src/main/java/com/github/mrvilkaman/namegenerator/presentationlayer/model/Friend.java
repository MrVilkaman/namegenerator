package com.github.mrvilkaman.namegenerator.presentationlayer.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 13.03.16.
 */
public class Friend {

	@SerializedName("name")
	private String firstName;
	@SerializedName("last_name")
	private String lastName;
	@SerializedName("city")
	private String city;
	private long id;
	@SerializedName("parent")
	private List<Parent> parent;

	public Friend(){
	}

	public Friend(long id, String name) {
		this.id = id;
		this.firstName = name;
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCity() {
		return city;
	}

	public List<Parent> getParent() {
		return parent;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Friend friend = (Friend) o;

		if (id != friend.id) return false;
		if (firstName != null ? !firstName.equals(friend.firstName) : friend.firstName != null)
			return false;
		if (lastName != null ? !lastName.equals(friend.lastName) : friend.lastName != null)
			return false;
		return city != null ? city.equals(friend.city) : friend.city == null;

	}

	@Override
	public int hashCode() {
		int result = firstName != null ? firstName.hashCode() : 0;
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (city != null ? city.hashCode() : 0);
		result = 31 * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return firstName;
	}
}
