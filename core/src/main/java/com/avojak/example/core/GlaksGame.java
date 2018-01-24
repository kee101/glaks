package com.avojak.example.core;

import com.avojak.example.core.menu.main.MainMenuPresenter;
import com.avojak.example.core.menu.main.MainMenuView;
import com.avojak.example.core.menu.main.MainMenuViewImpl;
import com.avojak.example.core.menu.main.listener.MainMenuPresenterListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;

/**
 * The main {@link Game} class for Glaks.
 */
public class GlaksGame extends Game implements MainMenuPresenterListener {

	private final Skin skin;
	private final I18NBundle i18nBundle;

	private MainMenuPresenter mainMenuPresenter;

	/**
	 * Constructor.
	 *
	 * @param skin       The {@link Skin}. Cannot be {@code null}.
	 * @param i18nBundle The {@link I18NBundle}. Cannot be {@code null}.
	 */
	GlaksGame(final Skin skin, final I18NBundle i18nBundle) {
		if (skin == null) {
			throw new IllegalArgumentException("skin cannot be null");
		}
		if (i18nBundle == null) {
			throw new IllegalArgumentException("i18nBundle cannot be null");
		}
		this.skin = skin;
		this.i18nBundle = i18nBundle;
	}

	@Override
	public void create() {
		final MainMenuView mainMenuView = new MainMenuViewImpl(skin);
		mainMenuPresenter = new MainMenuPresenter(mainMenuView);
		mainMenuPresenter.addListener(this);

		mainMenuPresenter.addGame("Sandbox");
		mainMenuPresenter.addGame("Frogger");
		mainMenuPresenter.addGame("Tanks");

		this.setScreen(mainMenuPresenter.getView());
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		mainMenuPresenter.dispose();
	}

	@Override
	public void onExitButtonClicked() {
		Gdx.app.exit();
	}

	@Override
	public void onSettingsButtonClicked() {
		// TODO: Implement
	}
}
