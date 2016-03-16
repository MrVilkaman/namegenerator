package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.hello;
/**
 * Created by root on 12.03.16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mrvilkaman.namegenerator.R;
import com.github.mrvilkaman.namegenerator.domainlayer.interactor.InteractorFactory;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.view.BaseFragment;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.friendslist.FriendsListScreenFragment;

import butterknife.Bind;
import butterknife.OnClick;

public class HelloScreenFragment extends BaseFragment<HelloScreenPresenter> implements HelloScreenView {

	@Bind(R.id.hello_message)
	TextView message;
	@Bind(R.id.hello_login_btn)
	Button btnLogin;

	public static HelloScreenFragment open() {
		return new HelloScreenFragment();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.layout_helloscreen_fragment;
	}

	@Override
	protected void onCreateView(View view, Bundle savedInstanceState) {

	}

	@Override
	public HelloScreenPresenter newPresenter() {
		return new HelloScreenPresenter(InteractorFactory.getVkLoginInteractor(this),InteractorFactory.getTokenInteractor());
	}

	@OnClick(R.id.hello_login_btn)
	void onClick() {
		getPresenter().onClickBtn();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		getPresenter().onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void showViewNext() {
		message.setText(R.string.hello_title_next);
		btnLogin.setText(R.string.hello_btn_next);
	}

	@Override
	public void showViewLogin() {
		btnLogin.setText(R.string.hello_btn_login);
		message.setText(R.string.hello_title_need_login);
	}

	@Override
	public void goToMainScreen() {
		showFragmentWithoutBackStack(FriendsListScreenFragment.open());
	}
}