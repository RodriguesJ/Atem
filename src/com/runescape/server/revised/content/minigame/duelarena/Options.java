package com.runescape.server.revised.content.minigame.duelarena;

public class Options {

	private Boolean isRanging;
	private Boolean isMaging;
	private Boolean isMeleeing;

	public Options(final Boolean isRanging, final Boolean isMaging, final Boolean isMeleeing) {
		this.setRanging(isRanging);
		this.setMaging(isMaging);
		this.setMeleeing(isMeleeing);
	}

	public void setRanging(final Boolean isRanging) {
		this.isRanging = isRanging;
	}

	public Boolean isRanging() {
		return this.isRanging;
	}

	public void setMaging(final Boolean isMaging) {
		this.isMaging = isMaging;
	}

	public Boolean isMaging() {
		return this.isMaging;
	}

	public void setMeleeing(final Boolean isMeleeing) {
		this.isMeleeing = isMeleeing;
	}

	public Boolean isMeleeing() {
		return this.isMeleeing;
	}
}