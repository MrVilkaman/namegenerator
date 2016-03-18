package com.github.mrvilkaman.namegenerator.datalayer.interactor;

import android.text.TextUtils;

import com.github.mrvilkaman.namegenerator.domainlayer.interactor.TokenInteractor;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SessionDataProvider;

/**
 * Created by root on 13.03.16.
 */
public class TokenInteractorImpl implements TokenInteractor {

	private SessionDataProvider dataProvider;

	public TokenInteractorImpl(SessionDataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}

	@Override
	public void saveToken(String accessToken) {
		dataProvider.saveToken(accessToken);
	}

	@Override
	public boolean checkToken() {
		String token = dataProvider.getToken();
		return !TextUtils.isEmpty(token);
	}
}
