package com.github.mrvilkaman.namegenerator.datalayer.providers;

import android.content.Context;
import android.support.annotation.NonNull;

import com.github.mrvilkaman.namegenerator.datalayer.entity.mapper.FriendEntityVkModelMapper;
import com.github.mrvilkaman.namegenerator.datalayer.store.LocalStorage;
import com.github.mrvilkaman.namegenerator.datalayer.store.LocalStorageImpl;
import com.github.mrvilkaman.namegenerator.datalayer.store.MemoryStorage;
import com.github.mrvilkaman.namegenerator.datalayer.store.MemoryStorageImpl;
import com.github.mrvilkaman.namegenerator.datalayer.store.VkStore;
import com.github.mrvilkaman.namegenerator.datalayer.store.VkStoreImpl;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SchedulersProvider;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SessionDataProvider;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zahar on 24.03.16.
 */
@Module
@Singleton
public class ProvidersModule {

	@Singleton
	@Provides
	SessionDataProvider provideSessionDataProvider(LocalStorage localStorage, MemoryStorage memoryStorage) {
		return new SessionDataProviderImpl(localStorage,memoryStorage);
	}

	@Singleton
	@Provides
	SchedulersProvider provideSchedulersProvider() {
		return new MainSchedulersProvider();
	}

	@Singleton
	@Provides
	@NonNull
	LocalStorage provideLocalStorage(Context context) {
		return new LocalStorageImpl(context);
	}

	@Singleton
	@Provides
	FriendDataProvider provideFriendDataProvider(VkStore vkStore, FriendEntityVkModelMapper mapper, MemoryStorage memoryStorage) {
		return new FriendDataProviderImpl(vkStore, mapper,memoryStorage);
	}

	@Singleton
	@Provides
	@NonNull
	MemoryStorage provideMemoryStorage() {
		return new MemoryStorageImpl();
	}

	@Singleton
	@Provides
	@NonNull
	VkStore provideVkStore() {
		return new VkStoreImpl();
	}

	@Singleton
	@Provides
	@NonNull
	FriendEntityVkModelMapper provideMapper(Gson gson) {
		return new FriendEntityVkModelMapper(gson);
	}

	@Singleton
	@Provides
	@NonNull
	Gson provideGson() {
		return new Gson();
	}
}
