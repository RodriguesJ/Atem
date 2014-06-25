package com.runescape.client.revised.media.tabarea.social.friend;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.media.j3d.ImageComponent2D;

import com.runescape.client.revised.interfaces.AbstractInterface;
import com.runescape.client.revised.media.tabarea.AbstractTab;
import com.runescape.client.revised.model.entity.player.GamePlayer;
import com.sun.j3d.utils.image.TextureLoader;

public class FriendTab extends AbstractTab {

	private List<Friend> friendList;
	private GamePlayer player;

	public FriendTab(final GamePlayer player) {
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
			return new TextureLoader(new URL("./images/friends_tab_icon.png"), null).getImage();
		} catch (final MalformedURLException murle) {
			murle.printStackTrace();
		}
		return null;
	}

	@Override
	public AbstractInterface getInterface() {
		return null;
	}

	public void setFriendList(final List<Friend> friendList) {
		this.friendList = friendList;
	}

	public List<Friend> getFriendList() {
		return this.friendList;
	}

	public void setPlayer(final GamePlayer player) {
		this.player = player;
	}

	public GamePlayer getPlayer() {
		return this.player;
	}
}