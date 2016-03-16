package com.github.mrvilkaman.namegenerator.domainlayer.usecase;

import android.content.Intent;
import android.util.Log;

import com.github.mrvilkaman.namegenerator.domainlayer.providers.SchedulersProvider;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKList;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Zahar on 17.03.16.
 */
public class HandleTokenUseCase extends UseCase<String> {

	private final int requestCode;
	private final int resultCode;
	private final Intent data;

	public HandleTokenUseCase(int requestCode, int resultCode, Intent data, SchedulersProvider provider) {
		super(provider.computation(), provider.mainThread());
		this.requestCode = requestCode;
		this.resultCode = resultCode;
		this.data = data;
	}

	@Override
	protected Observable<String> buildUseCaseObservable() {
		Observable.OnSubscribe<VKAccessToken> subscribe = subscriber -> {
			VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
				@Override
				public void onResult(VKAccessToken res) {
					if (!subscriber.isUnsubscribed()) {
						subscriber.onNext(res);
						subscriber.onCompleted();
					}
				}

				@Override
				public void onError(VKError error) {
					Log.e(HandleTokenUseCase.class.getSimpleName(),error.toString());
					subscriber.onError(error.httpError);
				}
			});
		};
		return Observable.create(subscribe)
				.map(vkAccessToken -> vkAccessToken.accessToken);
	}
}
