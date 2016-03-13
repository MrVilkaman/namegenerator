package com.github.mrvilkaman.namegenerator.datalayer;

/**
 * Created by root on 12.03.16.
 */
public interface LocalStorage {

	String getToken();
	void saveToken(String token);
}
