package com.github.mrvilkaman.namegenerator.presentationlayer.activities;

import android.app.Application;

import com.vk.sdk.VKSdk;

/**
 * Created by root on 12.03.16.
 */
public class App extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		VKSdk.initialize(this);
	}
}
