package com.github.mrvilkaman.namegenerator.datalayer.interactor;

import com.github.mrvilkaman.namegenerator.domainlayer.interactor.SaveLocalUserInteractor;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;

/**
 * Created by Zahar on 19.03.16.
 */
public class SaveLocalUserInteractorImpl implements SaveLocalUserInteractor {

	private FriendDataProvider friendDataProvider;

	public SaveLocalUserInteractorImpl(FriendDataProvider friendDataProvider) {
		this.friendDataProvider = friendDataProvider;
	}

	@Override
	public void saveFriend(Friend friend) {
		friendDataProvider.setLastFriend(friend);
	}
}
