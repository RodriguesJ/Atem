package com.runescape.client.revised.media.tabarea.inventory;

import java.net.MalformedURLException;
import java.net.URL;

import javax.media.j3d.ImageComponent2D;

import com.runescape.client.revised.interfaces.AbstractInterface;
import com.runescape.client.revised.media.tabarea.AbstractTab;
import com.runescape.client.revised.model.entity.player.GamePlayer;
import com.runescape.client.revised.model.item.AbstractItem;
import com.sun.j3d.utils.image.TextureLoader;

public class InventoryTab extends AbstractTab {

	private Slot[] slots;
	private GamePlayer player;

	public InventoryTab(final GamePlayer player) {
		this.setSlots(new Slot[27]);
		this.setPlayer(player);
	}

	public void switchItems(final AbstractItem firstItem, final AbstractItem secondItem) {

	}

	public void addItem(final AbstractItem item) {

	}

	public void removeItem(final AbstractItem item) {

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
			return new TextureLoader(new URL("./images/inventory_tab_icon.png"), null).getImage();
		} catch (final MalformedURLException murle) {
			murle.printStackTrace();
		}
		return null;
	}

	@Override
	public AbstractInterface getInterface() {
		return null;
	}

	public void setSlots(final Slot[] slots) {
		this.slots = slots;
	}

	public Slot[] getSlots() {
		return this.slots;
	}

	public void setPlayer(final GamePlayer player) {
		this.player = player;
	}

	public GamePlayer getPlayer() {
		return this.player;
	}
}