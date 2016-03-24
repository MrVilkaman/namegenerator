package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.info;

import android.support.annotation.NonNull;

import com.github.mrvilkaman.namegenerator.domainlayer.interactor.SaveLocalUserInteractor;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SchedulersProvider;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.GenerateNameUseCase;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.UseCase;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.UseCaseModule;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zahar on 24.03.16.
 */
@Module
public class InfoPresenterModule {

	@Provides
	@NonNull
	InfoPresenter provideInfoPresenter(GenerateNameUseCase name){
		return new InfoPresenter(name);
	}

	@Provides

	@NonNull
	GenerateNameUseCase provideGenerateNameUseCase(FriendDataProvider friendDataProvider, SchedulersProvider provider) {
		return new GenerateNameUseCase(friendDataProvider,provider);
	}
}
