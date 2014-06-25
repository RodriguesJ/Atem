package com.runescape.client.revised.packets.server;

public class CameraCutscene {

	/**
	 * 
		if(pktType == 166)
		{
			aBoolean1160 = true;
			anInt1098 = inStream.readUnsignedByte();
			anInt1099 = inStream.readUnsignedByte();
			anInt1100 = inStream.readUnsignedWord();
			anInt1101 = inStream.readUnsignedByte();
			anInt1102 = inStream.readUnsignedByte();
			if(anInt1102 >= 100)
			{
				xCameraPos = anInt1098 * 128 + 64;
				yCameraPos = anInt1099 * 128 + 64;
				zCameraPos = method42(plane, yCameraPos, xCameraPos) - anInt1100;
			}
			pktType = -1;
			return true;
		}
	 */
}