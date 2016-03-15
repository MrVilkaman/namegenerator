package com.github.mrvilkaman.namegenerator.domainlayer.usecase;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by root on 15.03.16.
 */
public abstract class UseCase {

	private final Scheduler subscribeOn;
	private final Scheduler observeOn;

	private Subscription subscription = Subscriptions.empty();

	public UseCase(Scheduler subscribeOn, Scheduler observeOn) {
		this.subscribeOn = subscribeOn;
		this.observeOn = observeOn;
	}

	protected abstract Observable buildUseCaseObservable();

	@SuppressWarnings("unchecked")
	public void execute(Subscriber UseCaseSubscriber) {
		this.subscription = this.buildUseCaseObservable()
				.subscribeOn(subscribeOn)
				.observeOn(observeOn)
				.subscribe(UseCaseSubscriber);
	}

	public void unsubscribe() {
		if (!subscription.isUnsubscribed()) {
			subscription.unsubscribe();
		}
	}
}
