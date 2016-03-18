package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.info;

import com.github.mrvilkaman.namegenerator.datalayer.providers.DefaultSubscriber;
import com.github.mrvilkaman.namegenerator.domainlayer.usecase.UseCase;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.presenter.BasePresenter;

/**
 * Created by Zahar on 18.03.16.
 */

public class InfoPresenter extends BasePresenter<InfoView> {

	private UseCase<String> generateNameUseCase;

	public InfoPresenter(UseCase<String> generateNameUseCase) {
		this.generateNameUseCase = generateNameUseCase;
	}

	public void generate() {
		generateNameUseCase.execute(new GenerateName());
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
