package com.runescape.client.revised.packets.server;

public class SeventyThree {

	/**
	 * if(pktType == 73 || pktType == 241)
		{
			
//mapReset();
int l2 = anInt1069;
			int i11 = anInt1070;
			if(pktType == 73)
			{
				l2 = inStream.method435();
				i11 = inStream.readUnsignedWord();
				aBoolean1159 = false;
			}
			if(pktType == 241)
			{
				i11 = inStream.method435();
				inStream.initBitAccess();
				for(int j16 = 0; j16 < 4; j16++)
				{
					for(int l20 = 0; l20 < 13; l20++)
					{
						for(int j23 = 0; j23 < 13; j23++)
						{
							int i26 = inStream.readBits(1);
							if(i26 == 1)
								anIntArrayArrayArray1129[j16][l20][j23] = inStream.readBits(26);
							else
								anIntArrayArrayArray1129[j16][l20][j23] = -1;
						}

					}

				}

				inStream.finishBitAccess();
				l2 = inStream.readUnsignedWord();
				aBoolean1159 = true;
			}
			if(anInt1069 == l2 && anInt1070 == i11 && loadingStage == 2)
			{
				pktType = -1;
				return true;
			}
			anInt1069 = l2;
			anInt1070 = i11;
			baseX = (anInt1069 - 6) * 8;
			baseY = (anInt1070 - 6) * 8;
			aBoolean1141 = (anInt1069 / 8 == 48 || anInt1069 / 8 == 49) && anInt1070 / 8 == 48;
			if(anInt1069 / 8 == 48 && anInt1070 / 8 == 148)
				aBoolean1141 = true;
			loadingStage = 1;
			aLong824 = System.currentTimeMillis();
			aRSImageProducer_1165.initDrawingArea();
			aTextDrawingArea_1271.drawText(0, "Loading - please wait.", 151, 257);
			aTextDrawingArea_1271.drawText(0xffffff, "Loading - please wait.", 150, 256);
			aRSImageProducer_1165.drawGraphics(4, super.graphics, 4);
			if(pktType == 73)
			{
				int k16 = 0;
				for(int i21 = (anInt1069 - 6) / 8; i21 <= (anInt1069 + 6) / 8; i21++)
				{
					for(int k23 = (anInt1070 - 6) / 8; k23 <= (anInt1070 + 6) / 8; k23++)
						k16++;

				}

				aByteArrayArray1183 = new byte[k16][];
				aByteArrayArray1247 = new byte[k16][];
				anIntArray1234 = new int[k16];
				anIntArray1235 = new int[k16];
				anIntArray1236 = new int[k16];
				k16 = 0;
				for(int l23 = (anInt1069 - 6) / 8; l23 <= (anInt1069 + 6) / 8; l23++)
				{
					for(int j26 = (anInt1070 - 6) / 8; j26 <= (anInt1070 + 6) / 8; j26++)
					{
						anIntArray1234[k16] = (l23 << 8) + j26;
						if(aBoolean1141 && (j26 == 49 || j26 == 149 || j26 == 147 || l23 == 50 || l23 == 49 && j26 == 47))
						{
							anIntArray1235[k16] = -1;
							anIntArray1236[k16] = -1;
							k16++;
						} else
						{
							int k28 = anIntArray1235[k16] = onDemandFetcher.method562(0, j26, l23);
							if(k28 != -1)
								onDemandFetcher.method558(3, k28);
							int j30 = anIntArray1236[k16] = onDemandFetcher.method562(1, j26, l23);
							if(j30 != -1)
								onDemandFetcher.method558(3, j30);
							k16++;
						}
					}

				}

			}
			if(pktType == 241)
			{
				int l16 = 0;
				int ai[] = new int[676];
				for(int i24 = 0; i24 < 4; i24++)
				{
					for(int k26 = 0; k26 < 13; k26++)
					{
						for(int l28 = 0; l28 < 13; l28++)
						{
							int k30 = anIntArrayArrayArray1129[i24][k26][l28];
							if(k30 != -1)
							{
								int k31 = k30 >> 14 & 0x3ff;
								int i32 = k30 >> 3 & 0x7ff;
								int k32 = (k31 / 8 << 8) + i32 / 8;
								for(int j33 = 0; j33 < l16; j33++)
								{
									if(ai[j33] != k32)
										continue;
									k32 = -1;
									break;
								}

								if(k32 != -1)
									ai[l16++] = k32;
							}
						}

					}

				}

				aByteArrayArray1183 = new byte[l16][];
				aByteArrayArray1247 = new byte[l16][];
				anIntArray1234 = new int[l16];
				anIntArray1235 = new int[l16];
				anIntArray1236 = new int[l16];
				for(int l26 = 0; l26 < l16; l26++)
				{
					int i29 = anIntArray1234[l26] = ai[l26];
					int l30 = i29 >> 8 & 0xff;
					int l31 = i29 & 0xff;
					int j32 = anIntArray1235[l26] = onDemandFetcher.method562(0, l31, l30);
					if(j32 != -1)
						onDemandFetcher.method558(3, j32);
					int i33 = anIntArray1236[l26] = onDemandFetcher.method562(1, l31, l30);
					if(i33 != -1)
						onDemandFetcher.method558(3, i33);
				}

			}
			int i17 = baseX - anInt1036;
			int j21 = baseY - anInt1037;
			anInt1036 = baseX;
			anInt1037 = baseY;
			for(int j24 = 0; j24 < 16384; j24++)
			{
				NPC npc = sessionNPCs[j24];
				if(npc != null)
				{
					for(int j29 = 0; j29 < 10; j29++)
					{
						npc.smallX[j29] -= i17;
						npc.smallY[j29] -= j21;
					}

					npc.x -= i17 * 128;
					npc.y -= j21 * 128;
				}
			}

			for(int i27 = 0; i27 < maxPlayers; i27++)
			{
				Player player = playerArray[i27];
				if(player != null)
				{
					for(int i31 = 0; i31 < 10; i31++)
					{
						player.smallX[i31] -= i17;
						player.smallY[i31] -= j21;
					}

					player.x -= i17 * 128;
					player.y -= j21 * 128;
				}
			}

			aBoolean1080 = true;
			byte byte1 = 0;
			byte byte2 = 104;
			byte byte3 = 1;
			if(i17 < 0)
			{
				byte1 = 103;
				byte2 = -1;
				byte3 = -1;
			}
			byte byte4 = 0;
			byte byte5 = 104;
			byte byte6 = 1;
			if(j21 < 0)
			{
				byte4 = 103;
				byte5 = -1;
				byte6 = -1;
			}
			for(int k33 = byte1; k33 != byte2; k33 += byte3)
			{
				for(int l33 = byte4; l33 != byte5; l33 += byte6)
				{
					int i34 = k33 + i17;
					int j34 = l33 + j21;
					for(int k34 = 0; k34 < 4; k34++)
						if(i34 >= 0 && j34 >= 0 && i34 < 104 && j34 < 104)
							groundArray[k34][k33][l33] = groundArray[k34][i34][j34];
						else
							groundArray[k34][k33][l33] = null;

				}

			}

			for(SpawnObjectNode class30_sub1_1 = (SpawnObjectNode)aClass19_1179.reverseGetFirst(); class30_sub1_1 != null; class30_sub1_1 = (SpawnObjectNode)aClass19_1179.reverseGetNext())
			{
				class30_sub1_1.anInt1297 -= i17;
				class30_sub1_1.anInt1298 -= j21;
				if(class30_sub1_1.anInt1297 < 0 || class30_sub1_1.anInt1298 < 0 || class30_sub1_1.anInt1297 >= 104 || class30_sub1_1.anInt1298 >= 104)
					class30_sub1_1.unlink();
			}

			if(destX != 0)
			{
				destX -= i17;
				destY -= j21;
			}
			aBoolean1160 = false;
			pktType = -1;
			return true;
		}
	 */
}