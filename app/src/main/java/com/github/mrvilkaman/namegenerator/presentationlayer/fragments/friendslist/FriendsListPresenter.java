package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.friendslist;

import com.github.mrvilkaman.namegenerator.domainlayer.providers.DefaultSubscriber;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.GetUserListInteractor;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.presenter.BasePresenter;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.view.BaseView;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;
import com.github.mrvilkaman.namegenerator.presentationlayer.utils.LoadSubscriber;

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
		getUserList.execute(new UserListSubscriber(view()));
	}

	@Override
	protected void onViewDetached() {
		getUserList.unsubscribe();
	}

	private final class UserListSubscriber extends LoadSubscriber<List<Friend>> {

		public UserListSubscriber(BaseView view) {
			super(view);
		}

		@Override
		public void onNext(List<Friend> friends) {
			view().renderFriendsList(friends);
		}
	}
}
