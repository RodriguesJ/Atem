package com.runescape.client.revised.media.tabarea;

import com.runescape.client.revised.model.entity.player.GamePlayer;

/**
 * The tab area system.
 * 
 * @author Josh
 *
 */
public class TabAreaSystem {

	private TabArea tabArea;
	private GamePlayer player;

	public TabAreaSystem(final GamePlayer player) {
		this.setPlayer(player);
		this.setTabArea(new TabArea(player));
	}

	public void setTabArea(final TabArea tabArea) {
		this.tabArea = tabArea;
	}

	public TabArea getTabArea() {
		return this.tabArea;
	}

	public void setPlayer(final GamePlayer player) {
		this.player = player;
	}

	public GamePlayer getPlayer() {
		return this.player;
	}
}