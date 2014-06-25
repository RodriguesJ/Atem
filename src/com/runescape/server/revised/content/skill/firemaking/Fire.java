package com.runescape.server.revised.content.skill.firemaking;

public enum Fire {

	TREE_FIRE, OAK_FIRE;
	
	private byte timeLength;
	private int id;
	
	public void setTimeLength(byte timeLength) {
		this.timeLength = timeLength;
	}
	
	public byte getTimeLength() {
		return timeLength;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
}