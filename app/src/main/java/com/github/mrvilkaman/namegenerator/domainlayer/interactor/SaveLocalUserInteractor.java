package com.github.mrvilkaman.namegenerator.domainlayer.interactor;

import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;

/**
 * Created by Zahar on 19.03.16.
 */
public class SaveLocalUserInteractor {

	private FriendDataProvider friendDataProvider;

	public SaveLocalUserInteractor(FriendDataProvider friendDataProvider) {
		this.friendDataProvider = friendDataProvider;
	}

	public void saveFriend(Friend friend) {
		friendDataProvider.setLastFriend(friend);
	}
}
