package com.runescape.client.revised.packets.server;

public class OneSeventySeven {
 // cutscene related

	/** if(pktType == 177)
	{
		aBoolean1160 = true;
		anInt995 = inStream.readUnsignedByte();
		anInt996 = inStream.readUnsignedByte();
		anInt997 = inStream.readUnsignedWord();
		anInt998 = inStream.readUnsignedByte();
		anInt999 = inStream.readUnsignedByte();
		if(anInt999 >= 100)
		{
			int k7 = anInt995 * 128 + 64;
			int k14 = anInt996 * 128 + 64;
			int i20 = method42(plane, k14, k7) - anInt997;
			int l22 = k7 - xCameraPos;
			int k25 = i20 - zCameraPos;
			int j28 = k14 - yCameraPos;
			int i30 = (int)Math.sqrt(l22 * l22 + j28 * j28);
			yCameraCurve = (int)(Math.atan2(k25, i30) * 325.94900000000001D) & 0x7ff;
			xCameraCurve = (int)(Math.atan2(l22, j28) * -325.94900000000001D) & 0x7ff;
			if(yCameraCurve < 128)
				yCameraCurve = 128;
			if(yCameraCurve > 383)
				yCameraCurve = 383;
		}
		pktType = -1;
		return true;
	} */
}