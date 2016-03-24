package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.info;

import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.hello.HelloScreenFragment;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.hello.HelloScreenModule;

import dagger.Component;

/**
 * Created by Zahar on 24.03.16.
 */
@Component(modules = InfoPresenterModule.class)
public interface InfoComponent {
	void inject(InfoScreenFragment fragment);
}
