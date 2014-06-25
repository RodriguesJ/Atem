package com.runescape.client.revised.media.tabarea.armour;

import java.net.MalformedURLException;
import java.net.URL;

import javax.media.j3d.ImageComponent2D;

import com.runescape.client.revised.interfaces.AbstractInterface;
import com.runescape.client.revised.media.tabarea.AbstractTab;
import com.runescape.client.revised.model.entity.player.GamePlayer;
import com.sun.j3d.utils.image.TextureLoader;

public class ArmourTab extends AbstractTab {

	private GamePlayer player;

	public ArmourTab(final GamePlayer player) {
		this.setPlayer(player);
	}

	/*
	 * (non-Javadoc)
	 * @see com.runescape.client.revised.AbstractSprite#getImage()
	 */
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

	/*
	 * (non-Javadoc)
	 * @see com.runescape.revised.media.tabarea.Tab#getTabIcon()
	 */
	@Override
	public ImageComponent2D getTabIcon() {
		try {
			return new TextureLoader(new URL("./images/equipment_tab_icon.png"), null).getImage();
		} catch (final MalformedURLException murle) {
			murle.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.runescape.revised.media.tabarea.Tab#getInterface()
	 */
	@Override
	public AbstractInterface getInterface() {
		return null;
	}

	public void setPlayer(final GamePlayer player) {
		this.player = player;
	}

	public GamePlayer getPlayer() {
		return this.player;
	}
}