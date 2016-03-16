package com.github.mrvilkaman.namegenerator.datalayer.providers;

import android.util.Log;

import com.github.mrvilkaman.namegenerator.datalayer.entity.mapper.FriendEntityVkModelMapper;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKList;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Zahar on 17.03.16.
 */
public class FriendDataProviderImpl implements FriendDataProvider {
	private FriendEntityVkModelMapper mapper;

	public FriendDataProviderImpl(FriendEntityVkModelMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Observable<List<Friend>> getFriends() {
		Observable.OnSubscribe<VKList> subscribe = subscriber -> {
			VKRequest result = VKApi.friends()
					.get(VKParameters.from(VKApiConst.FIELDS, "first_name,last_name,city"));
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
					Log.e(FriendDataProviderImpl.class.getSimpleName(), "getFriends\n" + error.toString());
					if (!subscriber.isUnsubscribed()) {
						subscriber.onError(error.apiError.httpError);
					}
				}
			});
		};
		return Observable.create(subscribe)
				.map((Func1<VKList, List<Friend>>) o -> {
					ArrayList<Friend> list = new ArrayList<>();
					for (int i = 0; i < o.size(); i++) {
						try {
							list.add(mapper.transform(o.get(i)));
						} catch (JSONException e) {
							// ingrore
							list.add(new Friend(-1, "parse error!"));
						}
					}
					return list;
				});
	}
}
