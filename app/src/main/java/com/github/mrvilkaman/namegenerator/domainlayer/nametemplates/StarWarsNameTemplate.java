package com.github.mrvilkaman.namegenerator.domainlayer.nametemplates;

import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Parent;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.UserSex;

/**
 * Created by Zahar on 18.03.16.
 */
public class StarWarsNameTemplate implements NameTemplate {
	@Override
	public String generate(Friend friend) {
		StringBuffer buffer = new StringBuffer(11);
		String lastName = friend.getLastName();
		lastName = lastName.substring(0,Math.min(3,lastName.length()));
		buffer.append(lastName);

		String firstName = friend.getFirstName();
		firstName = firstName.substring(0,Math.min(2,firstName.length()));
		buffer.append(firstName.toLowerCase());

		buffer.append(' ');

		for (Parent parent : friend.getParent()) {
			if (parent.getSex() == UserSex.MAN) {
				String name = parent.getName();
				name = name.substring(0,Math.min(2,name.length()));
				buffer.append(name);
				break;
			}
		}

		String city = friend.getCity();
		city = city.substring(0,Math.min(3,city.length()));
		buffer.append(city.toLowerCase());

		return buffer.toString();
	}
}
