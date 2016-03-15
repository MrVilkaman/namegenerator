package com.github.mrvilkaman.namegenerator.domainlayer.usecase;

import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SchedulersProvider;

import rx.Observable;

/**
 * Created by root on 15.03.16.
 */
public class GetUserListInteractor extends UseCase {

	private final FriendDataProvider friendProvider;

	public GetUserListInteractor(FriendDataProvider friendProvider, SchedulersProvider provider) {
		super(provider.io(), provider.mainThread());
		this.friendProvider = friendProvider;
	}

	@Override
	protected Observable buildUseCaseObservable() {
		return friendProvider.getFriends();
	}
}
