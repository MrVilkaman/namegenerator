package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.friendslist;
/**
 * Created by root on 13.03.16.
 */

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.mrvilkaman.namegenerator.R;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.DataProviders;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.DataProvidersFactory;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.GetUserListInteractor;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.MySimpleAdapter;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.view.BaseFragment;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;

import java.util.List;

import butterknife.Bind;

public class FriendsListScreenFragment extends BaseFragment<FriendsListPresenter> implements FriendsListView {

	@Bind(R.id.recycler_view)
	RecyclerView recyclerView;
	private MySimpleAdapter<Friend> adapter;

	public static FriendsListScreenFragment open() {
		return new FriendsListScreenFragment();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.layout_friendslistscreen_fragment;
	}

	@Override
	protected void onCreateView(View view, Bundle savedInstanceState) {
		setupRecyclerView();
	}

	private void setupRecyclerView() {
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setHasFixedSize(true);
		if (adapter == null) {
			adapter = new MySimpleAdapter<>();
			loadData();
		}
		adapter.setOnClick(this::openFriend);
		recyclerView.setAdapter(adapter);
	}

	private void openFriend(Friend friend) {
		showMessage(friend.toString());
	}

	private void loadData() {
		getPresenter().loadFiends();
	}

	@Override
	public void renderFriendsList(List<Friend> friends) {
		adapter.setItems(friends);
	}

	@Override
	public FriendsListPresenter newPresenter() {
		DataProviders dataProviders = DataProvidersFactory.get();
		return new FriendsListPresenter(new GetUserListInteractor(dataProviders.getFriendDataProvider(), dataProviders.getSchedulersProvider()));
	}
}