package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.info;

import android.support.annotation.NonNull;

import com.github.mrvilkaman.namegenerator.domainlayer.providers.DataProviders;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.DataProvidersFactory;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.GenerateNameUseCase;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.info.InfoPresenter;

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
		DataProviders providers = DataProvidersFactory.get();
		return new InfoPresenter(new GenerateNameUseCase(providers.getFriendDataProvider(),providers.getSchedulersProvider()));
	}
}
