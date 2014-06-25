package com.runescape.client.revised.mission;

public class MissionSystem {

	private static MissionSystem missionSystem;

	public static void setMissionSystem(final MissionSystem missionSystem) {
		MissionSystem.missionSystem = missionSystem;
	}

	public static MissionSystem getMissionSystem() {
		return MissionSystem.missionSystem;
	}
}