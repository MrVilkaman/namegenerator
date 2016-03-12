package com.github.mrvilkaman.namegenerator.presentationlayer.fragment.hello;

import android.content.Intent;

import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.presenter.BasePresenter;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.api.VKError;

/**
 * Created by root on 12.03.16.
 */

public class HelloScreenPresenter extends BasePresenter<HelloScreenView> {

	private IHelloModel model;

	public HelloScreenPresenter(IHelloModel model) {
		this.model = model;
	}

	public void tryLogin() {
		model.sendVkLoginRequest();
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		model.handleVkResponse(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
			@Override
			public void onResult(VKAccessToken res) {
// save token
// res.accessToken
				getView().showMessage(String.format("token = %s id = %s email = %s", res.accessToken, res.userId, res.email));
				// Пользователь успешно авторизовался

			}

			@Override
			public void onError(VKError error) {
				getView().showMessage(error.errorMessage);
				// Произошла ошибка авторизации (например, пользователь запретил авторизацию)
			}
		});
	}
}
