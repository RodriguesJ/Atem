package com.runescape.revised.client;

import com.runescape.client.revised.config.definitions.NPCDef;
import com.runescape.client.revised.graphics.image.DrawingArea;

public class Entity {

	private void updateEntities()
	{
		try{
			int anInt974 = 0;
			for(int j = -1; j < playerCount + npcCount; j++)
		{
			Object obj;
			if(j == -1)
				obj = myPlayer;
			else
			if(j < playerCount)
				obj = playerArray[playerIndices[j]];
			else
				obj = sessionNPCs[npcIndices[j - playerCount]];
			if(obj == null || !((Entity) (obj)).isVisible())
				continue;
			if(obj instanceof NPC)
			{
				NPCDef entityDef = ((NPC)obj).desc;
				if(entityDef.childrenIDs != null)
					entityDef = entityDef.method161();
				if(entityDef == null)
					continue;
			}
			if(j < playerCount)
			{
				int l = 30;
				Player player = (Player)obj;
				if(player.headIcon != 0)
				{
					npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
					if(spriteDrawX > -1)
					{
						for(int i2 = 0; i2 < 8; i2++)
							if((player.headIcon & 1 << i2) != 0)
							{
								headIcons[i2].drawSprite(spriteDrawX - 12, spriteDrawY - l);
								l -= 25;
							}

					}
				}
				if(j >= 0 && headiconDrawType == 10 && anInt933 == playerIndices[j])
				{
					npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
					if(spriteDrawX > -1)
						headIcons[7].drawSprite(spriteDrawX - 12, spriteDrawY - l);
				}
			} else
			{
				NPCDef entityDef_1 = ((NPC)obj).desc;
				if(entityDef_1.anInt75 >= 0 && entityDef_1.anInt75 < headIcons.length)
				{
					npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
					if(spriteDrawX > -1)
						headIcons[entityDef_1.anInt75].drawSprite(spriteDrawX - 12, spriteDrawY - 30);
				}
				if(headiconDrawType == 1 && anInt1222 == npcIndices[j - playerCount] && loopCycle % 20 < 10)
				{
					npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
					if(spriteDrawX > -1)
						headIcons[2].drawSprite(spriteDrawX - 12, spriteDrawY - 28);
				}
			}
			if(((Entity) (obj)).textSpoken != null && (j >= playerCount || publicChatMode == 0 || publicChatMode == 3 || publicChatMode == 1 && isFriendOrSelf(((Player)obj).name)))
			{
				npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height);
				if(spriteDrawX > -1 && anInt974 < anInt975)
				{
					anIntArray979[anInt974] = chatTextDrawingArea.method384(((Entity) (obj)).textSpoken) / 2;
					anIntArray978[anInt974] = chatTextDrawingArea.anInt1497;
					anIntArray976[anInt974] = spriteDrawX;
					anIntArray977[anInt974] = spriteDrawY;
					anIntArray980[anInt974] = ((Entity) (obj)).anInt1513;
					anIntArray981[anInt974] = ((Entity) (obj)).anInt1531;
					anIntArray982[anInt974] = ((Entity) (obj)).textCycle;
					aStringArray983[anInt974++] = ((Entity) (obj)).textSpoken;
					if(anInt1249 == 0 && ((Entity) (obj)).anInt1531 >= 1 && ((Entity) (obj)).anInt1531 <= 3)
					{
						anIntArray978[anInt974] += 10;
						anIntArray977[anInt974] += 5;
					}
					if(anInt1249 == 0 && ((Entity) (obj)).anInt1531 == 4)
						anIntArray979[anInt974] = 60;
					if(anInt1249 == 0 && ((Entity) (obj)).anInt1531 == 5)
						anIntArray978[anInt974] += 5;
				}
			}
			if(((Entity) (obj)).loopCycleStatus > loopCycle)
			{ try{
				npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
				if(spriteDrawX > -1)
				{
					int i1 = (((Entity) (obj)).currentHealth * 30) / ((Entity) (obj)).maxHealth;
					if(i1 > 30)
						i1 = 30;
					DrawingArea.method336(5, spriteDrawY - 3, spriteDrawX - 15, 65280, i1);
					DrawingArea.method336(5, spriteDrawY - 3, (spriteDrawX - 15) + i1, 0xff0000, 30 - i1);
				}
			}catch(Exception e){ }
			}
			for(int j1 = 0; j1 < 4; j1++)
				if(((Entity) (obj)).hitsLoopCycle[j1] > loopCycle)
				{
					npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height / 2);
					if(spriteDrawX > -1)
					{
						if(j1 == 1)
							spriteDrawY -= 20;
						if(j1 == 2)
						{
							spriteDrawX -= 15;
							spriteDrawY -= 10;
						}
						if(j1 == 3)
						{
							spriteDrawX += 15;
							spriteDrawY -= 10;
						}
						hitMarks[((Entity) (obj)).hitMarkTypes[j1]].drawSprite(spriteDrawX - 12, spriteDrawY - 12);
						aTextDrawingArea_1270.drawText(0, String.valueOf(((Entity) (obj)).hitArray[j1]), spriteDrawY + 4, spriteDrawX);
						aTextDrawingArea_1270.drawText(0xffffff, String.valueOf(((Entity) (obj)).hitArray[j1]), spriteDrawY + 3, spriteDrawX - 1);
					}
				}

		}
		for(int k = 0; k < anInt974; k++)
		{
			int k1 = anIntArray976[k];
			int l1 = anIntArray977[k];
			int j2 = anIntArray979[k];
			int k2 = anIntArray978[k];
			boolean flag = true;
			while(flag) 
			{
				flag = false;
				for(int l2 = 0; l2 < k; l2++)
					if(l1 + 2 > anIntArray977[l2] - anIntArray978[l2] && l1 - k2 < anIntArray977[l2] + 2 && k1 - j2 < anIntArray976[l2] + anIntArray979[l2] && k1 + j2 > anIntArray976[l2] - anIntArray979[l2] && anIntArray977[l2] - anIntArray978[l2] < l1)
					{
						l1 = anIntArray977[l2] - anIntArray978[l2];
						flag = true;
					}

			}
			spriteDrawX = anIntArray976[k];
			spriteDrawY = anIntArray977[k] = l1;
			String s = aStringArray983[k];
			if(anInt1249 == 0)
			{
				int i3 = 0xffff00;
				if(anIntArray980[k] < 6)
					i3 = anIntArray965[anIntArray980[k]];
				if(anIntArray980[k] == 6)
					i3 = anInt1265 % 20 >= 10 ? 0xffff00 : 0xff0000;
				if(anIntArray980[k] == 7)
					i3 = anInt1265 % 20 >= 10 ? 65535 : 255;
				if(anIntArray980[k] == 8)
					i3 = anInt1265 % 20 >= 10 ? 0x80ff80 : 45056;
				if(anIntArray980[k] == 9)
				{
					int j3 = 150 - anIntArray982[k];
					if(j3 < 50)
						i3 = 0xff0000 + 1280 * j3;
					else
					if(j3 < 100)
						i3 = 0xffff00 - 0x50000 * (j3 - 50);
					else
					if(j3 < 150)
						i3 = 65280 + 5 * (j3 - 100);
				}
				if(anIntArray980[k] == 10)
				{
					int k3 = 150 - anIntArray982[k];
					if(k3 < 50)
						i3 = 0xff0000 + 5 * k3;
					else
					if(k3 < 100)
						i3 = 0xff00ff - 0x50000 * (k3 - 50);
					else
					if(k3 < 150)
						i3 = (255 + 0x50000 * (k3 - 100)) - 5 * (k3 - 100);
				}
				if(anIntArray980[k] == 11)
				{
					int l3 = 150 - anIntArray982[k];
					if(l3 < 50)
						i3 = 0xffffff - 0x50005 * l3;
					else
					if(l3 < 100)
						i3 = 65280 + 0x50005 * (l3 - 50);
					else
					if(l3 < 150)
						i3 = 0xffffff - 0x50000 * (l3 - 100);
				}
				if(anIntArray981[k] == 0)
				{
					chatTextDrawingArea.drawText(0, s, spriteDrawY + 1, spriteDrawX);
					chatTextDrawingArea.drawText(i3, s, spriteDrawY, spriteDrawX);
				}
				if(anIntArray981[k] == 1)
				{
					chatTextDrawingArea.method386(0, s, spriteDrawX, anInt1265, spriteDrawY + 1);
					chatTextDrawingArea.method386(i3, s, spriteDrawX, anInt1265, spriteDrawY);
				}
				if(anIntArray981[k] == 2)
				{
					chatTextDrawingArea.method387(spriteDrawX, s, anInt1265, spriteDrawY + 1, 0);
					chatTextDrawingArea.method387(spriteDrawX, s, anInt1265, spriteDrawY, i3);
				}
				if(anIntArray981[k] == 3)
				{
					chatTextDrawingArea.method388(150 - anIntArray982[k], s, anInt1265, spriteDrawY + 1, spriteDrawX, 0);
					chatTextDrawingArea.method388(150 - anIntArray982[k], s, anInt1265, spriteDrawY, spriteDrawX, i3);
				}
				if(anIntArray981[k] == 4)
				{
					int i4 = chatTextDrawingArea.method384(s);
					int k4 = ((150 - anIntArray982[k]) * (i4 + 100)) / 150;
					DrawingArea.setDrawingArea(334, spriteDrawX - 50, spriteDrawX + 50, 0);
					chatTextDrawingArea.method385(0, s, spriteDrawY + 1, (spriteDrawX + 50) - k4);
					chatTextDrawingArea.method385(i3, s, spriteDrawY, (spriteDrawX + 50) - k4);
					DrawingArea.defaultDrawingAreaSize();
				}
				if(anIntArray981[k] == 5)
				{
					int j4 = 150 - anIntArray982[k];
					int l4 = 0;
					if(j4 < 25)
						l4 = j4 - 25;
					else
					if(j4 > 125)
						l4 = j4 - 125;
					DrawingArea.setDrawingArea(spriteDrawY + 5, 0, 512, spriteDrawY - chatTextDrawingArea.anInt1497 - 1);
					chatTextDrawingArea.drawText(0, s, spriteDrawY + 1 + l4, spriteDrawX);
					chatTextDrawingArea.drawText(i3, s, spriteDrawY + l4, spriteDrawX);
					DrawingArea.defaultDrawingAreaSize();
				}
			} else
			{
				chatTextDrawingArea.drawText(0, s, spriteDrawY + 1, spriteDrawX);
				chatTextDrawingArea.drawText(0xffff00, s, spriteDrawY, spriteDrawX);
			}
		}
	}catch(Exception e){ }

	}
	
	
	
	
	
	private void entityUpdateBlock(Entity entity)
	{
		if(entity.x < 128 || entity.y < 128 || entity.x >= 13184 || entity.y >= 13184)
		{
			entity.anim = -1;
			entity.anInt1520 = -1;
			entity.anInt1547 = 0;
			entity.anInt1548 = 0;
			entity.x = entity.smallX[0] * 128 + entity.anInt1540 * 64;
			entity.y = entity.smallY[0] * 128 + entity.anInt1540 * 64;
			entity.method446();
		}
		if(entity == myPlayer && (entity.x < 1536 || entity.y < 1536 || entity.x >= 11776 || entity.y >= 11776))
		{
			entity.anim = -1;
			entity.anInt1520 = -1;
			entity.anInt1547 = 0;
			entity.anInt1548 = 0;
			entity.x = entity.smallX[0] * 128 + entity.anInt1540 * 64;
			entity.y = entity.smallY[0] * 128 + entity.anInt1540 * 64;
			entity.method446();
		}
		if(entity.anInt1547 > loopCycle)
			refreshEntityPosition(entity);
		else
		if(entity.anInt1548 >= loopCycle)
			refreshEntityFaceDirection(entity);
		else
			getDegreesToTurn(entity);
		appendFocusDestination(entity);
		appendEmote(entity);
	}

	private void refreshEntityPosition(Entity entity)
	{
		int i = entity.anInt1547 - loopCycle;
		int j = entity.anInt1543 * 128 + entity.anInt1540 * 64;
		int k = entity.anInt1545 * 128 + entity.anInt1540 * 64;
		entity.x += (j - entity.x) / i;
		entity.y += (k - entity.y) / i;
		entity.anInt1503 = 0;
		if(entity.anInt1549 == 0)
			entity.turnDirection = 1024;
		if(entity.anInt1549 == 1)
			entity.turnDirection = 1536;
		if(entity.anInt1549 == 2)
			entity.turnDirection = 0;
		if(entity.anInt1549 == 3)
			entity.turnDirection = 512;
	}

	private void refreshEntityFaceDirection(Entity entity)
	{
		if(entity.anInt1548 == loopCycle || entity.anim == -1 || entity.anInt1529 != 0 || entity.anInt1528 + 1 > AbstractAnimation.anims[entity.anim].method258(entity.anInt1527))
		{
			int i = entity.anInt1548 - entity.anInt1547;
			int j = loopCycle - entity.anInt1547;
			int k = entity.anInt1543 * 128 + entity.anInt1540 * 64;
			int l = entity.anInt1545 * 128 + entity.anInt1540 * 64;
			int i1 = entity.anInt1544 * 128 + entity.anInt1540 * 64;
			int j1 = entity.anInt1546 * 128 + entity.anInt1540 * 64;
			entity.x = (k * (i - j) + i1 * j) / i;
			entity.y = (l * (i - j) + j1 * j) / i;
		}
		entity.anInt1503 = 0;
		if(entity.anInt1549 == 0)
			entity.turnDirection = 1024;
		if(entity.anInt1549 == 1)
			entity.turnDirection = 1536;
		if(entity.anInt1549 == 2)
			entity.turnDirection = 0;
		if(entity.anInt1549 == 3)
			entity.turnDirection = 512;
		entity.anInt1552 = entity.turnDirection;
	}
	
	
	
	
	private void calcEntityScreenPos(int i, int j, int l)
	{
		if(i < 128 || l < 128 || i > 13056 || l > 13056)
		{
			spriteDrawX = -1;
			spriteDrawY = -1;
			return;
		}
		int i1 = method42(plane, l, i) - j;
		i -= xCameraPos;
		i1 -= zCameraPos;
		l -= yCameraPos;
		int j1 = Model.modelIntArray1[yCameraCurve];
		int k1 = Model.modelIntArray2[yCameraCurve];
		int l1 = Model.modelIntArray1[xCameraCurve];
		int i2 = Model.modelIntArray2[xCameraCurve];
		int j2 = l * l1 + i * i2 >> 16;
		l = l * i2 - i * l1 >> 16;
		i = j2;
		j2 = i1 * k1 - l * j1 >> 16;
		l = i1 * j1 + l * k1 >> 16;
		i1 = j2;
		if(l >= 50)
		{
			spriteDrawX = Rasterizer.textureInt1 + (i << 9) / l;
			spriteDrawY = Rasterizer.textureInt2 + (i1 << 9) / l;
		} else
		{
			spriteDrawX = -1;
			spriteDrawY = -1;
		}
	}
}