package com.github.mrvilkaman.namegenerator.datalayer.store;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKList;

import rx.Observable;

/**
 * Created by Zahar on 18.03.16.
 */
public class VkStoreImpl implements VkStore{

	@Override
	public Observable<VKList> getFriends(String fields) {
		Observable.OnSubscribe<VKList> subscribe = subscriber -> {
			VKRequest result = VKApi.friends()
					.get(VKParameters.from(VKApiConst.FIELDS, fields));
			result.executeSyncWithListener(new VKRequest.VKRequestListener() {
				@Override
				public void onComplete(VKResponse response) {
					if (!subscriber.isUnsubscribed()) {
						subscriber.onNext((VKList) response.parsedModel);
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
