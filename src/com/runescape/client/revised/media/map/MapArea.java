package com.runescape.client.revised.media.map;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.j3d.ImageComponent;
import javax.media.j3d.ImageComponent2D;
import javax.vecmath.Vector3d;

import com.runescape.client.revised.AbstractSprite;
import com.runescape.client.revised.media.threeduniverse.Location;
import com.runescape.client.revised.media.threeduniverse.LocationArea;

public class MapArea extends AbstractSprite {

	private Compass compass;

	public MapArea(final Compass compass) {
		this.setCompass(compass);
	}

	@Override
	public Location getLocation() {
		return new Location(new Vector3d((short) 519, (short) -1, (byte) -1));
	}

	@Override
	public LocationArea getLocationArea() {
		return LocationArea.MAP;
	}

	@Override
	public ImageComponent2D getImage() {
		try {
			return new ImageComponent2D(ImageComponent.FORMAT_RGB, ImageIO.read(new File("./images/maparea.png")));
		} catch (final IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}

	public void setCompass(final Compass compass) {
		this.compass = compass;
	}

	public Compass getCompass() {
		return this.compass;
	}
}