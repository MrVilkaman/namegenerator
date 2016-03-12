package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.view;


import android.content.Context;

import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.presenter.BasePresenter;


public interface BaseView {

    Context getContext();
    BasePresenter getPresenter();

    void showProgress();
    void hideProgress();

    void showError(Throwable throwable);

    void showMessage(int testId);
    void showMessage(String text);

    void showToast(int resId);
    void showToast(String message);
}
