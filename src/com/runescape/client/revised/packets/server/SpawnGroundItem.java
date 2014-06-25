package com.runescape.client.revised.packets.server;

public class SpawnGroundItem {

	/**
	 * if(pktType == 64)
		{
			anInt1268 = inStream.method427();
			anInt1269 = inStream.method428();
			for(int j = anInt1268; j < anInt1268 + 8; j++)
			{
				for(int l9 = anInt1269; l9 < anInt1269 + 8; l9++)
					if(groundArray[plane][j][l9] != null)
					{
						groundArray[plane][j][l9] = null;
						spawnGroundItem(j, l9);
					}

			}

			for(SpawnObjectNode class30_sub1 = (SpawnObjectNode)aClass19_1179.reverseGetFirst(); class30_sub1 != null; class30_sub1 = (SpawnObjectNode)aClass19_1179.reverseGetNext())
				if(class30_sub1.anInt1297 >= anInt1268 && class30_sub1.anInt1297 < anInt1268 + 8 && class30_sub1.anInt1298 >= anInt1269 && class30_sub1.anInt1298 < anInt1269 + 8 && class30_sub1.anInt1295 == plane)
					class30_sub1.anInt1294 = 0;

			pktType = -1;
			return true;
		}
	 */
}