package com.github.mrvilkaman.namegenerator.domainlayer.usecase;

import com.github.mrvilkaman.namegenerator.TestSchedulers;
import com.github.mrvilkaman.namegenerator.domainlayer.nametemplates.NameTemplate;
import com.github.mrvilkaman.namegenerator.domainlayer.providers.FriendDataProvider;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action;
import rx.functions.Action0;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.spy;

/**
 * Created by Zahar on 19.03.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class GenerateNameUseCaseTest {

	private GenerateNameUseCase useCase;
	@Mock
	private FriendDataProvider dataProvider;
	@Mock
	private NameTemplate nameTemplate;

	@Before
	public void setUp() throws Exception {
		useCase = new GenerateNameUseCase(dataProvider,new TestSchedulers());
		when(dataProvider.getFriendsLocal()).thenReturn(Observable.just(null));
	}

	@Test
	public void testGetRemote() throws Exception {
		// Arrange
		when(dataProvider.getFriendsRemote()).thenReturn(Observable.just(Arrays.asList(new Friend(1,"qwer"),new Friend(2,"qwer2"))));
		when(nameTemplate.generate(any(Friend.class))).thenReturn("!!!");
		// Act
		TestSubscriber<String> test = new TestSubscriber<>();
		useCase.setFriendId(2).setNameTemplate(nameTemplate).execute(test);

		// Assert
		commonAssert(test);
		Assertions.assertThat(test.getOnNextEvents()).hasSize(1).contains("!!!");
		verify(nameTemplate).generate(new Friend(2,"qwer2"));
		verify(dataProvider).getFriendsRemote();
	}

	@Test
	public void testGetRemoteFriendsIdAbsent() throws Exception {
		// Arrange
		when(dataProvider.getFriendsRemote()).thenReturn(Observable.just(Arrays.asList(new Friend(1,"qwer"),new Friend(2,"qwer2"))));
		when(nameTemplate.generate(any(Friend.class))).thenReturn("!!!");
		// Act
		TestSubscriber<String> test = new TestSubscriber<>();
		useCase.setFriendId(3).setNameTemplate(nameTemplate).execute(test);

		// Assert
		commonAssert(test);
		Assertions.assertThat(test.getOnNextEvents()).hasSize(1).contains("");
		verify(nameTemplate,never()).generate(any());
		verify(dataProvider).getFriendsRemote();
	}

	@Test
	public void testGetRemoteFriendsEmpty() throws Exception {
		// Arrange
		when(dataProvider.getFriendsRemote()).thenReturn(Observable.just(Collections.EMPTY_LIST));
		// Act
		TestSubscriber<String> test = new TestSubscriber<>();
		useCase.setFriendId(3).setNameTemplate(nameTemplate).execute(test);

		// Assert
		commonAssert(test);
		Assertions.assertThat(test.getOnNextEvents()).hasSize(1).contains("");
		verify(nameTemplate,never()).generate(any());
		verify(dataProvider).getFriendsRemote();

	}

	@Test
	public void testGetLocalFriendsFull() throws Exception {
		// Arrange
		when(dataProvider.getFriendsLocal()).thenReturn(Observable.just(Arrays.asList(new Friend(1,"qwer"),new Friend(2,"qwer2"))));
		when(nameTemplate.generate(any(Friend.class))).thenReturn("!!!");
		Friend friend = spy(new Friend(3, "qwer3"));
		when(dataProvider.getFriendsRemote()).thenReturn(Observable.just(Arrays.asList(friend)));

		// Act
		TestSubscriber<String> test = new TestSubscriber<>();
		useCase.setFriendId(2).setNameTemplate(nameTemplate).execute(test);

		// Assert
		commonAssert(test);
		test.assertNoErrors();
		Assertions.assertThat(test.getOnNextEvents()).hasSize(1).contains("!!!");
		verify(nameTemplate).generate(new Friend(2,"qwer2"));
		verify(friend,never()).getId();
	}

	@Test
	@Ignore
	public void testGetLocalFriendsFullButAbsentNeedIdAndUseRemote() throws Exception {
		// Arrange
		when(dataProvider.getFriendsLocal()).thenReturn(Observable.just(Arrays.asList(new Friend(1,"qwer"),new Friend(2,"qwer2"))));
		when(nameTemplate.generate(any(Friend.class))).thenReturn("!!!");
		Friend friend = spy(new Friend(3, "qwer3"));
		when(dataProvider.getFriendsRemote()).thenReturn(Observable.just(Arrays.asList(friend)));

		// Act
		TestSubscriber<String> test = new TestSubscriber<>();
		useCase.setFriendId(3).setNameTemplate(nameTemplate).execute(test);

		// Assert
		commonAssert(test);
		test.assertNoErrors();
		Assertions.assertThat(test.getOnNextEvents()).hasSize(1).contains("!!!");
		verify(nameTemplate).generate(new Friend(3,"qwer3"));
		verify(friend,never()).getId();
	}

	private void commonAssert(TestSubscriber<String> test) {
		test.awaitTerminalEvent();
		test.onCompleted();
		verify(dataProvider).getFriendsLocal();
	}
}