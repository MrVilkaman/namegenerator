package com.github.mrvilkaman.namegenerator.datalayer.providers;

import com.github.mrvilkaman.namegenerator.datalayer.LocalStorage;
import com.github.mrvilkaman.namegenerator.datalayer.LocalStorageImpl;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SessionDataProvider;

/**
 * Created by Zahar on 16.03.16.
 */
public class SessionDataProviderImpl implements SessionDataProvider {
	private final LocalStorage localStorage;

	public SessionDataProviderImpl(LocalStorage localStorage) {
		this.localStorage = localStorage;
	}

	@Override
	public String getToken() {
		return localStorage.getToken();
	}

	@Override
	public void saveToken(String token) {
		localStorage.saveToken(token);
	}
}
