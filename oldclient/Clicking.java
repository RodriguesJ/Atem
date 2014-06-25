package com.runescape.revised.client;

public class Clicking {

	private void processMainScreenClick()
	{
		if(anInt1021 != 0)
			return;
		if(super.clickMode3 == 1)
		{
			int i = super.saveClickX - 25 - 550;
			int j = super.saveClickY - 5 - 4;
			if(i >= 0 && j >= 0 && i < 146 && j < 151)
			{
				i -= 73;
				j -= 75;
				int k = minimapInt1 + minimapInt2 & 0x7ff;
				int i1 = Rasterizer.anIntArray1470[k];
				int j1 = Rasterizer.anIntArray1471[k];
				i1 = i1 * (minimapInt3 + 256) >> 8;
				j1 = j1 * (minimapInt3 + 256) >> 8;
				int k1 = j * i1 + i * j1 >> 11;
				int l1 = j * j1 - i * i1 >> 11;
				int i2 = myPlayer.x + k1 >> 7;
				int j2 = myPlayer.y - l1 >> 7;
				boolean flag1 = doWalkTo(1, 0, 0, 0, myPlayer.smallY[0], 0, 0, j2, myPlayer.smallX[0], true, i2);
				if(flag1)
				{
					stream.putLEShort(i);
					stream.putLEShort(j);
					stream.putShort(minimapInt1);
					stream.putLEShort(57);
					stream.putLEShort(minimapInt2);
					stream.putLEShort(minimapInt3);
					stream.putLEShort(89);
					stream.putShort(myPlayer.x);
					stream.putShort(myPlayer.y);
					stream.putLEShort(anInt1264);
					stream.putLEShort(63);
				}
			}
			anInt1117++;
			if(anInt1117 > 1151)
			{
				anInt1117 = 0;
				stream.putPacketID(246);
				stream.putLEShort(0);
				int l = stream.currentOffset;
				if((int)(Math.random() * 2D) == 0)
					stream.putLEShort(101);
				stream.putLEShort(197);
				stream.putShort((int)(Math.random() * 65536D));
				stream.putLEShort((int)(Math.random() * 256D));
				stream.putLEShort(67);
				stream.putShort(14214);
				if((int)(Math.random() * 2D) == 0)
					stream.putShort(29487);
				stream.putShort((int)(Math.random() * 65536D));
				if((int)(Math.random() * 2D) == 0)
					stream.putLEShort(220);
				stream.putLEShort(180);
				stream.writeBytes(stream.currentOffset - l);
			}
		}
	}
}