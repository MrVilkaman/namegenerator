package com.github.mrvilkaman.namegenerator.domainlayer.interactor;

import android.content.Intent;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;

/**
 * Created by root on 12.03.16.
 */
public interface VkLoginInteractor {

	void sendVkLoginRequest();

	void handleVkResponse(int requestCode, int resultCode, Intent data, VKCallback<VKAccessToken> callback);
}
