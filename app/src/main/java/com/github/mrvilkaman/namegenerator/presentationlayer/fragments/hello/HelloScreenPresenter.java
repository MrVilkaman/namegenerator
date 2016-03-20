package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.hello;

import android.content.Intent;
import android.util.Log;

import com.github.mrvilkaman.namegenerator.datalayer.providers.DefaultSubscriber;
import com.github.mrvilkaman.namegenerator.domainlayer.interactor.TokenInteractor;
import com.github.mrvilkaman.namegenerator.domainlayer.interactor.VkLoginInteractor;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.HandleTokenUseCase;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.presenter.BasePresenter;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.view.BaseView;
import com.github.mrvilkaman.namegenerator.presentationlayer.utils.LoadSubscriber;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiModel;

/**
 * Created by root on 12.03.16.
 */

public class HelloScreenPresenter extends BasePresenter<HelloScreenView> {

	private VkLoginInteractor loginInteractor;
	private TokenInteractor tokenInteractor;
	private HandleTokenUseCase sub;

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

	@Override
	protected void onViewDetached() {
		if (sub != null) {
			sub.unsubscribe();
			sub = null;
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		view().showProgress();
		sub = loginInteractor.handleVkResponse(requestCode, resultCode, data);
		sub.execute(new HandleResponse(view()));
	}

	private class HandleResponse extends LoadSubscriber<String> {
		public HandleResponse(BaseView view) {
			super(view);
		}

		@Override
		public void onNext(String token) {
			tokenInteractor.saveToken(token);
			view().showViewNext();
			view().showMessage(token);
		}
	}
}
