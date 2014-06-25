package com.runescape.server.revised.content.skill.woodcutting;

public enum Tree {

	TREE, EVERGREEN, ACHEY, OAK, WILLOW, TEAK, MAPLE, HOLLOW, MAHOGANY, ARCTIC, EUCALYPTUS,
	IVY, YEW, MAGIC, CURSED_MAGIC, BLOODWOOD, ELDER, CRYSTAL;

	private byte level;
	private byte experience;
	private Enum<?> memberType;

	public void setLevel(final byte level) {
		this.level = level;
	}

	public byte getLevel() {
		return this.level;
	}

	public void setExperience(final byte experience) {
		this.experience = experience;
	}

	public byte getExperience() {
		return this.experience;
	}

	public void setMemberType(final Enum<?> memberType) {
		this.memberType = memberType;
	}

	public Enum<?> getMemberType() {
		return this.memberType;
	}
}