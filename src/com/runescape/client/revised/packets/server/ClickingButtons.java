package com.runescape.client.revised.packets.server;

public class ClickingButtons {

	/**
	 * 
		if(pktType == 185)
		{
			int k = inStream.method436();
			RSInterface.interfaceCache[k].anInt233 = 3;
			if(myPlayer.desc == null)
				RSInterface.interfaceCache[k].mediaID = (myPlayer.anIntArray1700[0] << 25) + (myPlayer.anIntArray1700[4] << 20) + (myPlayer.equipment[0] << 15) + (myPlayer.equipment[8] << 10) + (myPlayer.equipment[11] << 5) + myPlayer.equipment[1];
			else
				RSInterface.interfaceCache[k].mediaID = (int)(0x12345678L + myPlayer.desc.type);
			pktType = -1;
			return true;
		}
		
	 */
}