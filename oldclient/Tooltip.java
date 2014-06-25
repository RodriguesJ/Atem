package com.runescape.revised.client;

public class Tooltip {

	private void drawTooltip()
	{
		if(menuActionRow < 2 && itemSelected == 0 && spellSelected == 0)
			return;
		String s;
		if(itemSelected == 1 && menuActionRow < 2)
			s = "Use " + selectedItemName + " with...";
		else
		if(spellSelected == 1 && menuActionRow < 2)
			s = spellTooltip + "...";
		else
			s = menuActionName[menuActionRow - 1];
		if(menuActionRow > 2)
			s = s + "@whi@ / " + (menuActionRow - 2) + " more options";
		chatTextDrawingArea.method390(4, 0xffffff, s, loopCycle / 1000, 15);
	}
}