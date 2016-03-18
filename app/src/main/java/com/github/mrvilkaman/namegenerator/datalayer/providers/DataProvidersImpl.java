package com.github.mrvilkaman.namegenerator.datalayer.providers;

import android.content.Context;

import com.github.mrvilkaman.namegenerator.datalayer.store.LocalStorageImpl;
import com.github.mrvilkaman.namegenerator.datalayer.entity.mapper.FriendEntityVkModelMapper;
import com.github.mrvilkaman.namegenerator.datalayer.store.MemoryStorage;
import com.github.mrvilkaman.namegenerator.datalayer.store.MemoryStorageImpl;
import com.github.mrvilkaman.namegenerator.datalayer.store.VkStoreImpl;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.DataProviders;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SchedulersProvider;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SessionDataProvider;

/**
 * Created by Zahar on 16.03.16.
 */
public class DataProvidersImpl implements DataProviders {

	private Context context;
	private MemoryStorage memoryStorage = new MemoryStorageImpl();

	public DataProvidersImpl(Context context) {
		this.context = context;
	}

	@Override
	public SessionDataProvider getSessionDataProvider() {
		//// TODO: 18.03.16 maybe LocalStorageImpl singleton ?
		return new SessionDataProviderImpl(new LocalStorageImpl(context));
	}

	@Override
	public SchedulersProvider getSchedulersProvider() {
		return new MainSchedulersProvider();
	}

	@Override
	public FriendDataProvider getFriendDataProvider() {
		//// TODO: 17.03.16 maybe FriendEntityVkModelMapper and VkStoreImpl singleton
		return new FriendDataProviderImpl(new VkStoreImpl(),new FriendEntityVkModelMapper(),memoryStorage);
	}
}
