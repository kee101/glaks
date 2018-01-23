package com.avojak.example.core.menu.main;

import com.avojak.example.core.menu.main.listener.MainMenuPresenterListener;
import com.avojak.example.core.menu.main.listener.MainMenuViewListener;

import java.util.ArrayList;
import java.util.List;

public class MainMenuPresenter implements MainMenuViewListener {

	private final MainMenuView view;

	private final List<MainMenuPresenterListener> listeners = new ArrayList<MainMenuPresenterListener>();

	public MainMenuPresenter(final MainMenuView view) {
		if (view == null) {
			throw new IllegalArgumentException("view cannot be null");
		}
		this.view = view;

		view.addListener(this);
	}

	public MainMenuView getView() {
		return view;
	}

	public void addListener(final MainMenuPresenterListener listener) {
		if (listener == null) {
			throw new IllegalArgumentException("listener cannot be null");
		}
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	@Override
	public void onExitButtonClicked() {
		for (final MainMenuPresenterListener listener : listeners) {
			listener.onExitButtonClicked();
		}
	}

	@Override
	public void onSettingsButtonClicked() {
		for (final MainMenuPresenterListener listener : listeners) {
			listener.onSettingsButtonClicked();
		}
	}

	public void dispose() {
		view.dispose();
	}

}
