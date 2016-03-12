package com.github.mrvilkaman.namegenerator.presentationlayer.fragment.hello;
/**
 * Created by root on 12.03.16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.mrvilkaman.namegenerator.R;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.view.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

public class HelloScreenFragment extends BaseFragment<HelloScreenPresenter> implements HelloScreenView {

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
		return new HelloScreenPresenter(new HelloModel(this));
	}

	@OnClick(R.id.hello_login_btn)
	void onLogin(){
		getPresenter().tryLogin();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		getPresenter().onActivityResult(requestCode, resultCode, data);
	}
}