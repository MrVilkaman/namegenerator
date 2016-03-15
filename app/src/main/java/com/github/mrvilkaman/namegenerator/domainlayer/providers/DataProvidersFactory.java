package com.github.mrvilkaman.namegenerator.domainlayer.providers;

/**
 * Created by Zahar on 16.03.16.
 */
public class DataProvidersFactory implements DataProviders {


	private static DataProvidersFactory factory;
	private static DataProviders sourceFactory;
	private SchedulersProvider schedulersProvider;
	private FriendDataProvider friendDataProvider;

	private DataProvidersFactory() {
	}

	public static DataProvidersFactory get() {
		if (sourceFactory == null) {
			throw new RuntimeException("call setSourceFactory method first");
		}
		return factory;
	}

	public static void init(DataProviders sourceFactory) {
		if (factory == null) {
			factory = new DataProvidersFactory();
		}else{
			factory.clear();
		}
		DataProvidersFactory.sourceFactory = sourceFactory;
	}

	private void clear() {
		schedulersProvider = null;
		friendDataProvider = null;
	}

	@Override
	public SchedulersProvider getSchedulersProvider() {
		if (schedulersProvider == null) {
			schedulersProvider = sourceFactory.getSchedulersProvider();
		}
		return schedulersProvider;
	}

	@Override
	public FriendDataProvider getFriendDataProvider() {
		if (friendDataProvider == null) {
			friendDataProvider = sourceFactory.getFriendDataProvider();
		}
		return friendDataProvider;
	}
}
