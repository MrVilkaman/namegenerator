package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.friendslist;

import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.presenter.BasePresenter;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by root on 13.03.16.
 */

public class FriendsListPresenter extends BasePresenter<FriendsListView> {

	public void loadFiends() {

		view().bindFriends(Arrays.asList(new Friend(1,"Имя Фамилия"),new Friend(2,"Имя Фамилия"),new Friend(3,"Имя Фамилия")));
	}
}
