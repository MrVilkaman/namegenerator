package com.github.mrvilkaman.namegenerator.presentationlayer.model;

/**
 * Created by root on 13.03.16.
 */
public class Friend {
	private int id;
	private String name;

	public Friend(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
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
		return id;
	}

	@Override
	public String toString() {
		return name;
	}
}
