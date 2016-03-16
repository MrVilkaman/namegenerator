package com.github.mrvilkaman.namegenerator.domainlayer.interactor;

import android.content.Intent;

import com.github.mrvilkaman.namegenerator.domainlayer.usecase.HandleTokenUseCase;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;

import rx.Observable;

/**
 * Created by root on 12.03.16.
 */
public interface VkLoginInteractor {

	void sendVkLoginRequest();

	HandleTokenUseCase handleVkResponse(int requestCode, int resultCode, Intent data);
}
