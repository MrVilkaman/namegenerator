package com.github.mrvilkaman.namegenerator.datalayer.providers;

import com.github.mrvilkaman.namegenerator.datalayer.entity.mapper.FriendEntityVkModelMapper;
import com.github.mrvilkaman.namegenerator.datalayer.store.LocalCacheItemType;
import com.github.mrvilkaman.namegenerator.datalayer.store.MemoryStorage;
import com.github.mrvilkaman.namegenerator.datalayer.store.VkStore;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;
import com.vk.sdk.api.model.VKApiModel;
import com.vk.sdk.api.model.VKList;

import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by Zahar on 18.03.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class FriendDataProviderImplTest {

	private FriendDataProvider provider;
	@Mock
	private VkStore vkStore;
	@Mock
	private FriendEntityVkModelMapper mapper;
	@Mock
	private MemoryStorage mstore;

	@Before
	public void init() throws JSONException {
		provider = new FriendDataProviderImpl(vkStore, mapper, mstore);
		VKList value = new VKList();
		value.add(mock(VKApiModel.class));
		when(vkStore.getFriends(Matchers.anyString())).thenReturn(Observable.just(value));
		when(mapper.transform(Matchers.any())).thenReturn(new Friend(1,"qwer"));
	}

	// Remote model
	@Test
	public void testGetFriendsRemote() throws JSONException{
		// Act
		List<Friend> list = provider.getFriendsRemote()
				.toBlocking()
				.first();

		// Assert
		Assertions.assertThat(list).hasSize(1).contains(new Friend(1,"qwer"));
		verify(mapper).transform(any());
		verify(vkStore).getFriends(Matchers.anyString());
		verify(mstore).save(LocalCacheItemType.FRIENDS_LIST, list);
		verify(mstore,never()).get(Matchers.any());
	}

	@Test
	public void testGetFriendsInvalidItem() throws JSONException{
		// Arrange
		when(mapper.transform(Matchers.any())).thenThrow(new JSONException("qwer"));

		// Act
		List<Friend> list = provider.getFriendsRemote()
				.toBlocking()
				.first();

		// Assert
		Assertions.assertThat(list).hasSize(1).contains(new Friend(-1,"parse error!"));
	}

	//Local

	@Test
	public void testGetFriendsLocalEmpty() throws JSONException{
		// Arrange

		// Act
		List<Friend> list = provider.getFriendsLocal()
				.toBlocking()
				.first();

		// Assert
		Assertions.assertThat(list).isNull();
	}

	@Test
	public void testGetFriendsLocalHas() throws JSONException{
		// Arrange
		when(mstore.get(LocalCacheItemType.FRIENDS_LIST)).thenReturn(Arrays.asList(new Friend(1,"qwer")));

		// Act
		List<Friend> list = provider.getFriendsLocal()
				.toBlocking()
				.first();

		// Assert
		Assertions.assertThat(list).isNotEmpty().contains(new Friend(1,"qwer"));
	}
}