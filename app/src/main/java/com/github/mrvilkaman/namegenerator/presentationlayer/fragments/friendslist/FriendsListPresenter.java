package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.friendslist;

import com.github.mrvilkaman.namegenerator.datalayer.interactor.SaveLocalUserInteractorImpl;
import com.github.mrvilkaman.namegenerator.domainlayer.interactor.SaveLocalUserInteractor;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.UseCase;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.UseCaseModule;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.presenter.BasePresenter;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.view.BaseView;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;
import com.github.mrvilkaman.namegenerator.presentationlayer.utils.LoadSubscriber;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by root on 13.03.16.
 */

public class FriendsListPresenter extends BasePresenter<FriendsListView> {

	private final UseCase<List<Friend>> getUserListUseCase;
	private final SaveLocalUserInteractor saveLocalUserInteractor;

	@Inject
	public FriendsListPresenter(@Named(UseCaseModule.USER_LIST) UseCase<List<Friend>> getUserListUseCase, SaveLocalUserInteractor saveLocalUserInteractor) {
		this.getUserListUseCase = getUserListUseCase;
		this.saveLocalUserInteractor = saveLocalUserInteractor;
	}

	public void loadFiends() {
		view().showProgress();
		getUserListUseCase.execute(new UserListSubscriber(view()));
	}

	@Override
	protected void onViewDetached() {
		getUserListUseCase.unsubscribe();
	}

	public void openFriend(Friend friend) {
		saveLocalUserInteractor.saveFriend(friend);
		view().goToInfoScreen(friend.getId());
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
