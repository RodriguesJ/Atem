package com.runescape.server.revised.content.skill.construction;

public enum Room {

	GARDEN, PARLOUR, KITCHEN, DINING_ROOM, ; 
	
	private byte level;
	private int cost;
	private String uses;
	
	public void setLevel(byte level) {
		this.level = level;
	}
	
	public byte getLevel() {
		return this.level;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public void setUses(String uses) {
		this.uses = uses;
	}
	
	public String getUses() {
		return this.uses;
	}
}