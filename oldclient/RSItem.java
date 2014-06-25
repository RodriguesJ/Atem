package com.runescape.client.revised.model.item;

import com.runescape.client.revised.config.definitions.ItemDef;
import com.runescape.client.revised.model.RSModel;

final class RSItem extends Renderable {

	public final RSModel getRotatedModel() {
		final ItemDef itemDef = ItemDef.forID(this.ID);
		return itemDef.method201(this.anInt1559);
	}

	public RSItem() {}

	public int ID;
	public int x;
	public int y;
	public int anInt1559;
}