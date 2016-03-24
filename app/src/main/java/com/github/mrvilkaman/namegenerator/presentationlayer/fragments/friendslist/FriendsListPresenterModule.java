package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.friendslist;

import android.support.annotation.NonNull;

import com.github.mrvilkaman.namegenerator.domainlayer.interactor.InteractorFactory;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.UseCaseFactory;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.friendslist.FriendsListPresenter;

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
		return new FriendsListPresenter(UseCaseFactory.getUserListUseCase(), InteractorFactory.getSaveLocalUserInteractor());
	}

}
