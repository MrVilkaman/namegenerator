package com.github.mrvilkaman.namegenerator.datalayer.providers;

import com.github.mrvilkaman.namegenerator.datalayer.entity.mapper.FriendEntityVkModelMapper;
import com.github.mrvilkaman.namegenerator.datalayer.store.LocalCacheItemType;
import com.github.mrvilkaman.namegenerator.datalayer.store.MemoryStorage;
import com.github.mrvilkaman.namegenerator.datalayer.store.MemoryStorageImpl;
import com.github.mrvilkaman.namegenerator.datalayer.store.VkStore;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;
import com.vk.sdk.api.model.VKApiModel;
import com.vk.sdk.api.model.VKList;

import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.spy;


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
		provider = spy(new FriendDataProviderImpl(vkStore, mapper, mstore));
		VKList value = new VKList();
		value.add(mock(VKApiModel.class));
		when(vkStore.getFriends(Matchers.anyString())).thenReturn(Observable.just(value));
		when(mapper.transform(Matchers.any())).thenReturn(new Friend(1, "qwer"));
	}

	// Remote model
	@Test
	public void testGetFriendsRemote() throws JSONException {
		// Act
		List<Friend> list = provider.getFriendsRemote()
				.toBlocking()
				.first();

		// Assert
		Assertions.assertThat(list)
				.hasSize(1)
				.contains(new Friend(1, "qwer"));
		verify(mapper).transform(any());
		verify(vkStore).getFriends(Matchers.anyString());
		verify(mstore).save(LocalCacheItemType.FRIENDS_LIST, list);
		verify(mstore, never()).get(Matchers.any());
	}

	@Test
	public void testGetFriendsInvalidItem() throws JSONException {
		// Arrange
		when(mapper.transform(Matchers.any())).thenThrow(new JSONException("qwer"));

		// Act
		List<Friend> list = provider.getFriendsRemote()
				.toBlocking()
				.first();

		// Assert
		Assertions.assertThat(list)
				.hasSize(1)
				.contains(new Friend(-1, "parse error!"));
	}

	//Local

	@Test
	public void testGetFriendsLocalEmpty() throws JSONException {
		// Arrange

		// Act
		List<Friend> list = provider.getFriendsLocal()
				.toBlocking()
				.first();

		// Assert
		Assertions.assertThat(list)
				.isNull();
	}

	@Test
	public void testGetFriendsLocalHas() throws JSONException {
		// Arrange
		when(mstore.get(LocalCacheItemType.FRIENDS_LIST)).thenReturn(Arrays.asList(new Friend(1, "qwer")));

		// Act
		List<Friend> list = provider.getFriendsLocal()
				.toBlocking()
				.first();

		// Assert
		Assertions.assertThat(list)
				.isNotEmpty()
				.contains(new Friend(1, "qwer"));
	}

	@Test
	public void testGetSetLocalFriends() {
		// Arrange
		provider = new FriendDataProviderImpl(vkStore, mapper, new MemoryStorageImpl());

		// Act
		provider.setLastFriend(new Friend(1, "qwer"));
		Friend lastFriends = provider.getLastFriend()
				.toBlocking()
				.last();

		// Assert
		Assertions.assertThat(lastFriends)
				.isEqualTo(new Friend(1, "qwer"));
	}

	@Test
	public void testGetFriendsByIdRemote() {
		// Arrange
		doReturn(Observable.just(null)).when(provider).getFriendsLocal();
		doReturn(Observable.just(Arrays.asList(new Friend(1,"qwer"),new Friend(2,"qwer2")))).when(provider).getFriendsRemote();

		// Act
		Friend lastFriends = provider.getFriendsById(2)
				.toBlocking()
				.first();

		// Assert
		Assertions.assertThat(lastFriends)
				.isEqualTo(new Friend(2,"qwer2"));
	}

	@Test()
	public void testGetFriendsByIdIsAbsent() {
		// Arrange
		doReturn(Observable.just(null)).when(provider).getFriendsLocal();
		doReturn(Observable.just(Arrays.asList(new Friend(1,"qwer"),new Friend(2,"qwer2")))).when(provider).getFriendsRemote();

		// Act
		try {
			provider.getFriendsById(3)
					.toBlocking()
					.first();
			Assert.fail("fail ! but will not");
		} catch (NoSuchElementException e) {
			Assertions.assertThat(e).isNotNull();
		}
	}

	@Test
	public void testGetRemoteFriendsEmpty() throws Exception {
		// Arrange
		doReturn(Observable.just(null)).when(provider).getFriendsLocal();
		doReturn(Observable.just(Collections.EMPTY_LIST)).when(provider).getFriendsRemote();

		// Act
		try {
			provider.getFriendsById(3)
					.toBlocking()
					.first();
			Assert.fail("fail ! but will not");
		} catch (NoSuchElementException e) {
			Assertions.assertThat(e).isNotNull();
		}
	}

	@Test
	public void testGetFriendsLocalAndRemoteAbsent() throws Exception {
		// Arrange
		doReturn(Observable.just(Arrays.asList(new Friend(3,"qwer")))).when(provider).getFriendsLocal();
		doReturn(Observable.just(Arrays.asList(new Friend(1,"qwer"),new Friend(2,"qwer2")))).when(provider).getFriendsRemote();

		// Act
		try {
			provider.getFriendsById(5)
					.toBlocking()
					.first();
			Assert.fail("fail ! but will not");
		} catch (NoSuchElementException e) {
			Assertions.assertThat(e).isNotNull();
		}
	}

	@Test
	public void testGetFriendLocalFullButAbsentNeedIdAndUseRemote() throws Exception {
		// Arrange
		doReturn(Observable.just(Arrays.asList(new Friend(3,"qwer")))).when(provider).getFriendsLocal();
		doReturn(Observable.just(Arrays.asList(new Friend(1,"qwer"),new Friend(2,"qwer2")))).when(provider).getFriendsRemote();

		// Act
		Friend lastFriends = provider.getFriendsById(2)
				.toBlocking()
				.first();
		// Assert
		Assertions.assertThat(lastFriends)
				.isEqualTo(new Friend(2,"qwer2"));
	}

	@Test
	public void testGetFriendLocalFullB() throws Exception {
		// Arrange
		doReturn(Observable.just(Arrays.asList(new Friend(3,"qwer")))).when(provider).getFriendsLocal();
		Friend friend = spy(new Friend(1, "qwer"));
		Friend friend1 = spy(new Friend(2, "qwer2"));
		doReturn(Observable.just(Arrays.asList(friend, friend1))).when(provider).getFriendsRemote();

		// Act
		Friend lastFriends = provider.getFriendsById(3)
				.toBlocking()
				.first();
		// Assert
		Assertions.assertThat(lastFriends)
				.isEqualTo(new Friend(3,"qwer2"));
		verify(friend,never()).getId();
		verify(friend1,never()).getId();
	}
}
