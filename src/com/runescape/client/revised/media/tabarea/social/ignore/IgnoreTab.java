package com.runescape.client.revised.media.tabarea.social.ignore;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.media.j3d.ImageComponent2D;

import com.runescape.client.revised.interfaces.AbstractInterface;
import com.runescape.client.revised.media.tabarea.AbstractTab;
import com.runescape.client.revised.model.entity.player.GamePlayer;
import com.sun.j3d.utils.image.TextureLoader;

public class IgnoreTab extends AbstractTab {

	private List<Ignore> ignoreList; // ArrayList
	private GamePlayer player;

	public IgnoreTab(final GamePlayer player) {
		this.setPlayer(player);
	}

	@Override
	public ImageComponent2D getImage() {
		try {
			switch (this.getPlayer().getMouseActionType()) {
			case NONE:
				return new TextureLoader(new URL(""), null).getImage();
			case HOVER:
				return new TextureLoader(new URL(""), null).getImage();
			case LEFT_CLICK:
				return new TextureLoader(new URL("./images/redstone_one.png"), null).getImage();
			}
		} catch (final MalformedURLException murle) {
			murle.printStackTrace();
		}
		return null;
	}

	@Override
	public ImageComponent2D getTabIcon() {
		try {
			return new TextureLoader(new URL("./images/ignore_tab_icon.png"), null).getImage();
		} catch (final MalformedURLException murle) {
			murle.printStackTrace();
		}
		return null;
	}

	@Override
	public AbstractInterface getInterface() {
		return null;
	}

	public void setIgnoreList(final List<Ignore> ignoreList) {
		this.ignoreList = ignoreList;
	}

	public List<Ignore> getIgnoreList() {
		return this.ignoreList;
	}

	public void setPlayer(final GamePlayer player) {
		this.player = player;
	}

	public GamePlayer getPlayer() {
		return this.player;
	}
}