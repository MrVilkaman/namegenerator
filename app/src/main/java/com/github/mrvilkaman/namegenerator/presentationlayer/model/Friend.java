package com.github.mrvilkaman.namegenerator.presentationlayer.model;

/**
 * Created by root on 13.03.16.
 */
public class Friend {
	private long id;
	private String name;

	public Friend(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Friend friend = (Friend) o;

		return id == friend.id;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public String toString() {
		return name +" "+id;
	}
}
