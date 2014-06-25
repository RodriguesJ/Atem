package com.runescape.server.revised.content.minigame.duelarena;

import org.jboss.netty.channel.Channel;

import com.runescape.server.revised.actor.entity.character.player.login.game.GamePlayer;

public class Spectator extends GamePlayer {

	public Spectator(final String name, final Channel channel) {
		super(name, channel);
	}
}