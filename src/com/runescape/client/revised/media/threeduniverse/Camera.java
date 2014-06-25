package com.runescape.client.revised.media.threeduniverse;

public class Camera {

	private Location location;
	private CameraDirection cameraDirection;
	private byte angle;
	private byte speed;

	public void setLocation(final Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setCameraDirection(final CameraDirection cameraDirection) {
		this.cameraDirection = cameraDirection;
	}

	public CameraDirection getCameraDirection() {
		return this.cameraDirection;
	}

	public void setAngle(final byte angle) {
		this.angle = angle;
	}

	public byte getAngle() {
		return this.angle;
	}

	public void setSpeed(final byte speed) {
		this.speed = speed;
	}

	public byte getSpeed() {
		return this.speed;
	}
}