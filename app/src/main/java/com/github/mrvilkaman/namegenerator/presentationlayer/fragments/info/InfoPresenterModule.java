package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.info;

import android.support.annotation.NonNull;

import com.github.mrvilkaman.namegenerator.domainlayer.usecase.GenerateNameUseCase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zahar on 24.03.16.
 */
@Module
public class InfoPresenterModule {

	@Provides
	@NonNull
	InfoPresenter provideInfoPresenter(){
//		DataProviders providers = DataProvidersFactory.get();
		return null;
//				new InfoPresenter(new GenerateNameUseCase(providers.getFriendDataProvider(),providers.getSchedulersProvider()));
	}
}
