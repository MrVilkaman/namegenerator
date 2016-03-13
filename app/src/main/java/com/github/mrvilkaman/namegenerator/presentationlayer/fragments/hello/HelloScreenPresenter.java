package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.hello;

import android.content.Intent;

import com.github.mrvilkaman.namegenerator.domainlayer.interactor.TokenInteractor;
import com.github.mrvilkaman.namegenerator.domainlayer.interactor.VkLoginInteractor;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.presenter.BasePresenter;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.api.VKError;

/**
 * Created by root on 12.03.16.
 */

public class HelloScreenPresenter extends BasePresenter<HelloScreenView> {

	private VkLoginInteractor loginInteractor;
	private TokenInteractor tokenInteractor;

	public HelloScreenPresenter(VkLoginInteractor model,TokenInteractor tokenInteractor) {
		this.loginInteractor = model;
		this.tokenInteractor = tokenInteractor;
	}

	@Override
	protected void onViewAttached() {
		if (tokenInteractor.checkToken()) {
			view().showViewNext();
		}else{
			view().showViewLogin();
		}
	}

	public void onClickBtn() {
		if (tokenInteractor.checkToken()) {
			view().goToMainScreen();
		}else{
			loginInteractor.sendVkLoginRequest();
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		//// TODO: 13.03.16 user rxjava
		loginInteractor.handleVkResponse(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
			@Override
			public void onResult(VKAccessToken res) {
				tokenInteractor.saveToken(res.accessToken);
				view().showViewNext();
				view().showMessage(String.format("token = %s id = %s email = %s", res.accessToken, res.userId, res.email));
				// Пользователь успешно авторизовался

			}

			@Override
			public void onError(VKError error) {
				view().showMessage(error.errorMessage);
				// Произошла ошибка авторизации (например, пользователь запретил авторизацию)
			}
		});
	}
}
