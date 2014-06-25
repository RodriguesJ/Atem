package com.runescape.revised.client;

import com.runescape.client.revised.media.IndexedImage;

public class Texture {


	private void writeBackgroundTexture(int j) // method37
	{
		if(!lowMem)
		{
			if(Rasterizer.anIntArray1480[17] >= j) // water found water
			{
				IndexedImage background = Rasterizer.aBackgroundArray1474s[17];
				int k = background.anInt1452 * background.anInt1453 - 1;
				int j1 = background.anInt1452 * anInt945 * 2;
				byte abyte0[] = background.aByteArray1450;
				byte abyte3[] = aByteArray912;
				for(int i2 = 0; i2 <= k; i2++)
					abyte3[i2] = abyte0[i2 - j1 & k];

				background.aByteArray1450 = abyte3;
				aByteArray912 = abyte0;
				Rasterizer.method370(17);
				anticheat11++;
				if(anticheat11 > 1235)
				{
					anticheat11 = 0;
					stream.putPacketID(226);
					stream.putLEShort(0);
					int l2 = stream.currentOffset;
					stream.putShort(58722);
					stream.putLEShort(240);
					stream.putShort((int)(Math.random() * 65536D));
					stream.putLEShort((int)(Math.random() * 256D));
					if((int)(Math.random() * 2D) == 0)
						stream.putShort(51825);
					stream.putLEShort((int)(Math.random() * 256D));
					stream.putShort((int)(Math.random() * 65536D));
					stream.putShort(7130);
					stream.putShort((int)(Math.random() * 65536D));
					stream.putShort(61657);
					stream.writeBytes(stream.currentOffset - l2);
				}
			}
			if(Rasterizer.anIntArray1480[24] >= j) // flowing water
			{
				IndexedImage background_1 = Rasterizer.aBackgroundArray1474s[24];
				int l = background_1.anInt1452 * background_1.anInt1453 - 1;
				int k1 = background_1.anInt1452 * anInt945 * 2;
				byte abyte1[] = background_1.aByteArray1450;
				byte abyte4[] = aByteArray912;
				for(int j2 = 0; j2 <= l; j2++)
					abyte4[j2] = abyte1[j2 - k1 & l];

				background_1.aByteArray1450 = abyte4;
				aByteArray912 = abyte1;
				Rasterizer.method370(24);
			}
			if(Rasterizer.anIntArray1480[34] >= j) // magic tree stars
			{
				IndexedImage background_2 = Rasterizer.aBackgroundArray1474s[34];
				int i1 = background_2.anInt1452 * background_2.anInt1453 - 1;
				int l1 = background_2.anInt1452 * anInt945 * 2;
				byte abyte2[] = background_2.aByteArray1450;
				byte abyte5[] = aByteArray912;
				for(int k2 = 0; k2 <= i1; k2++)
					abyte5[k2] = abyte2[k2 - l1 & i1];

				background_2.aByteArray1450 = abyte5;
				aByteArray912 = abyte2;
				Rasterizer.method370(34);
			}
		}
	}
}