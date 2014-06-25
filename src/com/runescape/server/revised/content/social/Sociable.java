package com.runescape.server.revised.content.social;

public interface Sociable {

	public boolean addConversation();
	public boolean removeConversation();
	public boolean addSocialist(Socialist socialist);
	public boolean removeSocialist(Socialist socialist);
}