package com.runescape.client.revised.packets.server;

public class CloseInterfaces {

	/**
	 * 
		if(pktType == 219)
		{
			if(invOverlayInterfaceID != -1)
			{
				invOverlayInterfaceID = -1;
				needDrawTabArea = true;
				tabAreaAltered = true;
			}
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
			openInterfaceID = -1;
			aBoolean1149 = false;
			pktType = -1;
			return true;
		}
	 */
}
