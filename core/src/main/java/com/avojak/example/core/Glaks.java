package com.avojak.example.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;

/**
 * Implementation of {@link ApplicationListener} for Glaks. Wrapper for {@link GlaksGame} to handle dependency
 * injection.
 */
public class Glaks implements ApplicationListener {

	private Skin skin;
	private I18NBundle i18nBundle;
	private Game game;

	@Override
	public void create() {
		// TODO: Looking into using AssetManager instead (https://github.com/libgdx/libgdx/wiki/Managing-your-assets)
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		i18nBundle = I18NBundle.createBundle(Gdx.files.internal("i18n/resources"));
		game = new GlaksGame(skin, i18nBundle);
		game.create();
	}

	@Override
	public void resize(final int width, final int height) {
		game.resize(width, height);
	}

	@Override
	public void render() {
		game.render();
	}

	@Override
	public void pause() {
		game.pause();
	}

	@Override
	public void resume() {
		game.resume();
	}

	@Override
	public void dispose() {
		skin.dispose();
		game.dispose();
	}

}
