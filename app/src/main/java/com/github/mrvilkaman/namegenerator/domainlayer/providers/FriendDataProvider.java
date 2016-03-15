package com.github.mrvilkaman.namegenerator.domainlayer.providers;

import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;

import java.util.List;

import rx.Observable;

/**
 * Created by root on 15.03.16.
 */
public interface FriendDataProvider {

	Observable<List<Friend>> getFriends();
}
