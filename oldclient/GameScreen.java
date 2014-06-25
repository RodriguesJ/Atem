package com.runescape.revised.client;

public class GameScreen {

	private void drawGameScreen()
	{
		if(welcomeScreenRaised)
		{
			welcomeScreenRaised = false;
			backLeftIP1.drawGraphics(4, super.graphics, 0);
			backLeftIP2.drawGraphics(357, super.graphics, 0);
			backRightIP1.drawGraphics(4, super.graphics, 722);
			backRightIP2.drawGraphics(205, super.graphics, 743);
			backTopIP1.drawGraphics(0, super.graphics, 0);
			backVmidIP1.drawGraphics(4, super.graphics, 516);
			backVmidIP2.drawGraphics(205, super.graphics, 516);
			backVmidIP3.drawGraphics(357, super.graphics, 496);
			backVmidIP2_2.drawGraphics(338, super.graphics, 0);
			needDrawTabArea = true;
			inputTaken = true;
			tabAreaAltered = true;
			aBoolean1233 = true;
			if(loadingStage != 2)
			{
				aRSImageProducer_1165.drawGraphics(4, super.graphics, 4);
				aRSImageProducer_1164.drawGraphics(4, super.graphics, 550);
			}
		}
		if(loadingStage == 2)
			moveCameraWithPlayer();
		if(menuOpen && menuScreenArea == 1)
			needDrawTabArea = true;
		if(invOverlayInterfaceID != -1)
		{
			boolean flag1 = animateRSInterface(anInt945, invOverlayInterfaceID);
			if(flag1)
				needDrawTabArea = true;
		}
		if(atInventoryInterfaceType == 2)
			needDrawTabArea = true;
		if(activeInterfaceType == 2)
			needDrawTabArea = true;
		if(needDrawTabArea)
		{
			drawTabArea();
			needDrawTabArea = false;
		}
		if(backDialogID == -1)
		{
			aClass9_1059.scrollPosition = anInt1211 - anInt1089 - 77;
			if(super.mouseX > 448 && super.mouseX < 560 && super.mouseY > 332)
				method65(463, 77, super.mouseX - 17, super.mouseY - 357, aClass9_1059, 0, false, anInt1211);
			int i = anInt1211 - 77 - aClass9_1059.scrollPosition;
			if(i < 0)
				i = 0;
			if(i > anInt1211 - 77)
				i = anInt1211 - 77;
			if(anInt1089 != i)
			{
				anInt1089 = i;
				inputTaken = true;
			}
		}
		if(backDialogID != -1)
		{
			boolean flag2 = animateRSInterface(anInt945, backDialogID);
			if(flag2)
				inputTaken = true;
		}
		if(atInventoryInterfaceType == 3)
			inputTaken = true;
		if(activeInterfaceType == 3)
			inputTaken = true;
		if(clickToContinueString != null)
			inputTaken = true;
		if(menuOpen && menuScreenArea == 2)
			inputTaken = true;
		if(inputTaken)
		{
			drawChatArea();
			inputTaken = false;
		}
		if(loadingStage == 2)
		{
			drawMinimap();
			aRSImageProducer_1164.drawGraphics(4, super.graphics, 550);
		}
		if(anInt1054 != -1)
			tabAreaAltered = true;
		if(tabAreaAltered)
		{
			if(anInt1054 != -1 && anInt1054 == tabID)
			{
				anInt1054 = -1;
				stream.putPacketID(120);
				stream.putLEShort(tabID);
			}
			tabAreaAltered = false;
			aRSImageProducer_1125.initDrawingArea();
			backHmid1.method361(0, 0);
			if(invOverlayInterfaceID == -1)
			{
				if(tabInterfaceIDs[tabID] != -1)
				{
					if(tabID == 0)
						redStone1.method361(22, 10);
					if(tabID == 1)
						redStone2.method361(54, 8);
					if(tabID == 2)
						redStone2.method361(82, 8);
					if(tabID == 3)
						redStone3.method361(110, 8);
					if(tabID == 4)
						redStone2_2.method361(153, 8);
					if(tabID == 5)
						redStone2_2.method361(181, 8);
					if(tabID == 6)
						redStone1_2.method361(209, 9);
				}
				if(tabInterfaceIDs[0] != -1 && (anInt1054 != 0 || loopCycle % 20 < 10))
					sideIcons[0].method361(29, 13);
				if(tabInterfaceIDs[1] != -1 && (anInt1054 != 1 || loopCycle % 20 < 10))
					sideIcons[1].method361(53, 11);
				if(tabInterfaceIDs[2] != -1 && (anInt1054 != 2 || loopCycle % 20 < 10))
					sideIcons[2].method361(82, 11);
				if(tabInterfaceIDs[3] != -1 && (anInt1054 != 3 || loopCycle % 20 < 10))
					sideIcons[3].method361(115, 12);
				if(tabInterfaceIDs[4] != -1 && (anInt1054 != 4 || loopCycle % 20 < 10))
					sideIcons[4].method361(153, 13);
				if(tabInterfaceIDs[5] != -1 && (anInt1054 != 5 || loopCycle % 20 < 10))
					sideIcons[5].method361(180, 11);
				if(tabInterfaceIDs[6] != -1 && (anInt1054 != 6 || loopCycle % 20 < 10))
					sideIcons[6].method361(208, 13);
			}
			aRSImageProducer_1125.drawGraphics(160, super.graphics, 516);
			aRSImageProducer_1124.initDrawingArea();
			backBase2.method361(0, 0);
			if(invOverlayInterfaceID == -1)
			{
				if(tabInterfaceIDs[tabID] != -1)
				{
					if(tabID == 7)
						redStone1_3.method361(42, 0);
					if(tabID == 8)
						redStone2_3.method361(74, 0);
					if(tabID == 9)
						redStone2_3.method361(102, 0);
					if(tabID == 10)
						redStone3_2.method361(130, 1);
					if(tabID == 11)
						redStone2_4.method361(173, 0);
					if(tabID == 12)
						redStone2_4.method361(201, 0);
					if(tabID == 13)
						redStone1_4.method361(229, 0);
				}
				if(tabInterfaceIDs[8] != -1 && (anInt1054 != 8 || loopCycle % 20 < 10))
					sideIcons[7].method361(74, 2);
				if(tabInterfaceIDs[9] != -1 && (anInt1054 != 9 || loopCycle % 20 < 10))
					sideIcons[8].method361(102, 3);
				if(tabInterfaceIDs[10] != -1 && (anInt1054 != 10 || loopCycle % 20 < 10))
					sideIcons[9].method361(137, 4);
				if(tabInterfaceIDs[11] != -1 && (anInt1054 != 11 || loopCycle % 20 < 10))
					sideIcons[10].method361(174, 2);
				if(tabInterfaceIDs[12] != -1 && (anInt1054 != 12 || loopCycle % 20 < 10))
					sideIcons[11].method361(201, 2);
				if(tabInterfaceIDs[13] != -1 && (anInt1054 != 13 || loopCycle % 20 < 10))
					sideIcons[12].method361(226, 2);
			}
			aRSImageProducer_1124.drawGraphics(466, super.graphics, 496);
			aRSImageProducer_1165.initDrawingArea();
		}
		if(aBoolean1233)
		{
			aBoolean1233 = false;
			aRSImageProducer_1123.initDrawingArea();
			backBase1.method361(0, 0);
			aTextDrawingArea_1271.method382(0xffffff, 55, "Public chat", 28, true);
			if(publicChatMode == 0)
				aTextDrawingArea_1271.method382(65280, 55, "On", 41, true);
			if(publicChatMode == 1)
				aTextDrawingArea_1271.method382(0xffff00, 55, "Friends", 41, true);
			if(publicChatMode == 2)
				aTextDrawingArea_1271.method382(0xff0000, 55, "Off", 41, true);
			if(publicChatMode == 3)
				aTextDrawingArea_1271.method382(65535, 55, "Hide", 41, true);
			aTextDrawingArea_1271.method382(0xffffff, 184, "Private chat", 28, true);
			if(privateChatMode == 0)
				aTextDrawingArea_1271.method382(65280, 184, "On", 41, true);
			if(privateChatMode == 1)
				aTextDrawingArea_1271.method382(0xffff00, 184, "Friends", 41, true);
			if(privateChatMode == 2)
				aTextDrawingArea_1271.method382(0xff0000, 184, "Off", 41, true);
			aTextDrawingArea_1271.method382(0xffffff, 324, "Trade/compete", 28, true);
			if(tradeMode == 0)
				aTextDrawingArea_1271.method382(65280, 324, "On", 41, true);
			if(tradeMode == 1)
				aTextDrawingArea_1271.method382(0xffff00, 324, "Friends", 41, true);
			if(tradeMode == 2)
				aTextDrawingArea_1271.method382(0xff0000, 324, "Off", 41, true);
			aTextDrawingArea_1271.method382(0xffffff, 458, "Report abuse", 33, true);
			aRSImageProducer_1123.drawGraphics(453, super.graphics, 0);
			aRSImageProducer_1165.initDrawingArea();
		}
		anInt945 = 0;
	}
	
	
	
	
	
	private void draw3dScreen()
	{
		drawSplitPrivateChat();
		if(crossType == 1)
		{
			crosses[crossIndex / 100].drawSprite(crossX - 8 - 4, crossY - 8 - 4);
			anInt1142++;
			if(anInt1142 > 67)
			{
				anInt1142 = 0;
				stream.putPacketID(78);
			}
		}
		if(crossType == 2)
			crosses[4 + crossIndex / 100].drawSprite(crossX - 8 - 4, crossY - 8 - 4);
		if(anInt1018 != -1)
		{
			animateRSInterface(anInt945, anInt1018);
			drawInterface(0, 0, RSInterface.interfaceCache[anInt1018], 0);
		}
		if(openInterfaceID != -1)
		{
			animateRSInterface(anInt945, openInterfaceID);
			drawInterface(0, 0, RSInterface.interfaceCache[openInterfaceID], 0);
		}
		method70();
		if(!menuOpen)
		{
			processRightClick();
			drawTooltip();
		} else
		if(menuScreenArea == 0)
			drawMenu();
		if(anInt1055 == 1)
			headIcons[1].drawSprite(472, 296);
		if(fpsOn)
		{
			char c = '\u01FB';
			int k = 20;
			int i1 = 0xffff00;
			if(super.fps < 15)
				i1 = 0xff0000;
			aTextDrawingArea_1271.method380("Fps:" + super.fps, c, i1, k);
			k += 15;
			Runtime runtime = Runtime.getRuntime();
			int j1 = (int)((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
			i1 = 0xffff00;
			if(j1 > 0x2000000 && lowMem)
				i1 = 0xff0000;
			aTextDrawingArea_1271.method380("Mem:" + j1 + "k", c, 0xffff00, k);
			k += 15;
		}
		if(anInt1104 != 0)
		{
			int j = anInt1104 / 50;
			int l = j / 60;
			j %= 60;
			if(j < 10)
				aTextDrawingArea_1271.method385(0xffff00, "System update in: " + l + ":0" + j, 329, 4);
			else
				aTextDrawingArea_1271.method385(0xffff00, "System update in: " + l + ":" + j, 329, 4);
			anticheat10++;
			if(anticheat10 > 75)
			{
				anticheat10 = 0;
				stream.putPacketID(148);
			}
		}
	}
}