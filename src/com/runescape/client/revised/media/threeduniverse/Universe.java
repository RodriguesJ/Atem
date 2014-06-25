package com.runescape.client.revised.media.threeduniverse;

import java.util.ArrayList;
import java.util.List;

import com.runescape.client.revised.model.entity.npc.AbstractNPC;
import com.runescape.client.revised.model.entity.player.GamePlayer;

public class Universe {

	private static Universe universe;
	private List<GamePlayer> playerList;
	private List<AbstractNPC> npcList;

	public Universe() {
		this.setPlayerList(new ArrayList<GamePlayer>(2000));
		this.setNPCList(new ArrayList<AbstractNPC>(2000));
		// this.setLogger(Logger.getLogger(this.getClass().getName()));
	}

	public static void setUniverse(final Universe universe) {
		Universe.universe = universe;
	}

	public static Universe getUniverse() {
		if (Universe.universe == null) {
			Universe.setUniverse(new Universe());
		}
		return Universe.universe;
	}

	public void setPlayerList(final List<GamePlayer> playerList) {
		this.playerList = playerList;
	}

	public List<GamePlayer> getPlayerList() {
		return this.playerList;
	}

	public void setNPCList(final List<AbstractNPC> npcList) {
		this.npcList = npcList;
	}

	public List<AbstractNPC> getNPCList() {
		return this.npcList;
	}
}