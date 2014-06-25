package com.runescape.server.revised.content.skill.mining;

public enum Pickaxe {

	DWARVEN_ARMY_AXE,
	BRONZE,
	IRON,
	STEEL,
	MITHRIL,
	ADAMANT,
	RUNE,
	DRAGON,
	SACRED_CLAY,
	INFERNO_ADZE;

	private short id;

	public void setID(final short id) {
		this.id = id;
	}

	public short getID() {
		return this.id;
	}
}