package com.github.mrvilkaman.namegenerator.datalayer.store;

import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import rx.Observable;

/**
 * Created by Zahar on 18.03.16.
 */
public class VkStoreImpl implements VkStore{


	@Override
	public Observable<VKResponse> getFriends() {

		Observable.OnSubscribe<VKResponse> subscribe = subscriber -> {
			VKRequest vkRequest = new VKRequest("execute.newFunc");
			vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
				@Override
				public void onComplete(VKResponse response) {
					if (!subscriber.isUnsubscribed()) {
						subscriber.onNext(response);
						subscriber.onCompleted();
					}
				}

				@Override
				public void onError(VKError error) {
					if (!subscriber.isUnsubscribed()) {
						subscriber.onError(error.apiError.httpError);
					}
				}
			});
		};
		return Observable.create(subscribe);
	}
}
