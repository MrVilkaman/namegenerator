package com.github.mrvilkaman.namegenerator.domainlayer.usecase;

import com.github.mrvilkaman.namegenerator.domainlayer.nametemplates.NameTemplate;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SchedulersProvider;

import java.util.Collections;
import java.util.NoSuchElementException;

import rx.Observable;

/**
 * Created by Zahar on 18.03.16.
 */
public class GenerateNameUseCase extends UseCase<String> {

	private final FriendDataProvider friendDataProvider;
	private long friendId;
	private NameTemplate nameTemplate;

	public GenerateNameUseCase(FriendDataProvider friendDataProvider, SchedulersProvider provider) {
		super(provider.computation(), provider.mainThread());
		this.friendDataProvider = friendDataProvider;
	}

	public GenerateNameUseCase setFriendId(long friendId) {
		this.friendId = friendId;
		return this;
	}

	public GenerateNameUseCase setNameTemplate(NameTemplate nameTemplate) {
		this.nameTemplate = nameTemplate;
		return this;
	}

	@Override
	protected Observable<String> buildUseCaseObservable() {
		return friendDataProvider.getFriendsLocal()
				.filter(list -> list != null)
				.switchIfEmpty(friendDataProvider.getFriendsRemote())
				.flatMap(Observable::from)
				.first(friend -> friend.getId() == friendId)
				.map(friend -> nameTemplate.generate(friend))
				.onErrorReturn(throwable -> throwable instanceof NoSuchElementException ? "" : "Error!");

	}
}
