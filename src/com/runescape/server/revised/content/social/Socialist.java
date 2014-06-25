package com.runescape.server.revised.content.social;

public abstract class Socialist implements Sociable {

	private String name;
	private SocialStatus socialStatus;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSocialStatus(SocialStatus socialStatus) {
		this.socialStatus = socialStatus;
	}
	
	public SocialStatus getSocialStatus() {
		return socialStatus;
	}
}