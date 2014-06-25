package com.runescape.client.revised.media.tabarea.inventory;

import com.runescape.client.revised.model.item.AbstractItem;

public class Slot {

	private AbstractItem inventoryItem;

	public Slot(final AbstractItem inventoryItem) {
		this.setItem(inventoryItem);
	}

	public void setItem(final AbstractItem inventoryItem) {
		this.inventoryItem = inventoryItem;
	}

	public AbstractItem getItem() {
		return this.inventoryItem;
	}
}