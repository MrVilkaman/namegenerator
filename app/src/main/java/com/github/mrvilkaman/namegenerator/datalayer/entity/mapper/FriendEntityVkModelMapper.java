package com.github.mrvilkaman.namegenerator.datalayer.entity.mapper;

import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vk.sdk.api.model.VKApiModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zahar on 17.03.16.
 */
public class FriendEntityVkModelMapper {

	private Gson gson;

	public FriendEntityVkModelMapper(Gson gson) {
		this.gson = gson;
	}

	public List<Friend> transform(JSONObject vkApiModel) {
		Type type = new TypeToken<ArrayList<Friend>>() {}.getType();
		try {
			String response = vkApiModel.getString("response");
			return gson.fromJson(response,type);
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
}
