package com.runescape.client.revised.packets.server;

public class DrawHeadIcon {

	/**
	 * if(pktType == 254)
		{
			headiconDrawType = inStream.readUnsignedByte();
			if(headiconDrawType == 1)
				anInt1222 = inStream.readUnsignedWord();
			if(headiconDrawType >= 2 && headiconDrawType <= 6)
			{
				if(headiconDrawType == 2)
				{
					anInt937 = 64;
					anInt938 = 64;
				}
				if(headiconDrawType == 3)
				{
					anInt937 = 0;
					anInt938 = 64;
				}
				if(headiconDrawType == 4)
				{
					anInt937 = 128;
					anInt938 = 64;
				}
				if(headiconDrawType == 5)
				{
					anInt937 = 64;
					anInt938 = 0;
				}
				if(headiconDrawType == 6)
				{
					anInt937 = 64;
					anInt938 = 128;
				}
				headiconDrawType = 2;
				anInt934 = inStream.readUnsignedWord();
				anInt935 = inStream.readUnsignedWord();
				anInt936 = inStream.readUnsignedByte();
			}
			if(headiconDrawType == 10)
				anInt933 = inStream.readUnsignedWord();
			pktType = -1;
			return true;
		}
	 */
}