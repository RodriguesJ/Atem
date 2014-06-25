package com.runescape.server.revised.content.skill.firemaking;

import com.runescape.server.revised.actor.entity.character.action.impl.event.skill.SkillEvent;
import com.runescape.server.revised.actor.entity.character.action.impl.event.skill.impl.FiremakingActionEvent;
import com.runescape.server.revised.actor.entity.character.update.blocks.animation.AbstractAnimation;
import com.runescape.server.revised.actor.entity.item.AbstractItem;
import com.runescape.server.revised.content.skill.AbstractSkill;

public class Firemaking extends AbstractSkill  {

	private Log log;

	public Firemaking(final Object... objectParameters) {
		this.setLog((Log) objectParameters[0]);
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
		return new FiremakingActionEvent();
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

	public void setLog(final Log log) {
		this.log = log;
	}

	public Log getLog() {
		return this.log;
	}
}