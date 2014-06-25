package com.runescape.client.revised.media.threeduniverse;

public class FPS {

	private int fps;

	public FPS(final int fps) {
		this.setFPS(fps);
	}

	public void setFPS(final int fps) {
		this.fps = fps;
	}

	public int getFPS() {
		return this.fps;
	}
}