package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.friendslist;
/**
 * Created by root on 13.03.16.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.mrvilkaman.namegenerator.R;
import com.github.mrvilkaman.namegenerator.presentationlayer.app.App;
import com.github.mrvilkaman.namegenerator.presentationlayer.app.AppComponent;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.MySimpleAdapter;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.view.BaseFragment;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.info.InfoScreenFragment;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class FriendsListScreenFragment extends BaseFragment<FriendsListPresenter> implements FriendsListView {

	@Bind(R.id.recycler_view)
	RecyclerView recyclerView;

	@Bind(R.id.parent)
	SwipeRefreshLayout refreshLayout;

	private MySimpleAdapter<Friend> adapter;

	public static FriendsListScreenFragment open() {
		return new FriendsListScreenFragment();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppComponent appComponent = App.get(getActivity())
				.getAppComponent();
		DaggerFriendsListComponent.builder()
				.appComponent(appComponent)
				.build().inject(this);
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
		refreshLayout.setOnRefreshListener(this::loadData);
		if (adapter == null) {
			adapter = new MySimpleAdapter<>();
			refreshLayout.post(this::loadData);
		}
		adapter.setOnClick(this::openFriend);
		recyclerView.setAdapter(adapter);

	}

	private void openFriend(Friend friend) {
		getPresenter().openFriend(friend);
	}

	private void loadData() {
		showProgress();
		getPresenter().loadFiends();
	}

	@Override
	public void goToInfoScreen(long id) {
		showFragment(InfoScreenFragment.open(id));
	}

	@Override
	public void renderFriendsList(List<Friend> friends) {
		adapter.setItems(friends);
	}

	@Override
	public void showProgress() {
		refreshLayout.setRefreshing(true);
	}

	@Override
	public void hideProgress() {
		refreshLayout.setRefreshing(false);
	}
}