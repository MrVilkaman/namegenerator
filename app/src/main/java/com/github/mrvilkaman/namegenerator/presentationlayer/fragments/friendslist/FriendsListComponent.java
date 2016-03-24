package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.friendslist;

import dagger.Component;

/**
 * Created by Zahar on 24.03.16.
 */
@Component(modules = FriendsListPresenterModule.class)
public interface FriendsListComponent {
	void inject(FriendsListScreenFragment fragment);
}
