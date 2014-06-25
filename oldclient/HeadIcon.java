package com.runescape.revised.client;

public class HeadIcon {

	private void drawHeadIcon()
	{
		if(headiconDrawType != 2)
			return;
		calcEntityScreenPos((anInt934 - baseX << 7) + anInt937, anInt936 * 2, (anInt935 - baseY << 7) + anInt938);
		if(spriteDrawX > -1 && loopCycle % 20 < 10)
			headIcons[2].drawSprite(spriteDrawX - 12, spriteDrawY - 28);
	}
}