package com.github.mrvilkaman.namegenerator.datalayer.providers;

import android.content.Context;

import com.github.mrvilkaman.namegenerator.datalayer.LocalStorageImpl;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.DataProviders;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SchedulersProvider;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SessionDataProvider;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by Zahar on 16.03.16.
 */
public class DataProvidersImpl implements DataProviders {

	private Context context;

	public DataProvidersImpl(Context context) {
		this.context = context;
	}

	@Override
	public SessionDataProvider getSessionDataProvider() {
		return new SessionDataProviderImpl(new LocalStorageImpl(context));
	}

	@Override
	public SchedulersProvider getSchedulersProvider() {
		return new MainSchedulersProvider();
	}

	@Override
	public FriendDataProvider getFriendDataProvider() {
		return () -> Observable.just(
				Arrays.asList(new Friend(1, "Имя Фамилия"), new Friend(2, "Имя Фамилия"), new Friend(3, "Имя Фамилия")))
				.delay(2, TimeUnit.SECONDS);
	}
}
