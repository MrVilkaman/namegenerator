package com.github.mrvilkaman.namegenerator.domainlayer.interactor;

import com.github.mrvilkaman.namegenerator.datalayer.interactor.SaveLocalUserInteractorImpl;
import com.github.mrvilkaman.namegenerator.datalayer.interactor.TokenInteractorImpl;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SessionDataProvider;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Zahar on 16.03.16.
 */
@Module
public class InteractorModule {

	@Provides
	TokenInteractor getTokenInteractor(SessionDataProvider session) {
		return new TokenInteractorImpl(session);
	}

	@Provides
	SaveLocalUserInteractor getSaveLocalUserInteractor(FriendDataProvider friendDataProvider) {
		return new SaveLocalUserInteractorImpl(friendDataProvider);
	}
}
