package com.github.mrvilkaman.namegenerator.datalayer.providers;

import android.content.Context;

import com.github.mrvilkaman.namegenerator.datalayer.entity.mapper.FriendEntityVkModelMapper;
import com.github.mrvilkaman.namegenerator.datalayer.store.LocalStorageImpl;
import com.github.mrvilkaman.namegenerator.datalayer.store.MemoryStorage;
import com.github.mrvilkaman.namegenerator.datalayer.store.MemoryStorageImpl;
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
	private static final MemoryStorage memoryStorage;
	private static final Gson gson;

	static{
		memoryStorage = new MemoryStorageImpl();
		gson = new Gson();
	}

	@Singleton
	@Provides
	public SessionDataProvider getSessionDataProvider(Context context) {
		return new SessionDataProviderImpl(new LocalStorageImpl(context),memoryStorage);
	}

	@Singleton
	@Provides
	public SchedulersProvider getSchedulersProvider() {
		return new MainSchedulersProvider();
	}
	@Singleton
	@Provides
	public FriendDataProvider getFriendDataProvider() {
		return new FriendDataProviderImpl(new VkStoreImpl(),new FriendEntityVkModelMapper(gson),memoryStorage);
	}
}
