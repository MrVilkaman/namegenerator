package com.github.mrvilkaman.namegenerator.datalayer.store;

import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKList;

import rx.Observable;

/**
 * Created by Zahar on 18.03.16.
 */
public interface VkStore {

	Observable<VKResponse> getFriends();
}
