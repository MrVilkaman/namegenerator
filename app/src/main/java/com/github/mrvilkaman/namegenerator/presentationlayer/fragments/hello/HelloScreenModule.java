package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.hello;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.github.mrvilkaman.namegenerator.datalayer.interactor.VkLoginInteractorImpl;
import com.github.mrvilkaman.namegenerator.domainlayer.interactor.TokenInteractor;
import com.github.mrvilkaman.namegenerator.domainlayer.interactor.VkLoginInteractor;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SchedulersProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zahar on 24.03.16.
 */
@Module
public class HelloScreenModule {

	private Fragment fragment;

	public HelloScreenModule(Fragment fragment) {
		this.fragment = fragment;
	}

	@Provides
	@NonNull
	HelloScreenPresenter provideHelloScreenPresenter(VkLoginInteractor model, TokenInteractor token){
		return new HelloScreenPresenter(model, token);
	}

	@Provides
	VkLoginInteractor provideVkLoginInteractor(SchedulersProvider scheduler) {
		return new VkLoginInteractorImpl(fragment,scheduler);
	}

}

