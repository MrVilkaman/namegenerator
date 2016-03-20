package com.github.mrvilkaman.namegenerator.presentationlayer.model;

import com.vk.sdk.api.model.VKApiUserFull;

/**
 * Created by Zahar on 20.03.16.
 */
public class Parent {
	private String name;
	private UserSex sex;

	public Parent(String name, UserSex sex) {
		this.name = name;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public UserSex getSex() {
		return sex;
	}
}
