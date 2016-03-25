package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.info;
/**
 * Created by Zahar on 18.03.16.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.github.mrvilkaman.namegenerator.BuildConfig;
import com.github.mrvilkaman.namegenerator.R;
import com.github.mrvilkaman.namegenerator.presentationlayer.app.App;
import com.github.mrvilkaman.namegenerator.presentationlayer.app.AppComponent;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.view.BaseFragment;

import javax.inject.Inject;

import butterknife.Bind;

public class InfoScreenFragment extends BaseFragment<InfoPresenter> implements InfoView {

	private static final String EXTRA_ID = "id";
	@Bind(R.id.info_name)
	TextView contentView;

	public static InfoScreenFragment open(long id) {
		InfoScreenFragment fragment = new InfoScreenFragment();
		Bundle args = new Bundle();
		args.putLong(EXTRA_ID, id);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected void daggerInject() {
		DaggerInfoComponent.builder()
				.appComponent(getAppComponent())
				.build().inject(this);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.layout_infoscreen_fragment;
	}

	@Override
	protected void onCreateView(View view, Bundle savedInstanceState) {
		getPresenter().generate();
	}


	@Override
	public void bindContent(String text) {
		contentView.setText(text);
	}

	@Override
	public long getFriendId() {
		return getArguments().getLong(EXTRA_ID);
	}
}