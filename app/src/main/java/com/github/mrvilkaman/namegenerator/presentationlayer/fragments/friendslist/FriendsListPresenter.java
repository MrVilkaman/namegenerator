package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.friendslist;

import com.github.mrvilkaman.namegenerator.domainlayer.usecase.UseCase;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.presenter.BasePresenter;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.view.BaseView;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;
import com.github.mrvilkaman.namegenerator.presentationlayer.utils.LoadSubscriber;

import java.util.List;

/**
 * Created by root on 13.03.16.
 */

public class FriendsListPresenter extends BasePresenter<FriendsListView> {

	private final UseCase getUserListUseCase;

	public FriendsListPresenter(UseCase getUserListUseCase) {
		this.getUserListUseCase = getUserListUseCase;
	}

	public void loadFiends() {
		view().showProgress();
		getUserListUseCase.execute(new UserListSubscriber(view()));
	}

	@Override
	protected void onViewDetached() {
		getUserListUseCase.unsubscribe();
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
