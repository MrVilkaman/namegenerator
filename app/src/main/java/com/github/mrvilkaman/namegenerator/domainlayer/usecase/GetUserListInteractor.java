package com.github.mrvilkaman.namegenerator.domainlayer.usecase;

import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.presentationlayer.activities.App;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;

import java.util.List;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by root on 15.03.16.
 */
public class GetUserListInteractor extends UseCase{

	private final FriendDataProvider friendProvider;

	public GetUserListInteractor(FriendDataProvider friendProvider, Scheduler subscribeOn, Scheduler observeOn) {
		super(subscribeOn, observeOn);
		this.friendProvider = friendProvider;
	}

	@Override
	protected Observable buildUseCaseObservable() {
		return friendProvider.getFriends();
	}
}
