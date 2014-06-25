package com.runescape.revised.client;

import com.runescape.client.SignLink;

public class Loading {

	private void loadingStages()
	{
		if(lowMem && loadingStage == 2 && Region.anInt131 != plane)
		{
			aRSImageProducer_1165.initDrawingArea();
			aTextDrawingArea_1271.drawText(0, "Loading - please wait.", 151, 257);
			aTextDrawingArea_1271.drawText(0xffffff, "Loading - please wait.", 150, 256);
			aRSImageProducer_1165.drawGraphics(4, super.graphics, 4);
			loadingStage = 1;
			aLong824 = System.currentTimeMillis();
		}
		if(loadingStage == 1)
		{
			int j = method54();
			if(j != 0 && System.currentTimeMillis() - aLong824 > 0x57e40L)
			{
				SignLink.reporterror(myUsername + " glcfb " + aLong1215 + "," + j + "," + lowMem + "," + decompressors[0] + "," + onDemandFetcher.getNodeCount() + "," + plane + "," + anInt1069 + "," + anInt1070);
				aLong824 = System.currentTimeMillis();
			}
		}
		if(loadingStage == 2 && plane != anInt985)
		{
			anInt985 = plane;
			renderMapScene(plane);
		}
	}
}