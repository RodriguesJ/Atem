package com.runescape.client.revised.media.tabarea;

import javax.media.j3d.ImageComponent2D;
import javax.vecmath.Vector3d;

import com.runescape.client.revised.AbstractSprite;
import com.runescape.client.revised.interfaces.AbstractInterface;
import com.runescape.client.revised.media.threeduniverse.Location;
import com.runescape.client.revised.media.threeduniverse.LocationArea;

/**
 * Represents a tab within the tab area.
 * @author Josh
 *
 */
public abstract class AbstractTab extends AbstractSprite {

	/**
	 * Gets the tab's icon image.
	 * @return The tab icon.
	 */
	public abstract ImageComponent2D getTabIcon();

	/**
	 * Gets the tab's interface.
	 * @return The interface for this tab.
	 */
	public abstract AbstractInterface getInterface();

	/*
	 * (non-Javadoc)
	 * @see com.runescape.revised.Sprite#getLocation()
	 */
	@Override
	public Location getLocation() {
		return new Location(new Vector3d((short) 0, (short) 0, (byte) -1));
	}

	/*
	 * (non-Javadoc)
	 * @see com.runescape.revised.Sprite#getLocationArea()
	 */
	@Override
	public LocationArea getLocationArea() {
		return LocationArea.TAB_AREA;
	}
}