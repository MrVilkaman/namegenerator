package com.github.mrvilkaman.namegenerator.presentationlayer.app;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zahar on 24.03.16.
 */
@Module
@Singleton
public class AppModule {

	@NonNull
	private Context context;

	public AppModule(@NonNull Context context) {
		this.context = context;
	}

	@Provides @NonNull @Singleton
	public Context provideContext() {
		return context;
	}

}
