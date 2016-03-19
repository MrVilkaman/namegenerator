package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.info;

import com.github.mrvilkaman.namegenerator.datalayer.providers.DefaultSubscriber;
import com.github.mrvilkaman.namegenerator.domainlayer.nametemplates.StarWarsNameTemplate;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.GenerateNameUseCase;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.presenter.BasePresenter;

/**
 * Created by Zahar on 18.03.16.
 */

public class InfoPresenter extends BasePresenter<InfoView> {

	private GenerateNameUseCase generateNameUseCase;

	public InfoPresenter(GenerateNameUseCase generateNameUseCase) {
		this.generateNameUseCase = generateNameUseCase;
		generateNameUseCase.setNameTemplate(new StarWarsNameTemplate());
	}

	public void generate() {
		generateNameUseCase.setFriendId(view().getFriendId())
				.execute(new GenerateName());
	}

	@Override
	protected void onViewDetached() {
		generateNameUseCase.unsubscribe();
	}

	private class GenerateName extends DefaultSubscriber<String> {

		@Override
		public void onNext(String text) {
			view().bindContent(text);
		}
	}
}
