package com.runescape.client.revised.packets.client.command;

import com.runescape.client.revised.media.chat.RankType;

public class Command {

	private RankType rankType;
	private String command;

	public void setRankType(final RankType rankType) {
		this.rankType = rankType;
	}

	public RankType getRankType() {
		return this.rankType;
	}

	public void setCommand(final String command) {
		this.command = command;
	}

	public String getCommand() {
		return this.command;
	}
}