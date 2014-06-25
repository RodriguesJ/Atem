package com.runescape.client.revised.packets.server;

public class DisplayInventoryOverlay {

	/**
	 * 
		if(pktType == 248)
		{
			int i5 = inStream.method435();
			int k12 = inStream.readUnsignedWord();
			if(backDialogID != -1)
			{
				backDialogID = -1;
				inputTaken = true;
			}
			if(inputDialogState != 0)
			{
				inputDialogState = 0;
				inputTaken = true;
			}
			openInterfaceID = i5;
			invOverlayInterfaceID = k12;
			needDrawTabArea = true;
			tabAreaAltered = true;
			aBoolean1149 = false;
			pktType = -1;
			return true;
		}
	 */
}