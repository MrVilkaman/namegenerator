package com.github.mrvilkaman.namegenerator.domainlayer.providers;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zahar on 22.01.2016.
 */
public class MainSchedulersProvider implements SchedulersProvider {

	@Override
	public Scheduler mainThread() {
		return AndroidSchedulers.mainThread();
	}

	@Override
	public Scheduler io() {
		return Schedulers.io();
	}

	@Override
	public Scheduler computation() {
		return Schedulers.computation();
	}
}
