package com.github.mrvilkaman.namegenerator.presentationlayer.fragment.hello;

import android.content.Intent;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;

/**
 * Created by root on 12.03.16.
 */
public interface IHelloModel {

	void sendVkLoginRequest();

	void handleVkResponse(int requestCode, int resultCode, Intent data, VKCallback<VKAccessToken> callback);
}
