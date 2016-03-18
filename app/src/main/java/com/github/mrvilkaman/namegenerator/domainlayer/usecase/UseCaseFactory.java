package com.github.mrvilkaman.namegenerator.domainlayer.usecase;

import com.github.mrvilkaman.namegenerator.domainlayer.providers.DataProviders;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.DataProvidersFactory;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by Zahar on 16.03.16.
 */
public class UseCaseFactory {

	public static GetUserListUseCase getUserListUseCase(){
		DataProviders dataProviders = DataProvidersFactory.get();
		return new GetUserListUseCase(dataProviders.getFriendDataProvider(), dataProviders.getSchedulersProvider());
	}
}
