package com.github.mrvilkaman.namegenerator.domainlayer.usecase;

import com.github.mrvilkaman.namegenerator.domainlayer.nametemplates.NameTemplate;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SchedulersProvider;

import rx.Observable;

/**
 * Created by Zahar on 18.03.16.
 */
public class GenerateNameUseCase extends UseCase<String> {

	private final FriendDataProvider friendDataProvider;
	private final long friendId;
	private NameTemplate nameTemplate;

	public GenerateNameUseCase(long friendId, NameTemplate nameTemplate, FriendDataProvider friendDataProvider, SchedulersProvider provider) {
		super(provider.computation(), provider.mainThread());
		this.friendDataProvider = friendDataProvider;
		this.nameTemplate = nameTemplate;
		this.friendId = friendId;
	}

	@Override
	protected Observable<String> buildUseCaseObservable() {
		return friendDataProvider.getFriends(false)
				.flatMap(Observable::from)
				.first(friend -> friend.getId() == friendId)
				.map(friend -> nameTemplate.generate(friend))
				.onErrorReturn(throwable -> "Error!")
				.defaultIfEmpty("");

	}
}
