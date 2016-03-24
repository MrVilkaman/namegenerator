package com.github.mrvilkaman.namegenerator.presentationlayer.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.vk.sdk.VKSdk;

/**
 * Created by root on 12.03.16.
 */
public class App extends Application {

	@NonNull
	private AppComponent appComponent;

	// Prevent need in a singleton (global) reference to the application object.
	@NonNull
	public static App get(@NonNull Context context) {
		return (App) context.getApplicationContext();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		appComponent = prepareApplicationComponent().build();
		appComponent.inject(this);

		VKSdk.initialize(this);

	}

	protected DaggerAppComponent.Builder prepareApplicationComponent() {
		return DaggerAppComponent.builder()
				.appModule(new AppModule(this));
	}

	@NonNull
	public AppComponent getAppComponent() {
		return appComponent;
	}
}
