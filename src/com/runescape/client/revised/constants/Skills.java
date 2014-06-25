package com.runescape.client.revised.constants;

public final class Skills {

	public final int[] currentExp;
	public final int[] currentStats;
	public static final int skillsCount = 25;
	public static final String[] skillNames = {
		"attack", "defence", "strength", "hitpoints", "ranged", "prayer", "magic", "cooking", "woodcutting", "fletching", 
		"fishing", "firemaking", "crafting", "smithing", "mining", "herblore", "agility", "thieving", "slayer", "farming", 
		"runecraft", "-unused-", "-unused-", "-unused-", "-unused-"
	};
	public static final boolean[] skillEnabled = {
		true, true, true, true, true, true, true, true, true, true, 
		true, true, true, true, true, true, true, true, true, false, 
		true, false, false, false, false
	};
	
	public Skills() {
		currentExp = new int[Skills.skillsCount];
		currentStats = new int[Skills.skillsCount];
	}
}
