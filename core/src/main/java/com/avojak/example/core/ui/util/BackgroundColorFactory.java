package com.avojak.example.core.ui.util;

/**
 * Factory class to create instances of {@link BackgroundColor}.
 */
public class BackgroundColorFactory {

	private static final String WHITE_TEXTURE = "white_color_texture.png";

	/**
	 * Gets a {@link BackgroundColor} for the given RGBA {@code int} values.
	 *
	 * @param r The red value.
	 * @param g The green value.
	 * @param b The blue value.
	 * @param a The alpha value.
	 *
	 * @return A new non-{@code null} instance of {@link BackgroundColor}.
	 */
	public static BackgroundColor get(final int r, final int g, final int b, final int a) {
		final BackgroundColor backgroundColor = new BackgroundColor(WHITE_TEXTURE);
		backgroundColor.setColor(r, g, b, a);
		return backgroundColor;
	}

	/**
	 * Gets a {@link BackgroundColor} for the given RGBA {@code float} values.
	 *
	 * @param r The red value.
	 * @param g The green value.
	 * @param b The blue value.
	 * @param a The alpha value.
	 *
	 * @return A new non-{@code null} instance of {@link BackgroundColor}.
	 */
	public static BackgroundColor get(final float r, final float g, final float b, final float a) {
		final BackgroundColor backgroundColor = new BackgroundColor(WHITE_TEXTURE);
		backgroundColor.setColor(r, g, b, a);
		return backgroundColor;
	}

}
