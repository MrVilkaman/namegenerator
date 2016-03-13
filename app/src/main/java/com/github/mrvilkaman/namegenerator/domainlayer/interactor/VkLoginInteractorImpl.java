package com.github.mrvilkaman.namegenerator.domainlayer.interactor;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKServiceActivity;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.model.VKScopes;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by root on 12.03.16.
 */
public class VkLoginInteractorImpl implements VkLoginInteractor {
	private static final String KEY_TYPE = "arg1";
	private static final String KEY_SCOPE_LIST = "arg2";
	private static final String KEY_REQUEST = "arg3";
	private static final String KEY_SDK_CUSTOM_INITIALIZE = "arg4";

	private Fragment fragment;

	public VkLoginInteractorImpl(Fragment fragment) {
		this.fragment = fragment;
	}

	@Override
	public void sendVkLoginRequest() {
//		VKSdk.login(fragment, VKScopes.FRIENDS);

		ArrayList<String> scopeList = new ArrayList<>(Arrays.asList(VKScopes.FRIENDS));
		if (!scopeList.contains(VKScope.OFFLINE)) {
			scopeList.add(VKScope.OFFLINE);
		}

		Intent intent = new Intent(fragment.getActivity(), VKServiceActivity.class);
		intent.putStringArrayListExtra(KEY_SCOPE_LIST, scopeList);
		intent.putExtra(KEY_SDK_CUSTOM_INITIALIZE, VKSdk.isCustomInitialize());
		intent.putExtra(KEY_TYPE, VKServiceActivity.VKServiceType.Authorization.name());
		fragment.startActivityForResult(intent, VKServiceActivity.VKServiceType.Authorization.getOuterCode());
	}

	@Override
	public void handleVkResponse(int requestCode, int resultCode, Intent data, VKCallback<VKAccessToken> callback) {
		VKSdk.onActivityResult(requestCode, resultCode, data, callback);
	}
}
