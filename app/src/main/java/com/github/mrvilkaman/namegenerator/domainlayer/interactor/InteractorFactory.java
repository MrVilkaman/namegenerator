package com.github.mrvilkaman.namegenerator.domainlayer.interactor;

import android.support.v4.app.Fragment;

import com.github.mrvilkaman.namegenerator.datalayer.interactor.TokenInteractorImpl;
import com.github.mrvilkaman.namegenerator.datalayer.interactor.VkLoginInteractorImpl;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SessionDataProvider;

import static com.github.mrvilkaman.namegenerator.domainlayer.providers.DataProvidersFactory.get;

/**
 * Created by Zahar on 16.03.16.
 */
public class InteractorFactory {

	public static TokenInteractor getTokenInteractor() {
		SessionDataProvider sessionDataProvider = get().getSessionDataProvider();
		return new TokenInteractorImpl(sessionDataProvider);
	}

	public static VkLoginInteractor getVkLoginInteractor(Fragment frag) {
		return new VkLoginInteractorImpl(frag);
	}
}
