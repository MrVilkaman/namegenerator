package com.github.mrvilkaman.namegenerator.domainlayer.interactor;

/**
 * Created by root on 13.03.16.
 */
public interface TokenInteractor {

	void saveToken(String accessToken);
	boolean checkToken();
}
