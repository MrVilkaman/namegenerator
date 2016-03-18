package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.info;
/**
 * Created by Zahar on 18.03.16.
 */

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mrvilkaman.namegenerator.R;
import com.github.mrvilkaman.namegenerator.domainlayer.nametemplates.NameTemplate;
import com.github.mrvilkaman.namegenerator.domainlayer.nametemplates.StarWarsNameTemplate;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.DataProviders;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.DataProvidersFactory;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.GenerateNameUseCase;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.view.BaseFragment;

import butterknife.Bind;

public class InfoScreenFragment extends BaseFragment<InfoPresenter> implements InfoView {

	private static final String EXTRA_ID = "id";
	@Bind(R.id.info_name)
	TextView contentView;

	public static InfoScreenFragment open(long id) {
		InfoScreenFragment fragment = new InfoScreenFragment();
		Bundle args = new Bundle();
		args.putLong(EXTRA_ID,id);
		fragment.setArguments(args);
		return fragment;
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
	public InfoPresenter newPresenter() {
		NameTemplate template = new StarWarsNameTemplate();
		DataProviders providers = DataProvidersFactory.get();
		return new InfoPresenter(new GenerateNameUseCase(getFriendId(),template,providers.getFriendDataProvider(),providers.getSchedulersProvider()));
	}

	@Override
	public void bindContent(String text){
		contentView.setText(text);
	}

	@Override
	public long getFriendId(){
		return getArguments().getLong(EXTRA_ID);
	}
}