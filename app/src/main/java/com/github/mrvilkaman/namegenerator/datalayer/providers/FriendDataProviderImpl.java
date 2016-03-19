package com.github.mrvilkaman.namegenerator.datalayer.providers;

import android.util.Log;

import com.github.mrvilkaman.namegenerator.datalayer.entity.mapper.FriendEntityVkModelMapper;
import com.github.mrvilkaman.namegenerator.datalayer.store.LocalCacheItemType;
import com.github.mrvilkaman.namegenerator.datalayer.store.MemoryStorage;
import com.github.mrvilkaman.namegenerator.datalayer.store.VkStore;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;
import com.vk.sdk.api.model.VKList;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Zahar on 17.03.16.
 */
public class FriendDataProviderImpl implements FriendDataProvider {
	private final MemoryStorage memoryStorage;
	private final VkStore vkStore;
	private final FriendEntityVkModelMapper mapper;

	public FriendDataProviderImpl(VkStore vkStore, FriendEntityVkModelMapper mapper, MemoryStorage memoryStorage) {
		this.vkStore = vkStore;
		this.mapper = mapper;
		this.memoryStorage = memoryStorage;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Observable<List<Friend>> getFriendsLocal() {
		return Observable.just(memoryStorage.<List<Friend>>get(LocalCacheItemType.FRIENDS_LIST));
	}

	@Override
	public Observable<List<Friend>> getFriendsRemote() {
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
				})
				.doOnNext(friends -> memoryStorage.save(LocalCacheItemType.FRIENDS_LIST, friends));
	}

	@Override
	public Observable<Friend> getLastFriend() {
		return Observable.just(memoryStorage.get(LocalCacheItemType.FRIENDS));
	}

	@Override
	public void setLastFriend(Friend friend) {
		memoryStorage.save(LocalCacheItemType.FRIENDS,friend);
	}

	@Override
	public Observable<Friend> getFriendsById(long friendId) {
		return getFriendsLocal()
				.filter(list -> list != null)
				.concatWith(getFriendsRemote())
				.concatMap(Observable::from)
				.first(friend -> friend.getId() == friendId)
				.doOnNext(this::setLastFriend);
	}
}
