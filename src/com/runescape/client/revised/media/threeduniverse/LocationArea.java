package com.runescape.client.revised.media.threeduniverse;

public enum LocationArea {

	MAP, CHAT, TAB_AREA, THREE_D, GAME_AREA;

	private Location location;

	// LocationArea(Location location) {
	// 	this.setLocation(location);
	// }

	public void setLocation(final Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return this.location;
	}
}