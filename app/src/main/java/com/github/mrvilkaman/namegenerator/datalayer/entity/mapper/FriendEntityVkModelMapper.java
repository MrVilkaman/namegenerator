package com.github.mrvilkaman.namegenerator.datalayer.entity.mapper;

import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;
import com.vk.sdk.api.model.VKApiModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Zahar on 17.03.16.
 */
public class FriendEntityVkModelMapper {
	public Friend transform(VKApiModel vkApiModel) throws JSONException {
		JSONObject jsonObject = vkApiModel.fields;
		String s = jsonObject.getString("first_name");
		String s1 = jsonObject.getString("last_name");
		long id = jsonObject.getLong("id");

		return new Friend(id,s +" "+ s1);
	}
}
