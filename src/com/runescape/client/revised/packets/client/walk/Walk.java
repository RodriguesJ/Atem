package com.runescape.client.revised.packets.client.walk;

import com.runescape.client.revised.Priority;
import com.runescape.client.revised.packets.AbstractPacket;
import com.runescape.client.revised.packets.VariableType;

public class Walk extends AbstractPacket {

	@Override
	public short getOpcode() {
		return 164;
	}

	@Override
	public byte getSize() {
		return -1;
	}

	@Override
	public VariableType getVariableType() {
		return VariableType.BYTE;
	}

	@Override
	public Priority getPriority() {
		return null;
	}
}