package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.hello;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.github.mrvilkaman.namegenerator.domainlayer.interactor.InteractorFactory;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.hello.HelloScreenPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zahar on 24.03.16.
 */
@Module
public class HelloScreenModule {

	private Fragment fragment;

	public HelloScreenModule(@NonNull Fragment fragment) {
		this.fragment = fragment;
	}

	@Provides
	@NonNull
	HelloScreenPresenter provideHelloScreenPresenter(){
		return new HelloScreenPresenter(InteractorFactory.getVkLoginInteractor(fragment), InteractorFactory.getTokenInteractor());
	}

}

