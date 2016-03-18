package com.github.mrvilkaman.namegenerator.datalayer.store;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Type;


/**
 * Created by Zahar on 18.03.16.
 */
public class MemoryStorageImpl implements MemoryStorage {

	private Map<Types,Object> friends = new HashMap<>();

	@Override
	public <T> void save(Types type, T object) {
		friends.put(type,object);
	}

	@Override
	public <T> T get(Types type) {
		return (T)friends.get(type);
	}

}
