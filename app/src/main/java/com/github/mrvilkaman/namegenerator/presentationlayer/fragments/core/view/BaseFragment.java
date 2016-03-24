package com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mrvilkaman.namegenerator.R;
import com.github.mrvilkaman.namegenerator.presentationlayer.activities.BaseActivityPresenter;
import com.github.mrvilkaman.namegenerator.presentationlayer.activities.BaseActivityView;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.core.presenter.BasePresenter;
import com.github.mrvilkaman.namegenerator.presentationlayer.fragments.info.InfoPresenter;
import com.github.mrvilkaman.namegenerator.presentationlayer.utils.IToolbar;
import com.github.mrvilkaman.namegenerator.presentationlayer.utils.OnBackPressedListener;
import com.github.mrvilkaman.namegenerator.presentationlayer.utils.UIUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnTouch;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView, BaseActivityView, OnBackPressedListener {

	private static final String PREVIOUS_FRAGMENT = "previousFragment";

	private String previousFragment;

	@Inject
	P relationPresenter;

	@Nullable
	@Bind(R.id.progress_wheel)
	View progressBar;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(getLayoutId(), container, false);
		if (isWorkCall()) {
			IToolbar toolbar = getToolbar();
			if (toolbar != null) {
				toolbar.clear();
			}
			ButterKnife.bind(this, view);
			getPresenter().setView(this);
			onCreateView(view, savedInstanceState);
		}
		if (savedInstanceState != null) {
			previousFragment = savedInstanceState.getString(PREVIOUS_FRAGMENT, previousFragment);
		}
		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (previousFragment != null) {
			outState.putString(PREVIOUS_FRAGMENT, previousFragment);
		}
	}

	@Nullable
	@OnTouch(R.id.parent)
	boolean onTouchParent() {
		hideKeyboard();
		return false;
	}

	@Override
	public void onDestroyView() {
		ButterKnife.unbind(this);
		getPresenter().setView(null);
		super.onDestroyView();
	}

	@Override
	public boolean onBackPressed() {
		return false;
	}

	@Override
	public void back() {
		getActivityView().back();
	}

	@Override
	public void hideKeyboard() {
		getActivityView().hideKeyboard();
	}

	@Override
	public void showProgress() {
		if (progressBar == null) {
			getActivityView().showProgress();
		} else {
			progressBar.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void hideProgress() {
		if (progressBar == null) {
			getActivityView().hideProgress();
		} else {
			progressBar.setVisibility(View.GONE);
		}
	}

	@Override
	public void clearProgress() {
		getActivityView().clearProgress();
	}

	private boolean isWorkCall() {
		return true;
	}

	public String getPreviousFragment() {
		return previousFragment;
	}

	public void setPreviousFragment(String previousFragment) {
		this.previousFragment = previousFragment;
	}

	protected abstract void onCreateView(View view, Bundle savedInstanceState);

	protected abstract int getLayoutId();

	protected void showFragment(BaseFragment fragment) {
		getBaseActivity().loadRootFragment(fragment, true, false, false, false);
	}

	protected void showRootFragment(BaseFragment fragment) {
		getBaseActivity().loadRootFragment(fragment, false, true, false, false);
	}

	protected void showFragmentWithoutBackStack(BaseFragment fragment) {
		getBaseActivity().loadRootFragment(fragment, false, false, false, false);
	}

	protected void showOrOpenFragment(BaseFragment fragment) {
		getBaseActivity().loadRootFragment(fragment, true, false, false, true);
	}

	public BaseActivityPresenter getBaseActivity() {
		return (BaseActivityPresenter) getActivity();
	}

	public BaseActivityView getActivityView() {
		return (BaseActivityView) getActivity();
	}

	@Override
	public P getPresenter() {
		return relationPresenter;
	}

	@Override
	public IToolbar getToolbar() {
		return getActivityView().getToolbar();
	}

	@Override
	public void showError(Throwable throwable) {
		hideProgress();
		Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void showMessage(int testId) {
		UIUtils.showAlert(getContext(), testId);
	}

	@Override
	public void showMessage(String text) {
		UIUtils.showAlert(getContext(), text);
	}

	@Override
	public void showToast(int resId) {
		UIUtils.showToast(getContext(), resId);
	}

	@Override
	public void showToast(String message) {
		UIUtils.showToast(getContext(), message);
	}

	public String getName() {
		return getClass().getSimpleName();
	}
}
