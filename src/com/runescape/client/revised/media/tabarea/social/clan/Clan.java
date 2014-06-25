package com.runescape.client.revised.media.tabarea.social.clan;

import com.runescape.client.revised.media.chat.ChatBox;
import com.runescape.client.revised.media.tabarea.social.Sociable;
import com.runescape.client.revised.media.tabarea.social.Socialist;

public class Clan extends Socialist implements Sociable {

	/*
	 * (non-Javadoc)
	 * @see com.runescape.client.revised.media.tabarea.social.Sociable#addConversation(com.runescape.client.revised.media.chat.ChatBox)
	 */
	@Override
	public boolean addConversation(final ChatBox chatBox) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.runescape.client.revised.media.tabarea.social.Sociable#removeConversation(com.runescape.client.revised.media.chat.ChatBox)
	 */
	@Override
	public boolean removeConversation(final ChatBox chatBox) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.runescape.client.revised.media.tabarea.social.Sociable#addSocialist(com.runescape.client.revised.media.tabarea.social.Socialist)
	 */
	@Override
	public boolean addSocialist(final Socialist socialist) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.runescape.client.revised.media.tabarea.social.Sociable#removeSocialist(com.runescape.client.revised.media.tabarea.social.Socialist)
	 */
	@Override
	public boolean removeSocialist(final Socialist socialist) {
		return false;
	}
}