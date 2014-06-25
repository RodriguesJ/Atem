package com.runescape.client.revised.packets.server;

public class ChangeInterfaceHiddenState {

	/**
	 * 
		if(pktType == 171)
		{
			boolean flag1 = inStream.readUnsignedByte() == 1;
			int j13 = inStream.readUnsignedWord();
			RSInterface.interfaceCache[j13].aBoolean266 = flag1;
			pktType = -1;
			return true;
		}
	 */
}