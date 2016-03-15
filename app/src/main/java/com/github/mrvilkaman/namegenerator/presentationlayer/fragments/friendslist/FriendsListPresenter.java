package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.friendslist;

import com.github.mrvilkaman.namegenerator.domainlayer.providers.DefaultSubscriber;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.GetUserListInteractor;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.presenter.BasePresenter;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;

import java.util.List;

/**
 * Created by root on 13.03.16.
 */

public class FriendsListPresenter extends BasePresenter<FriendsListView> {

	private final GetUserListInteractor getUserList;

	public FriendsListPresenter(GetUserListInteractor getUserList) {
		this.getUserList = getUserList;
	}

	public void loadFiends() {
		view().showProgress();
		getUserList.execute(new UserListSubscriber());

//		view().renderFriendsList(Arrays.asList(new Friend(1,"Имя Фамилия"),new Friend(2,"Имя Фамилия"),new Friend(3,"Имя Фамилия")));
	}
	private final class UserListSubscriber extends DefaultSubscriber<List<Friend>> {

		@Override
		public void onNext(List<Friend> friends) {
			view().renderFriendsList(friends);
		}

		@Override
		public void onError(Throwable e) {
			view().hideProgress();
			view().showError(e);
		}

		@Override
		public void onCompleted() {
			view().hideProgress();
		}
	}
}
