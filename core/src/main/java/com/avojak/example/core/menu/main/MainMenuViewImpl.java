package com.avojak.example.core.menu.main;

import com.avojak.example.core.menu.main.listener.MainMenuViewListener;
import com.avojak.example.core.ui.util.BackgroundColorFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * implementation of {@link MainMenuView}.
 */
public class MainMenuViewImpl implements MainMenuView {

	private final Stage stage;
	private final Skin skin;

	private final Table gameGrid;

	private final List<MainMenuViewListener> listeners = new ArrayList<MainMenuViewListener>();

	/**
	 * Constructor.
	 *
	 * @param skin The {@link Skin}. Cannot be {@code null}.
	 */
	public MainMenuViewImpl(final Skin skin) {
		if (skin == null) {
			throw new IllegalArgumentException("skin cannot be null");
		}
		this.skin = skin;

		this.stage = new Stage();

		final Table container = new Table(skin);
		container.setBackground(BackgroundColorFactory.get(125, 125, 125, 255));
		container.setFillParent(true);
		container.top();

		gameGrid = createGameGrid();

		final Table titleArea = createTitleArea();
		final Table gameSelectionArea = createGameSelectionArea(gameGrid);
		final Table controlArea = createControlArea();

		container.add(titleArea).top().fillX().expandX();
		container.row();
		container.add(gameSelectionArea).center().fill().expand();
		container.row();
		container.add(controlArea).bottom().fillX().expandX();
		container.pack();

		stage.addActor(container);
	}

	private Table createGameGrid() {
		final Table table = new Table(skin);
		table.setBackground(BackgroundColorFactory.get(125, 125, 0, 255));

		return table;
	}

	private Table createTitleArea() {
		final Table titleTable = new Table(skin);
		titleTable.padTop(10);
		titleTable.padBottom(10);

		final Label titleLabel = new Label("Welcome to Glaks, User!", skin);

		titleTable.add(titleLabel);
		titleTable.pack();

		return titleTable;
	}

	private Table createGameSelectionArea(final Table gameGrid) {
		final Table gameSelectionTable = new Table(skin);
		gameSelectionTable.setBackground(BackgroundColorFactory.get(0, 0, 0, 255));
		gameSelectionTable.pad(10, 10, 10, 10);

		final Label gameSelectionLabel = new Label("Select a game:", skin);
		gameSelectionLabel.setAlignment(1);

		gameSelectionTable.add(gameSelectionLabel).center().fillX().expandX().padBottom(10);
		gameSelectionTable.row();
		gameSelectionTable.add(gameGrid).center().fill().expand();
		gameSelectionTable.pack();

		return gameSelectionTable;
	}

	private Table createControlArea() {
		final Table controlTable = new Table(skin);
		controlTable.padTop(10);
		controlTable.padBottom(10);

		final TextButton settingsButton = new TextButton("Settings", skin);
		settingsButton.addListener(new ChangeListener() {
			@Override
			public void changed(final ChangeEvent event, final Actor actor) {
				Gdx.app.log(getClass().getName(), "Settings button clicked");
				notifyOnSettingsButtonClicked();
			}
		});

		final TextButton exitButton = new TextButton("Exit", skin);
		exitButton.addListener(new ChangeListener() {
			@Override
			public void changed(final ChangeEvent event, final Actor actor) {
				Gdx.app.log(getClass().getName(), "Exit button clicked");
				notifyOnExitButtonClicked();
			}
		});

		controlTable.add(settingsButton).pad(0, 10, 0, 10).fillX().expandX();
		controlTable.add(exitButton).pad(0, 10, 0, 10).fillX().expandX();
		controlTable.pack();

		return controlTable;
	}

	private void notifyOnSettingsButtonClicked() {
		for (final MainMenuViewListener listener : listeners) {
			listener.onSettingsButtonClicked();
		}
	}

	private void notifyOnExitButtonClicked() {
		for (final MainMenuViewListener listener : listeners) {
			listener.onExitButtonClicked();
		}
	}

	@Override
	public void render(final float delta) {
		stage.draw();
	}

	@Override
	public void resize(final int width, final int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		// I don't know if this is actually necessary
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void addListener(final MainMenuViewListener listener) {
		if (listener == null) {
			throw new IllegalArgumentException("listener cannot be null");
		}
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	@Override
	public void addGame(final String name) {
		final Table gameTable = new Table(skin);
		final Button gameButton = new TextButton(name, skin);
		gameButton.addListener(new ChangeListener() {
			@Override
			public void changed(final ChangeEvent event, final Actor actor) {
				Gdx.app.log(getClass().getName(), "Game button clicked [" + name + "]");
				notifyOnGameButtonClicked(name);
			}
		});

		gameTable.add(gameButton).fillX().expandX().pad(10, 10, 10, 10);
		gameTable.pack();

		// Start a new row for every two games
		if (gameGrid.getCells().size % 2 == 0) {
			gameGrid.row();
		}

		gameGrid.add(gameTable).center().fillX().expandX();
	}

	private void notifyOnGameButtonClicked(final String name) {
		for (final MainMenuViewListener listener : listeners) {
			listener.onGameButtonClicked(name);
		}
	}

}
