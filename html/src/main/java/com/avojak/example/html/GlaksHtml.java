package com.avojak.example.html;

import com.avojak.example.core.Glaks;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class GlaksHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new Glaks();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
