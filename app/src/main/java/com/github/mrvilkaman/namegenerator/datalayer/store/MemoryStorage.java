package com.github.mrvilkaman.namegenerator.datalayer.store;

import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Zahar on 18.03.16.
 */
public interface MemoryStorage {


	enum Types{
		FRIENDS,
		FRIENDS_LIST
	}

	<T> void save(Types type, T object);
	<T> T get(Types type);
}
