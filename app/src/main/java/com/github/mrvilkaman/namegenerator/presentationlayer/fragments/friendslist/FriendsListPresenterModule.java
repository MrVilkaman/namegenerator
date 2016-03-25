package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.friendslist;

import android.support.annotation.NonNull;

import com.github.mrvilkaman.namegenerator.domainlayer.interactor.SaveLocalUserInteractor;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.UseCase;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.UseCaseModule;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Zahar on 24.03.16.
 */
@Module
public class FriendsListPresenterModule {

	@Provides
	@NonNull
	FriendsListPresenter provideInfoPresenter(
			@Named(UseCaseModule.USER_LIST) UseCase<List<Friend>> userListUseCase,
			SaveLocalUserInteractor saveLocalUserInteractor) {
		return new FriendsListPresenter(userListUseCase, saveLocalUserInteractor);
	}

}
