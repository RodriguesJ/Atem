package com.runescape.client.revised.packets.server;

public class UpdateSingleItem {

	/**
	 * 
		if(pktType == 34)
		{
			needDrawTabArea = true;
			int i9 = inStream.readUnsignedWord();
			RSInterface class9_2 = RSInterface.interfaceCache[i9];
			while(inStream.currentOffset < pktSize)
			{
				int j20 = inStream.method422();
				int i23 = inStream.readUnsignedWord();
				int l25 = inStream.readUnsignedByte();
				if(l25 == 255)
					l25 = inStream.readDWord();
				if(j20 >= 0 && j20 < class9_2.inv.length)
				{
					class9_2.inv[j20] = i23;
					class9_2.invStackSizes[j20] = l25;
				}
			}
			pktType = -1;
			return true;
		}
	 */
}
