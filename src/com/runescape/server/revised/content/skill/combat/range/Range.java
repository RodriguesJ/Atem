package com.runescape.server.revised.content.skill.combat.range;

import com.runescape.server.revised.actor.entity.character.action.impl.event.skill.SkillEvent;
import com.runescape.server.revised.actor.entity.character.update.blocks.animation.AbstractAnimation;
import com.runescape.server.revised.actor.entity.item.AbstractItem;
import com.runescape.server.revised.content.skill.AbstractSkill;

public class Range extends AbstractSkill {

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
		return null;
	}

	@Override
	public String getRewardMessage() {
		return null;
	}

	@Override
	public AbstractAnimation getAnimation() {
		return null;
	}

	@Override
	public String getLowLevelMessage() {
		return null;
	}

	@Override
	public AbstractItem getItemReward() {
		return null;
	}
}