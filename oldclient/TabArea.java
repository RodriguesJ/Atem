package com.runescape.revised.client;

public class TabArea {

	private void drawTabArea()
	{
		aRSImageProducer_1163.initDrawingArea();
		Rasterizer.anIntArray1472 = anIntArray1181;
		invBack.method361(0, 0);
		if(invOverlayInterfaceID != -1)
			drawInterface(0, 0, RSInterface.interfaceCache[invOverlayInterfaceID], 0);
		else
		if(tabInterfaceIDs[tabID] != -1)
			drawInterface(0, 0, RSInterface.interfaceCache[tabInterfaceIDs[tabID]], 0);
		if(menuOpen && menuScreenArea == 1)
			drawMenu();
		aRSImageProducer_1163.drawGraphics(205, super.graphics, 553);
		aRSImageProducer_1165.initDrawingArea();
		Rasterizer.anIntArray1472 = anIntArray1182;
	}
	
	
	
	
	private void processTabClick()
	{
		if(super.clickMode3 == 1)
		{
			if(super.saveClickX >= 539 && super.saveClickX <= 573 && super.saveClickY >= 169 && super.saveClickY < 205 && tabInterfaceIDs[0] != -1)
			{
				needDrawTabArea = true;
				tabID = 0;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 569 && super.saveClickX <= 599 && super.saveClickY >= 168 && super.saveClickY < 205 && tabInterfaceIDs[1] != -1)
			{
				needDrawTabArea = true;
				tabID = 1;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 597 && super.saveClickX <= 627 && super.saveClickY >= 168 && super.saveClickY < 205 && tabInterfaceIDs[2] != -1)
			{
				needDrawTabArea = true;
				tabID = 2;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 625 && super.saveClickX <= 669 && super.saveClickY >= 168 && super.saveClickY < 203 && tabInterfaceIDs[3] != -1)
			{
				needDrawTabArea = true;
				tabID = 3;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 666 && super.saveClickX <= 696 && super.saveClickY >= 168 && super.saveClickY < 205 && tabInterfaceIDs[4] != -1)
			{
				needDrawTabArea = true;
				tabID = 4;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 694 && super.saveClickX <= 724 && super.saveClickY >= 168 && super.saveClickY < 205 && tabInterfaceIDs[5] != -1)
			{
				needDrawTabArea = true;
				tabID = 5;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 722 && super.saveClickX <= 756 && super.saveClickY >= 169 && super.saveClickY < 205 && tabInterfaceIDs[6] != -1)
			{
				needDrawTabArea = true;
				tabID = 6;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 540 && super.saveClickX <= 574 && super.saveClickY >= 466 && super.saveClickY < 502 && tabInterfaceIDs[7] != -1)
			{
				needDrawTabArea = true;
				tabID = 7;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 572 && super.saveClickX <= 602 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[8] != -1)
			{
				needDrawTabArea = true;
				tabID = 8;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 599 && super.saveClickX <= 629 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[9] != -1)
			{
				needDrawTabArea = true;
				tabID = 9;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 627 && super.saveClickX <= 671 && super.saveClickY >= 467 && super.saveClickY < 502 && tabInterfaceIDs[10] != -1)
			{
				needDrawTabArea = true;
				tabID = 10;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 669 && super.saveClickX <= 699 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[11] != -1)
			{
				needDrawTabArea = true;
				tabID = 11;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 696 && super.saveClickX <= 726 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[12] != -1)
			{
				needDrawTabArea = true;
				tabID = 12;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 724 && super.saveClickX <= 758 && super.saveClickY >= 466 && super.saveClickY < 502 && tabInterfaceIDs[13] != -1)
			{
				needDrawTabArea = true;
				tabID = 13;
				tabAreaAltered = true;
			}
		}
	}
}