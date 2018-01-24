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

	public void addGame(final String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("name cannot be null or empty");
		}
		view.addGame(name);
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

	@Override
	public void onGameButtonClicked(final String name) {

	}

	public void dispose() {
		view.dispose();
	}

}
