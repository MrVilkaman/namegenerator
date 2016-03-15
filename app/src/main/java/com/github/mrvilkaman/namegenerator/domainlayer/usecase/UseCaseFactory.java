package com.github.mrvilkaman.namegenerator.domainlayer.usecase;

import com.github.mrvilkaman.namegenerator.domainlayer.providers.DataProviders;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.DataProvidersFactory;

/**
 * Created by Zahar on 16.03.16.
 */
public class UseCaseFactory {

	public static GetUserListUseCase getUserListInteractor(){
		DataProviders dataProviders = DataProvidersFactory.get();
		return new GetUserListUseCase(dataProviders.getFriendDataProvider(), dataProviders.getSchedulersProvider());
	}
}
