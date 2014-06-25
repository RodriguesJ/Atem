package com.runescape.server.revised.content.social.impl;

import com.runescape.server.revised.content.social.Sociable;
import com.runescape.server.revised.content.social.Socialist;

public class Friend implements Sociable {

	@Override
	public boolean addConversation() {
		return false;
	}

	@Override
	public boolean removeConversation() {
		return false;
	}

	@Override
	public boolean addSocialist(final Socialist socialist) {
		return false;
	}

	@Override
	public boolean removeSocialist(final Socialist socialist) {
		return false;
	}
}