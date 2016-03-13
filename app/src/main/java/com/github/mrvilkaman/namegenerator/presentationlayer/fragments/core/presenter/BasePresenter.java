package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.presenter;

import android.content.Context;

import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.view.BaseView;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<V extends BaseView> {

	private CompositeSubscription subscriptions;
	private V view;

	protected void onViewBeforeDetached() {
	}

	public final <T> void subscribe(Observable<T> observable, Observer<T> observer) {
		subscriptions.add(observable.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(observer));
	}

	public final <T> void subscribe(Subscription observable) {
		subscriptions.add(observable);
	}

	protected void onViewAttached() {

	}

	protected void onViewDetached() {
	}

	public final V view() {
		return view;
	}

	public final void setView(V view) {
		if (view == null) {
			if (subscriptions != null) {
				subscriptions.unsubscribe();
			}

			if (this.view != null) {
				onViewBeforeDetached();
			}
			this.view = null;
			onViewDetached();
		} else {
			this.view = view;
			subscriptions = new CompositeSubscription();
			onViewAttached();
		}
	}

	public final Context getContext() {
		return view == null ? null : view.getContext();
	}

	public void handleError(Throwable throwable) {

		view().hideProgress();
		// Use in Retrofit
//		if (throwable instanceof RetrofitError) {
//			RetrofitError error = (RetrofitError) throwable;
//			if (error.getKind() == RetrofitError.Kind.NETWORK) {
//				view().showToast(R.string.dialog_internet_error);
//			} else if (error.getKind() == RetrofitError.Kind.HTTP) {
//				Response response = error.getResponse();
//				if (response != null) {
//					handleHttpError(error, response.getStatus());
//				} else {
//					view().showMessage(error.getMessage());
//				}
//			}
//			if (error.getKind() == RetrofitError.Kind.UNEXPECTED ||
//					error.getKind() == RetrofitError.Kind.CONVERSION) {
//				view().showMessage(error.getMessage());
//			}
//		} else
		{
			view().showToast(throwable.getMessage());
		}

	}

//	protected void handleHttpError(RetrofitError error, int status) {
//		switch (status) {
//			case 401:
//				view().showMessage(R.string.dialog_invalid_token);
//				break;
//		}
//	}
}
