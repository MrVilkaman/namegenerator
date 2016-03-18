package com.github.mrvilkaman.namegenerator.datalayer.store;

import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Zahar on 18.03.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MemoryStorageImplTest {

	private MemoryStorage storage;

	@Before
	public void init() {
		storage = new MemoryStorageImpl();
	}

	@Test
	public void testGetNull() {
		// Arrange
		// Act
		Friend friend = storage.get(LocalCacheItemType.TOKEN);

		List<Friend> friendList = storage.get(LocalCacheItemType.FRIENDS_LIST);

		// Assert
		Assert.assertThat(friendList, CoreMatchers.nullValue());
		Assert.assertThat(friend, CoreMatchers.nullValue());
	}

	@Test
	public void testSaveAndGet() {
		// Arrange

		Friend sad = new Friend(1, "sad");
		List<Friend> friendList2 = new ArrayList<>();
		friendList2.add(sad);

		// Act
		storage.save(LocalCacheItemType.TOKEN, sad);
		storage.save(LocalCacheItemType.FRIENDS_LIST, friendList2);

		Friend friend = storage.get(LocalCacheItemType.TOKEN);
		List<Friend> friendList = storage.get(LocalCacheItemType.FRIENDS_LIST);

		// Assert
		Assert.assertThat(friend, CoreMatchers.equalTo(sad));
		Assert.assertThat(friendList, CoreMatchers.equalTo(friendList2));
	}

	@Test
	public void testSaveReplaceAndGet() {
		// Arrange
		Friend sad = new Friend(1, "sad");
		List<Friend> friendList2 = new ArrayList<>();
		friendList2.add(sad);
		storage.save(LocalCacheItemType.TOKEN, sad);
		storage.save(LocalCacheItemType.FRIENDS_LIST, friendList2);

		sad = new Friend(99, "фывфывsad");
		friendList2 = new ArrayList<>();
		friendList2.add(sad);

		storage.save(LocalCacheItemType.TOKEN, sad);
		storage.save(LocalCacheItemType.FRIENDS_LIST, friendList2);

		// Act


		Friend friend = storage.get(LocalCacheItemType.TOKEN);
		List<Friend> friendList = storage.get(LocalCacheItemType.FRIENDS_LIST);

		// Assert
		Assert.assertThat(friend, CoreMatchers.equalTo(sad));
		Assert.assertThat(friendList, CoreMatchers.equalTo(friendList2));
	}


}