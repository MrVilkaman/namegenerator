package com.github.mrvilkaman.namegenerator.datalayer.providers;

import com.github.mrvilkaman.namegenerator.datalayer.entity.mapper.FriendEntityVkModelMapper;
import com.github.mrvilkaman.namegenerator.datalayer.store.VkStore;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;
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
	private VkStore vkStore;
	private FriendEntityVkModelMapper mapper;

	public FriendDataProviderImpl(VkStore vkStore, FriendEntityVkModelMapper mapper) {
		this.vkStore = vkStore;
		this.mapper = mapper;
	}

	@Override
	public Observable<List<Friend>> getFriends() {
		return vkStore.getFriends("first_name,last_name,city")
				.map((Func1<VKList, List<Friend>>) o -> {
					ArrayList<Friend> list = new ArrayList<>();
					for (int i = 0; i < o.size(); i++) {
						try {
							list.add(mapper.transform(o.get(i)));
						} catch (JSONException e) {
							list.add(new Friend(-1, "parse error!"));
						}
					}
					return list;
				});
	}
}
