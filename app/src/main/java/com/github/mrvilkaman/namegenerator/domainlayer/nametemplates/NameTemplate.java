package com.github.mrvilkaman.namegenerator.domainlayer.nametemplates;

import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;

/**
 * Created by Zahar on 18.03.16.
 */
public interface NameTemplate {
	String generate(Friend friend);
}
