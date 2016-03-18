package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.info;

import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.view.BaseView;

/**
 * Created by Zahar on 18.03.16.
 */

public interface InfoView extends BaseView {

	void bindContent(String text);

	long getFriendId();
}