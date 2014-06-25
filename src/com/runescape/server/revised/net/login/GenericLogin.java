package com.runescape.server.revised.net.login;

import com.runescape.server.revised.actor.entity.character.player.login.game.GamePlayer;
import com.runescape.server.revised.actor.message.MessageStage;
import com.runescape.server.revised.actor.message.SynchronousType;
import com.runescape.server.revised.actor.message.impl.ChannelConnectedMessage;

public class GenericLogin<I, P extends GamePlayer> {

	private I id;
	private P gamePlayer;
	private String name;
	private String pass;
	private LoginOptions loginOptions;

	public GenericLogin(final I id, final P gamePlayer) {
		this.setID(id);
		this.setGamePlayer(gamePlayer);
		this.getGamePlayer().sendMessage(new ChannelConnectedMessage(MessageStage.DISPATCHED, SynchronousType.ASYNCHRONOUS), this.getGamePlayer());
	}

	public void setID(final I id) {
		this.id = id;
	}

	public I getID() {
		return this.id;
	}

	public void setGamePlayer(final P gamePlayer) {
		this.gamePlayer = gamePlayer;
	}

	public P getGamePlayer() {
		return this.gamePlayer;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setPass(final String pass) {
		this.pass = pass;
	}

	public String getPass() {
		return this.pass;
	}

	public void setLoginOptions(final LoginOptions loginOptions) {
		this.loginOptions = loginOptions;
	}

	public LoginOptions getLoginOptions() {
		return this.loginOptions;
	}
}