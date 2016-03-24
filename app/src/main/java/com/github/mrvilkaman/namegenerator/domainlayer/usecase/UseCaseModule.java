package com.github.mrvilkaman.namegenerator.domainlayer.usecase;

import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SchedulersProvider;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zahar on 16.03.16.
 */
@Module
public class UseCaseModule {


	public static final String USER_LIST = "user_list";

	@Provides
	@Named(USER_LIST)
	UseCase provideUserListUseCase(FriendDataProvider friendsProvider, SchedulersProvider schedulers) {
		return new GetUserListUseCase(friendsProvider, schedulers);
	}
}
