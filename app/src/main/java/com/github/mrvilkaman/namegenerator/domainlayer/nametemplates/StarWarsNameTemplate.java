package com.github.mrvilkaman.namegenerator.domainlayer.nametemplates;

import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;

/**
 * Created by Zahar on 18.03.16.
 */
public class StarWarsNameTemplate implements NameTemplate {
	@Override
	public String generate(Friend friend) {
		return friend.getName() +" generate";
	}
}
