package com.runescape.server.revised.content.skill.combat.strength;

import com.runescape.server.revised.actor.entity.character.update.blocks.animation.AbstractAnimation;

public enum StrengthAnimationType {

	SOMETHING;

	private AbstractAnimation strengthAnimation;

	StrengthAnimationType() {

	}

	public void setStrengthAnimation(final AbstractAnimation strengthAnimation) {
		this.strengthAnimation = strengthAnimation;
	}

	public AbstractAnimation getStrengthAnimation() {
		return this.strengthAnimation;
	}
}