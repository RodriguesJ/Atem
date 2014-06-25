package com.runescape.server.revised.actor.entity.character.npc.impl;

import com.runescape.server.revised.actor.entity.character.npc.AbstractNPC;
import com.runescape.server.revised.actor.entity.character.npc.CombatableNPCType;
import com.runescape.server.revised.actor.location.Location;

public final class JadNPC extends AbstractNPC {

	public JadNPC(final Location location) {
		super("Jad", location);
	}

	@Override
	public CombatableNPCType getNPCType() {
		return CombatableNPCType.COMBATABLE;
	}
}