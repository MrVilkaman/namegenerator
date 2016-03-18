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

import static com.github.mrvilkaman.namegenerator.datalayer.store.MemoryStorageImpl.Types;

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
		Friend friend = storage.get(Types.FRIENDS);

		List<Friend> friendList = storage.get(Types.FRIENDS_LIST);

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
		storage.save(Types.FRIENDS, sad);
		storage.save(Types.FRIENDS_LIST, friendList2);

		Friend friend = storage.get(Types.FRIENDS);
		List<Friend> friendList = storage.get(Types.FRIENDS_LIST);

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
		storage.save(Types.FRIENDS, sad);
		storage.save(Types.FRIENDS_LIST, friendList2);

		sad = new Friend(99, "фывфывsad");
		friendList2 = new ArrayList<>();
		friendList2.add(sad);

		storage.save(Types.FRIENDS, sad);
		storage.save(Types.FRIENDS_LIST, friendList2);

		// Act


		Friend friend = storage.get(Types.FRIENDS);
		List<Friend> friendList = storage.get(Types.FRIENDS_LIST);

		// Assert
		Assert.assertThat(friend, CoreMatchers.equalTo(sad));
		Assert.assertThat(friendList, CoreMatchers.equalTo(friendList2));
	}


}