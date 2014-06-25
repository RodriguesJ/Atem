package com.runescape.server.revised.content.skill.mining;

import com.runescape.server.revised.actor.entity.character.action.impl.event.skill.SkillEvent;
import com.runescape.server.revised.actor.entity.character.action.impl.event.skill.impl.MiningActionEvent;
import com.runescape.server.revised.actor.entity.character.update.blocks.animation.AbstractAnimation;
import com.runescape.server.revised.actor.entity.item.AbstractItem;
import com.runescape.server.revised.content.skill.AbstractSkill;

public class Mining extends AbstractSkill implements Prospectable {

	private Ore ore;
	private Pickaxe pickaxe;
	private AbstractAnimation miningAnimation;

	public Mining(final Object... objectParameters) {
		this.setOre((Ore) objectParameters[0]);
		this.setPickaxe((Pickaxe) objectParameters[1]);
	}

	@Override
	public short getID() {
		return 0;
	}

	@Override
	public byte getLevel() {
		return 0;
	}

	@Override
	public int getExperience() {
		return 0;
	}

	@Override
	public SkillEvent getSkillEvent() {
		return new MiningActionEvent();
	}

	@Override
	public String getRewardMessage() {
		return null;
	}

	@Override
	public AbstractAnimation getAnimation() {

		/**
		 * Animation System will check and execute the id and delay when the constructor of
		 * the animation is created.
		 */
		return new MiningAnimation();
	}

	@Override
	public String getLowLevelMessage() {
		return null;
	}

	@Override
	public AbstractItem getItemReward() {
		return null;
	}

	@Override
	public void prospect() {

	}

	public void setOre(final Ore ore) {
		this.ore = ore;
	}

	public Ore getOre() {
		return this.ore;
	}

	public void setPickaxe(final Pickaxe pickaxe) {
		this.pickaxe = pickaxe;
	}

	public Pickaxe getPickaxe() {
		return this.pickaxe;
	}

	public void setMiningAnimation(final AbstractAnimation miningAnimation) {
		this.miningAnimation = miningAnimation;
	}

	public AbstractAnimation getMiningAnimation() {
		return this.miningAnimation;
	}
}