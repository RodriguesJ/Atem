package com.runescape.revised.client;

import com.runescape.client.revised.packets.server.StillGraphic;

public class Graphics {

	private void createStationaryGraphics()
	{
		StillGraphic class30_sub2_sub4_sub3 = (StillGraphic)aClass19_1056.reverseGetFirst();
		for(; class30_sub2_sub4_sub3 != null; class30_sub2_sub4_sub3 = (StillGraphic)aClass19_1056.reverseGetNext())
			if(class30_sub2_sub4_sub3.anInt1560 != plane || class30_sub2_sub4_sub3.aBoolean1567)
				class30_sub2_sub4_sub3.unlink();
			else
			if(loopCycle >= class30_sub2_sub4_sub3.anInt1564)
			{
				class30_sub2_sub4_sub3.method454(anInt945);
				if(class30_sub2_sub4_sub3.aBoolean1567)
					class30_sub2_sub4_sub3.unlink();
				else
					worldController.method285(class30_sub2_sub4_sub3.anInt1560, 0, class30_sub2_sub4_sub3.anInt1563, -1, class30_sub2_sub4_sub3.anInt1562, 60, class30_sub2_sub4_sub3.anInt1561, class30_sub2_sub4_sub3, false);
			}

	}
}