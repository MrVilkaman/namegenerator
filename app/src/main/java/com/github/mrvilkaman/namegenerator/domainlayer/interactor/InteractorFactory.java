package com.github.mrvilkaman.namegenerator.domainlayer.interactor;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.github.mrvilkaman.namegenerator.datalayer.interactor.TokenInteractorImpl;
import com.github.mrvilkaman.namegenerator.datalayer.interactor.VkLoginInteractorImpl;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.DataProviders;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.DataProvidersFactory;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.SessionDataProvider;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.HandleTokenUseCase;

import java.util.concurrent.TimeUnit;

import rx.Observable;

import static com.github.mrvilkaman.namegenerator.domainlayer.providers.DataProvidersFactory.get;

/**
 * Created by Zahar on 16.03.16.
 */
public class InteractorFactory {

	public static TokenInteractor getTokenInteractor() {
		SessionDataProvider sessionDataProvider = get().getSessionDataProvider();
		return new TokenInteractorImpl(sessionDataProvider){
			@Override
			public boolean checkToken() {
				return false;
			}

			@Override
			public void saveToken(String accessToken) {
			}
		};
	}

	public static VkLoginInteractor getVkLoginInteractor(Fragment frag) {
		return new VkLoginInteractorImpl(frag){
			@Override
			public void sendVkLoginRequest() {
			}

			@Override
			public HandleTokenUseCase handleVkResponse(int requestCode, int resultCode, Intent data) {
				return new HandleTokenUseCase(requestCode,resultCode,data, DataProvidersFactory.get().getSchedulersProvider()){
					@Override
					protected Observable<String> buildUseCaseObservable() {
						return Observable.just("qwer")
								.delay(5, TimeUnit.SECONDS);
					}
				};
			}
		};
	}
}
