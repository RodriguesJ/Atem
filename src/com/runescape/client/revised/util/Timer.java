package com.runescape.client.revised.util;

public class Timer {

	private long startTime;

	public Timer() {
		this.setStartTime(System.currentTimeMillis());
	}

	public void setStartTime(final long startTime) {
		this.startTime = startTime;
	}

	public long getStartTime() {
		return this.startTime;
	}

	@Override
	public String toString() {
		return (System.currentTimeMillis() - this.getStartTime()) + "ms";
	}
}