package com.runescape.client.revised.model;

import com.runescape.client.revised.media.threeduniverse.Location;

public abstract class AbstractModel {

	public abstract void loadModel();
	public abstract short getID();
	public abstract String getName();
	public abstract Location[] getLocations();
}