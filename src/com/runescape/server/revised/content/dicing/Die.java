package com.runescape.server.revised.content.dicing;

import java.util.Random;

import com.runescape.server.revised.actor.entity.item.AbstractItem;

public class Die extends AbstractItem {

	private byte sides;

	public Die(final short id, final byte sides) {
		super(id);
		this.setSides(sides);
	}

	public byte roll() {
		return (byte) new Random().nextInt(this.getSides() - 1);
	}

	public void setSides(final byte sides) {
		this.sides = sides;
	}

	public byte getSides() {
		return this.sides;
	}

	@Override
	public void cut() {
		return;
	}

	@Override
	public void deposit() {
		return;
	}

	@Override
	public void pick() {
		return;
	}

	@Override
	public void pickup() {
		return;
	}

	@Override
	public void use() {
		return;
	}

	@Override
	public void wield() {
		return;
	}
}