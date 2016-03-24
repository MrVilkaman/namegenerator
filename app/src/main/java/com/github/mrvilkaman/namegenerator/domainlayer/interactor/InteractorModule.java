package com.github.mrvilkaman.namegenerator.domainlayer.interactor;

import com.github.mrvilkaman.namegenerator.datalayer.interactor.TokenInteractorImpl;
import com.github.mrvilkaman.namegenerator.datalayer.interactor.VkLoginInteractorImpl;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SchedulersProvider;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SessionDataProvider;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Zahar on 16.03.16.
 */
@Module
public class InteractorModule {

	@Provides
	public static TokenInteractor getTokenInteractor(SessionDataProvider session) {
		return new TokenInteractorImpl(session);
	}

	@Provides
	public static SaveLocalUserInteractor getSaveLocalUserInteractor(FriendDataProvider friendDataProvider) {
		return new SaveLocalUserInteractor(friendDataProvider);
	}
}
