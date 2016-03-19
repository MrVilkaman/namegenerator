package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.friendslist;

import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.view.BaseView;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;

import java.util.List;

/**
 * Created by root on 13.03.16.
 */

public interface FriendsListView extends BaseView {

	void renderFriendsList(List<Friend> friends);

	void goToInfoScreen(long id);
}