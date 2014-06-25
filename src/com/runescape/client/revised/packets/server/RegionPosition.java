package com.runescape.client.revised.packets.server;

import com.runescape.client.revised.Priority;
import com.runescape.client.revised.packets.AbstractPacket;
import com.runescape.client.revised.packets.VariableType;

public class RegionPosition extends AbstractPacket {

	@Override
	public short getOpcode() {
		return 85;
	}

	@Override
	public byte getSize() {
		return 0;
	}

	@Override
	public VariableType getVariableType() {
		return null;
	}

	@Override
	public Priority getPriority() {
		return null;
	}

	/**
	 * 
		if(pktType == 85)
		{
			anInt1269 = inStream.method427();
			anInt1268 = inStream.method427();
			pktType = -1;
			return true;
		}
	 */
}
