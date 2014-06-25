package com.runescape.client.revised.media.threeduniverse;

import java.net.MalformedURLException;
import java.net.URL;

import javax.media.j3d.ImageComponent2D;
import javax.vecmath.Vector3d;

import com.runescape.client.revised.AbstractSprite;
import com.sun.j3d.utils.image.TextureLoader;

public class GameArea extends AbstractSprite {

	@Override
	public Location getLocation() {
		return new Location(new Vector3d((short) 0, (short) 0, (byte) -1));
	}

	@Override
	public LocationArea getLocationArea() {
		return LocationArea.GAME_AREA;
	}

	@Override
	public ImageComponent2D getImage() {
		try {
			return new TextureLoader(new URL("./images/gamearea.png"), null).getImage();
		} catch (final MalformedURLException murle) {
			murle.printStackTrace();
		}
		return null;
	}
}