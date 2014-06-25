package com.runescape.client.revised.packets.server;

public class Ignore {

	/**
	 * 
		if(pktType == 214)
		{
			ignoreCount = pktSize / 8;
			for(int j1 = 0; j1 < ignoreCount; j1++)
				ignoreListAsLongs[j1] = inStream.readQWord();

			pktType = -1;
			return true;
		}
	 */
}