package com.runescape.client.revised.media.tabarea;

public enum TabType {

	COMBAT((short) 0), SKILLS((short) 0), QUEST((short) 0), INVENTORY((short) 0), ARMOUR((short) 0), PRAYER((short) 0), MAGIC((short) 0),
	OTHER((short) 0), FRIENDS((short) 0), IGNORE((short) 0), LOGOUT((short) 0), SETTINGS((short) 0), RUN((short) 0), MUSIC((short) 0);

	private short id;

	TabType(final short id) {
		this.setID(id);
	}

	public void setID(final short id) {
		this.id = id;
	}

	public short getID() {
		return this.id;
	}
}