package com.runescape.client.revised.packets.server;

public class PlayerOption {

	/**
	 * 
		if(pktType == 104)
		{
			int j4 = inStream.method427();
			int i12 = inStream.method426();
			String s6 = inStream.readString();
			if(j4 >= 1 && j4 <= 5)
			{
				if(s6.equalsIgnoreCase("null"))
					s6 = null;
				atPlayerActions[j4 - 1] = s6;
				atPlayerArray[j4 - 1] = i12 == 0;
			}
			pktType = -1;
			return true;
		}
	 */
}