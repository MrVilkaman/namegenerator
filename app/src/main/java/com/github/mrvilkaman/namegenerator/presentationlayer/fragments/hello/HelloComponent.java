package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.hello;

import dagger.Component;

/**
 * Created by Zahar on 24.03.16.
 */
@Component(modules = HelloScreenModule.class)
public interface HelloComponent {
	void inject(HelloScreenFragment fragment);
}
