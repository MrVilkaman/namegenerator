package com.github.mrvilkaman.namegenerator.presentationlayer.app;

import android.app.Application;
import android.support.annotation.NonNull;

import com.github.mrvilkaman.namegenerator.domainlayer.providers.DataProvidersFactory;
import com.github.mrvilkaman.namegenerator.datalayer.providers.DataProvidersImpl;
import com.vk.sdk.VKSdk;

/**
 * Created by root on 12.03.16.
 */
public class App extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		VKSdk.initialize(this);

		DataProvidersFactory.init(getSourceFactory());
	}

	@NonNull
	protected DataProvidersImpl getSourceFactory() {
		return new DataProvidersImpl(this);
	}
}
