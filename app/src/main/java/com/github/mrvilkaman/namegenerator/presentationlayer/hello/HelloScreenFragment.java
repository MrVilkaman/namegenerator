package com.github.mrvilkaman.namegenerator.presentationlayer.hello;
/**
 * Created by root on 12.03.16.
 */

import android.os.Bundle;
import android.view.View;

import com.github.mrvilkaman.namegenerator.R;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.view.BaseFragment;
import com.github.mrvilkaman.namegenerator.presentationlayer.hello.HelloScreenView;

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
		return new HelloScreenPresenter();
	}
}