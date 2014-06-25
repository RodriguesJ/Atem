package com.runescape.revised.client;

import com.runescape.client.SignLink;
import com.runescape.client.revised.config.definitions.ObjectDef;

public class RSObject {

	private void updateWorldObjects()
	{
		try
		{
			anInt985 = -1;
			aClass19_1056.removeAll();
			aClass19_1013.removeAll();
			Rasterizer.method366();
			unlinkMRUNodes();
			worldController.initToNull();
			System.gc();
			for(int i = 0; i < 4; i++)
				aClass11Array1230[i].method210();

			for(int l = 0; l < 4; l++)
			{
				for(int k1 = 0; k1 < 104; k1++)
				{
					for(int j2 = 0; j2 < 104; j2++)
						byteGroundArray[l][k1][j2] = 0;

				}
			}
			Region objectManager = new Region(byteGroundArray, intGroundArray);
			int k2 = aByteArrayArray1183.length;
			stream.putPacketID(0);
			if(!aBoolean1159)
			{
				for(int i3 = 0; i3 < k2; i3++)
				{
					int i4 = (anIntArray1234[i3] >> 8) * 64 - baseX;
					int k5 = (anIntArray1234[i3] & 0xff) * 64 - baseY;
					byte abyte0[] = aByteArrayArray1183[i3];
					if(abyte0 != null)
						objectManager.method180(abyte0, k5, i4, (anInt1069 - 6) * 8, (anInt1070 - 6) * 8, aClass11Array1230);
				}

				for(int j4 = 0; j4 < k2; j4++)
				{
					int l5 = (anIntArray1234[j4] >> 8) * 64 - baseX;
					int k7 = (anIntArray1234[j4] & 0xff) * 64 - baseY;
					byte abyte2[] = aByteArrayArray1183[j4];
					if(abyte2 == null && anInt1070 < 800)
						objectManager.method174(k7, 64, 64, l5);
				}

				anInt1097++;
				if(anInt1097 > 160)
				{
					anInt1097 = 0;
					stream.putPacketID(238);
					stream.putLEShort(96);
				}
				stream.putPacketID(0);
				for(int i6 = 0; i6 < k2; i6++)
				{
					byte abyte1[] = aByteArrayArray1247[i6];
					if(abyte1 != null)
					{
						int l8 = (anIntArray1234[i6] >> 8) * 64 - baseX;
						int k9 = (anIntArray1234[i6] & 0xff) * 64 - baseY;
						objectManager.method190(l8, aClass11Array1230, k9, worldController, abyte1);
					}
				}

			}
			if(aBoolean1159)
			{
				for(int j3 = 0; j3 < 4; j3++)
				{
					for(int k4 = 0; k4 < 13; k4++)
					{
						for(int j6 = 0; j6 < 13; j6++)
						{
							int l7 = anIntArrayArrayArray1129[j3][k4][j6];
							if(l7 != -1)
							{
								int i9 = l7 >> 24 & 3;
								int l9 = l7 >> 1 & 3;
								int j10 = l7 >> 14 & 0x3ff;
								int l10 = l7 >> 3 & 0x7ff;
								int j11 = (j10 / 8 << 8) + l10 / 8;
								for(int l11 = 0; l11 < anIntArray1234.length; l11++)
								{
									if(anIntArray1234[l11] != j11 || aByteArrayArray1183[l11] == null)
										continue;
									objectManager.method179(i9, l9, aClass11Array1230, k4 * 8, (j10 & 7) * 8, aByteArrayArray1183[l11], (l10 & 7) * 8, j3, j6 * 8);
									break;
								}

							}
						}

					}

				}

				for(int l4 = 0; l4 < 13; l4++)
				{
					for(int k6 = 0; k6 < 13; k6++)
					{
						int i8 = anIntArrayArrayArray1129[0][l4][k6];
						if(i8 == -1)
							objectManager.method174(k6 * 8, 8, 8, l4 * 8);
					}

				}

				stream.putPacketID(0);
				for(int l6 = 0; l6 < 4; l6++)
				{
					for(int j8 = 0; j8 < 13; j8++)
					{
						for(int j9 = 0; j9 < 13; j9++)
						{
							int i10 = anIntArrayArrayArray1129[l6][j8][j9];
							if(i10 != -1)
							{
								int k10 = i10 >> 24 & 3;
								int i11 = i10 >> 1 & 3;
								int k11 = i10 >> 14 & 0x3ff;
								int i12 = i10 >> 3 & 0x7ff;
								int j12 = (k11 / 8 << 8) + i12 / 8;
								for(int k12 = 0; k12 < anIntArray1234.length; k12++)
								{
									if(anIntArray1234[k12] != j12 || aByteArrayArray1247[k12] == null)
										continue;
									objectManager.method183(aClass11Array1230, worldController, k10, j8 * 8, (i12 & 7) * 8, l6, aByteArrayArray1247[k12], (k11 & 7) * 8, i11, j9 * 8);
									break;
								}

							}
						}

					}

				}

			}
			stream.putPacketID(0);
			objectManager.method171(aClass11Array1230, worldController);
			aRSImageProducer_1165.initDrawingArea();
			stream.putPacketID(0);
			int k3 = Region.anInt145;
			if(k3 > plane)
				k3 = plane;
			if(k3 < plane - 1)
				k3 = plane - 1;
			if(lowMem)
				worldController.method275(Region.anInt145);
			else
				worldController.method275(0);
			for(int i5 = 0; i5 < 104; i5++)
			{
				for(int i7 = 0; i7 < 104; i7++)
					spawnGroundItem(i5, i7);

			}

			anInt1051++;
			if(anInt1051 > 98)
			{
				anInt1051 = 0;
				stream.putPacketID(150);
			}
			method63();
		}
		catch(Exception exception) { }
		ObjectDef.modelCache.unlinkAll();
		if(super.gameFrame != null)
		{
			stream.putPacketID(210);
			stream.writeDWord(0x3f008edd);
		}
		if(lowMem && SignLink.cache_dat != null)
		{
			int j = onDemandFetcher.getVersionCount(0);
			for(int i1 = 0; i1 < j; i1++)
			{
				int l1 = onDemandFetcher.getModelIndex(i1);
				if((l1 & 0x79) == 0)
					Model.method461(i1);
			}

		}
		System.gc();
		Rasterizer.method367();
		onDemandFetcher.method566();
		int k = (anInt1069 - 6) / 8 - 1;
		int j1 = (anInt1069 + 6) / 8 + 1;
		int i2 = (anInt1070 - 6) / 8 - 1;
		int l2 = (anInt1070 + 6) / 8 + 1;
		if(aBoolean1141)
		{
			k = 49;
			j1 = 50;
			i2 = 49;
			l2 = 50;
		}
		for(int l3 = k; l3 <= j1; l3++)
		{
			for(int j5 = i2; j5 <= l2; j5++)
				if(l3 == k || l3 == j1 || j5 == i2 || j5 == l2)
				{
					int j7 = onDemandFetcher.method562(0, j5, l3);
					if(j7 != -1)
						onDemandFetcher.method560(j7, 3);
					int k8 = onDemandFetcher.method562(1, j5, l3);
					if(k8 != -1)
						onDemandFetcher.method560(k8, 3);
				}
		}
	}
	
	
	
	
	private void createObject(int j, int k, int l, int i1, int j1, int k1,
						   int l1, int i2, int j2)
	{
		SpawnObjectNode class30_sub1 = null;
		for(SpawnObjectNode class30_sub1_1 = (SpawnObjectNode)aClass19_1179.reverseGetFirst(); class30_sub1_1 != null; class30_sub1_1 = (SpawnObjectNode)aClass19_1179.reverseGetNext())
		{
			if(class30_sub1_1.anInt1295 != l1 || class30_sub1_1.anInt1297 != i2 || class30_sub1_1.anInt1298 != j1 || class30_sub1_1.anInt1296 != i1)
				continue;
			class30_sub1 = class30_sub1_1;
			break;
		}

		if(class30_sub1 == null)
		{
			class30_sub1 = new SpawnObjectNode();
			class30_sub1.anInt1295 = l1;
			class30_sub1.anInt1296 = i1;
			class30_sub1.anInt1297 = i2;
			class30_sub1.anInt1298 = j1;
			method89(class30_sub1);
			aClass19_1179.insertHead(class30_sub1);
		}
		class30_sub1.anInt1291 = k;
		class30_sub1.anInt1293 = k1;
		class30_sub1.anInt1292 = l;
		class30_sub1.anInt1302 = j2;
		class30_sub1.anInt1294 = j;
	}
	
	
		private boolean clickObject(int i, int j, int k) // method66
	{
		int i1 = i >> 14 & 0x7fff;
		int j1 = worldController.method304(plane, k, j, i);
		if(j1 == -1)
			return false;
		int k1 = j1 & 0x1f;
		int l1 = j1 >> 6 & 3;
		if(k1 == 10 || k1 == 11 || k1 == 22)
		{
			ObjectDef class46 = ObjectDef.forID(i1);
			int i2;
			int j2;
			if(l1 == 0 || l1 == 2)
			{
				i2 = class46.width;
				j2 = class46.height;
			} else
			{
				i2 = class46.height;
				j2 = class46.width;
			}
			int k2 = class46.anInt768;
			if(l1 != 0)
				k2 = (k2 << l1 & 0xf) + (k2 >> 4 - l1);
			doWalkTo(2, 0, j2, 0, myPlayer.smallY[0], i2, k2, j, myPlayer.smallX[0], false, k);
		} else
		{
			doWalkTo(2, l1, 0, k1 + 1, myPlayer.smallY[0], 0, 0, j, myPlayer.smallX[0], false, k);
		}
		crossX = super.saveClickX;
		crossY = super.saveClickY;
		crossType = 2;
		crossIndex = 0;
		return true;
	}
}