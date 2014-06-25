package com.runescape.client.revised.media.threeduniverse;

import javax.vecmath.Vector3d;

public class Location {

	private short x, y;
	private byte z;
	private Vector3d location;

	public Location(final short x, final short y, final byte z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}

	public Location(final Vector3d location) {
		this.setLocation(location);
	}

	public void setX(final short x) {
		this.x = x;
	}

	public short getX() {
		return this.x;
	}

	public void setY(final short y) {
		this.y = y;
	}

	public short getY() {
		return this.y;
	}

	public void setZ(final byte z) {
		this.z = z;
	}

	public byte getZ() {
		return this.z;
	}

	public void setLocation(final Vector3d location) {
		this.location = location;
	}

	public Vector3d getLocation() {
		return this.location;
	}
}