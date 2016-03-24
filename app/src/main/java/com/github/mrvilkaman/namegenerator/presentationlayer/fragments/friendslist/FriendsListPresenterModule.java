package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.friendslist;

import android.support.annotation.NonNull;

import com.github.mrvilkaman.namegenerator.domainlayer.interactor.InteractorModule;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.UseCaseFactory;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Zahar on 24.03.16.
 */
@Module
public class FriendsListPresenterModule {

	@Provides
	@NonNull
	FriendsListPresenter provideInfoPresenter() {
		return null;//new FriendsListPresenter(UseCaseFactory.getUserListUseCase(), InteractorModule.getSaveLocalUserInteractor(friendDataProvider));
	}

}
