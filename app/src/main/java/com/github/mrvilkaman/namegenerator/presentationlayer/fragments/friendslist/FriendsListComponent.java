package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.friendslist;

import com.github.mrvilkaman.namegenerator.domainlayer.interactor.InteractorModule;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.UseCaseModule;
import com.github.mrvilkaman.namegenerator.presentationlayer.app.AppComponent;
import com.github.mrvilkaman.namegenerator.presentationlayer.app.PerActivity;

import dagger.Component;

/**
 * Created by Zahar on 24.03.16.
 */
@PerActivity
@Component(dependencies = AppComponent.class,
		modules = {FriendsListPresenterModule.class,InteractorModule.class, UseCaseModule.class})
public interface FriendsListComponent {
	void inject(FriendsListScreenFragment fragment);
}
