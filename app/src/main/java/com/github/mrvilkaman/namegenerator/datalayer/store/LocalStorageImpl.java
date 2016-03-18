package com.github.mrvilkaman.namegenerator.datalayer.store;

import android.content.Context;

/**
 * Created by root on 12.03.16.
 */
public class LocalStorageImpl implements LocalStorage {


	private static final String PREF = "pref";
	private static final String TOKEN = "token";
	private Context context;

	private String token;

	public LocalStorageImpl(Context context) {
		this.context = context.getApplicationContext();
	}

	@Override
	public String getToken() {
		if (token != null) {
			return token;
		}
		token = context.getSharedPreferences(PREF, Context.MODE_PRIVATE).getString(TOKEN, null);
		return token;
	}

	@Override
	public void saveToken(String token) {
		this.token = token;
		context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
				.edit()
				.putString(TOKEN, token)
				.apply();
	}
}
