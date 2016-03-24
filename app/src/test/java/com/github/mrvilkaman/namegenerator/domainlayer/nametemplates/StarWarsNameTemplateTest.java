package com.github.mrvilkaman.namegenerator.domainlayer.nametemplates;

import com.github.mrvilkaman.namegenerator.presentationlayer.model.Friend;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.Parent;
import com.github.mrvilkaman.namegenerator.presentationlayer.model.UserSex;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Zahar on 24.03.16.
 */
@RunWith(Parameterized.class)
public class StarWarsNameTemplateTest {

	@Parameterized.Parameters(name = "{index} - {1}")
	public static Object[] data() {
	 	return new Object[][] {
				{new Friend("Захар","Золотарев","Константиновка", Arrays.asList(new Parent("Юрий", UserSex.MAN))),"Золза Юркон"}
		};
	 }

	@Parameterized.Parameter(0)
	public Friend friend;

	@Parameterized.Parameter(1)
	public String title;

	private NameTemplate template = new StarWarsNameTemplate();
	@Test
	public void testGenerate() throws Exception {
		// Act
		String res = template.generate(friend);

		// Assert
		Assertions.assertThat(res).isEqualTo(title);
	}
}