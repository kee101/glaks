package com.avojak.example.core.menu.main;

import com.avojak.example.core.menu.main.listener.MainMenuViewListener;
import com.badlogic.gdx.Screen;

/**
 * Provides methods display UI elements for the Main Menu.
 */
public interface MainMenuView extends Screen {

	/**
	 * Adds the given listener if it has not already been added.
	 *
	 * @param listener The {@link MainMenuViewListener}. Cannot be {@code null}.
	 */
	void addListener(final MainMenuViewListener listener);

	void addGame(final String name);

}
