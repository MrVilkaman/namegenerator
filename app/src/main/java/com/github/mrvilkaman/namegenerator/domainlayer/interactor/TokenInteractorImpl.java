package com.github.mrvilkaman.namegenerator.domainlayer.interactor;

import android.text.TextUtils;

import com.github.mrvilkaman.namegenerator.datalayer.LocalStorage;
import com.github.mrvilkaman.namegenerator.datalayer.LocalStorageImpl;

/**
 * Created by root on 13.03.16.
 */
public class TokenInteractorImpl implements TokenInteractor {

	private LocalStorage localStorage;

	public TokenInteractorImpl(LocalStorage localStorage) {
		this.localStorage = localStorage;
	}

	@Override
	public void saveToken(String accessToken) {
		localStorage.saveToken(accessToken);
	}

	@Override
	public boolean checkToken() {
		String token = localStorage.getToken();
		return !TextUtils.isEmpty(token);
	}
}
