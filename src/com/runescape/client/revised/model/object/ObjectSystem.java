package com.runescape.client.revised.model.object;

public class ObjectSystem {

	private static ObjectSystem objectSystem;

	public static void setObjectSystem(final ObjectSystem objectSystem) {
		ObjectSystem.objectSystem = objectSystem;
	}

	public static ObjectSystem getObjectSystem() {
		return ObjectSystem.objectSystem;
	}
}