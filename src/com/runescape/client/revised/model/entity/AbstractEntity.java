package com.runescape.client.revised.model.entity;

import java.util.Map;

import com.runescape.client.revised.media.threeduniverse.Location;
import com.runescape.client.revised.model.AbstractModel;

public abstract class AbstractEntity extends AbstractModel implements Walkable {

	private Location location;
	private byte age;
	private String name;
	private Map<Object, Object> characertisticMap;

	public void setLocation(final Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setAge(final byte age) {
		this.age = age;
	}

	public byte getAge() {
		return this.age;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void setCharacteristicMap(final Map<Object, Object> characertisticMap) {
		this.characertisticMap = characertisticMap;
	}

	public Map<Object, Object> getCharacteristicMap() {
		return this.characertisticMap;
	}
}