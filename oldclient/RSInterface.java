package com.runescape.revised.client;

import com.runescape.client.revised.AbstractSprite;
import com.runescape.client.revised.config.VarBit;
import com.runescape.client.revised.config.definitions.ItemDef;
import com.runescape.client.revised.constants.Skills;
import com.runescape.client.revised.graphics.image.DrawingArea;

public class RSInterface {

	private void writeInterface(int i)
	{
		RSInterface class9 = RSInterface.interfaceCache[i];
		for(int j = 0; j < class9.children.length; j++)
		{
			if(class9.children[j] == -1)
				break;
			RSInterface class9_1 = RSInterface.interfaceCache[class9.children[j]];
			if(class9_1.type == 1)
				writeInterface(class9_1.id);
			class9_1.anInt246 = 0;
			class9_1.anInt208 = 0;
		}
	}
	
	
	
	
	
	
	
	private void drawInterface(int j, int k, RSInterface class9, int l)
	{
		if(class9.type != 0 || class9.children == null)
			return;
		if(class9.aBoolean266 && anInt1026 != class9.id && anInt1048 != class9.id && anInt1039 != class9.id)
			return;
		int i1 = DrawingArea.topX;
		int j1 = DrawingArea.topY;
		int k1 = DrawingArea.bottomX;
		int l1 = DrawingArea.bottomY;
		DrawingArea.setDrawingArea(l + class9.height, k, k + class9.width, l);
		int i2 = class9.children.length;
		for(int j2 = 0; j2 < i2; j2++)
		{
			int k2 = class9.childX[j2] + k;
			int l2 = (class9.childY[j2] + l) - j;
			RSInterface class9_1 = RSInterface.interfaceCache[class9.children[j2]];
			k2 += class9_1.anInt263;
			l2 += class9_1.anInt265;
			if(class9_1.anInt214 > 0)
				drawFriendsListOrWelcomeScreen(class9_1);
			if(class9_1.type == 0)
			{
				if(class9_1.scrollPosition > class9_1.scrollMax - class9_1.height)
					class9_1.scrollPosition = class9_1.scrollMax - class9_1.height;
				if(class9_1.scrollPosition < 0)
					class9_1.scrollPosition = 0;
				drawInterface(class9_1.scrollPosition, k2, class9_1, l2);
				if(class9_1.scrollMax > class9_1.height)
					manageChatInterface(class9_1.height, class9_1.scrollPosition, l2, k2 + class9_1.width, class9_1.scrollMax);
			} else
			if(class9_1.type != 1)
				if(class9_1.type == 2)
				{
					int i3 = 0;
					for(int l3 = 0; l3 < class9_1.height; l3++)
					{
						for(int l4 = 0; l4 < class9_1.width; l4++)
						{
							int k5 = k2 + l4 * (32 + class9_1.invSpritePadX);
							int j6 = l2 + l3 * (32 + class9_1.invSpritePadY);
							if(i3 < 20)
							{
								k5 += class9_1.spritesX[i3];
								j6 += class9_1.spritesY[i3];
							}
							if(class9_1.inv[i3] > 0)
							{
								int k6 = 0;
								int j7 = 0;
								int j9 = class9_1.inv[i3] - 1;
								if(k5 > DrawingArea.topX - 32 && k5 < DrawingArea.bottomX && j6 > DrawingArea.topY - 32 && j6 < DrawingArea.bottomY || activeInterfaceType != 0 && anInt1085 == i3)
								{
									int l9 = 0;
									if(itemSelected == 1 && anInt1283 == i3 && anInt1284 == class9_1.id)
										l9 = 0xffffff;
									AbstractSprite class30_sub2_sub1_sub1_2 = ItemDef.getSprite(j9, class9_1.invStackSizes[i3], l9);
									if(class30_sub2_sub1_sub1_2 != null)
									{
										if(activeInterfaceType != 0 && anInt1085 == i3 && anInt1084 == class9_1.id)
										{
											k6 = super.mouseX - anInt1087;
											j7 = super.mouseY - anInt1088;
											if(k6 < 5 && k6 > -5)
												k6 = 0;
											if(j7 < 5 && j7 > -5)
												j7 = 0;
											if(anInt989 < 5)
											{
												k6 = 0;
												j7 = 0;
											}
											class30_sub2_sub1_sub1_2.drawSprite1(k5 + k6, j6 + j7);
											if(j6 + j7 < DrawingArea.topY && class9.scrollPosition > 0)
											{
												int i10 = (anInt945 * (DrawingArea.topY - j6 - j7)) / 3;
												if(i10 > anInt945 * 10)
													i10 = anInt945 * 10;
												if(i10 > class9.scrollPosition)
													i10 = class9.scrollPosition;
												class9.scrollPosition -= i10;
												anInt1088 += i10;
											}
											if(j6 + j7 + 32 > DrawingArea.bottomY && class9.scrollPosition < class9.scrollMax - class9.height)
											{
												int j10 = (anInt945 * ((j6 + j7 + 32) - DrawingArea.bottomY)) / 3;
												if(j10 > anInt945 * 10)
													j10 = anInt945 * 10;
												if(j10 > class9.scrollMax - class9.height - class9.scrollPosition)
													j10 = class9.scrollMax - class9.height - class9.scrollPosition;
												class9.scrollPosition += j10;
												anInt1088 -= j10;
											}
										} else
										if(atInventoryInterfaceType != 0 && atInventoryIndex == i3 && atInventoryInterface == class9_1.id)
											class30_sub2_sub1_sub1_2.drawSprite1(k5, j6);
										else
											class30_sub2_sub1_sub1_2.drawSprite(k5, j6);
										if(class30_sub2_sub1_sub1_2.anInt1444 == 33 || class9_1.invStackSizes[i3] != 1)
										{
											int k10 = class9_1.invStackSizes[i3];
											aTextDrawingArea_1270.method385(0, intToKOrMil(k10), j6 + 10 + j7, k5 + 1 + k6);
											aTextDrawingArea_1270.method385(0xffff00, intToKOrMil(k10), j6 + 9 + j7, k5 + k6);
										}
									}
								}
							} else
							if(class9_1.sprites != null && i3 < 20)
							{
								AbstractSprite class30_sub2_sub1_sub1_1 = class9_1.sprites[i3];
								if(class30_sub2_sub1_sub1_1 != null)
									class30_sub2_sub1_sub1_1.drawSprite(k5, j6);
							}
							i3++;
						}

					}

				} else
				if(class9_1.type == 3)
				{
					boolean flag = false;
					if(anInt1039 == class9_1.id || anInt1048 == class9_1.id || anInt1026 == class9_1.id)
						flag = true;
					int j3;
					if(interfaceIsSelected(class9_1))
					{
						j3 = class9_1.anInt219;
						if(flag && class9_1.anInt239 != 0)
							j3 = class9_1.anInt239;
					} else
					{
						j3 = class9_1.textColor;
						if(flag && class9_1.anInt216 != 0)
							j3 = class9_1.anInt216;
					}
					if(class9_1.aByte254 == 0)
					{
						if(class9_1.aBoolean227)
							DrawingArea.method336(class9_1.height, l2, k2, j3, class9_1.width);
						else
							DrawingArea.fillPixels(k2, class9_1.width, class9_1.height, j3, l2);
					} else
					if(class9_1.aBoolean227)
						DrawingArea.method335(j3, l2, class9_1.width, class9_1.height, 256 - (class9_1.aByte254 & 0xff), k2);
					else
						DrawingArea.method338(l2, class9_1.height, 256 - (class9_1.aByte254 & 0xff), j3, class9_1.width, k2);
				} else
				if(class9_1.type == 4)
				{
					TextDrawingArea textDrawingArea = class9_1.textDrawingAreas;
					String s = class9_1.message;
					boolean flag1 = false;
					if(anInt1039 == class9_1.id || anInt1048 == class9_1.id || anInt1026 == class9_1.id)
						flag1 = true;
					int i4;
					if(interfaceIsSelected(class9_1))
					{
						i4 = class9_1.anInt219;
						if(flag1 && class9_1.anInt239 != 0)
							i4 = class9_1.anInt239;
						if(class9_1.aString228.length() > 0)
							s = class9_1.aString228;
					} else
					{
						i4 = class9_1.textColor;
						if(flag1 && class9_1.anInt216 != 0)
							i4 = class9_1.anInt216;
					}
					if(class9_1.atActionType == 6 && aBoolean1149)
					{
						s = "Please wait...";
						i4 = class9_1.textColor;
					}
					if(DrawingArea.width == 479)
					{
						if(i4 == 0xffff00)
							i4 = 255;
						if(i4 == 49152)
							i4 = 0xffffff;
					}
					for(int l6 = l2 + textDrawingArea.anInt1497; s.length() > 0; l6 += textDrawingArea.anInt1497)
					{
						if(s.indexOf("%") != -1)
						{
							do
							{
								int k7 = s.indexOf("%1");
								if(k7 == -1)
									break;
								s = s.substring(0, k7) + interfaceIntToString(extractInterfaceValues(class9_1, 0)) + s.substring(k7 + 2);
							} while(true);
							do
							{
								int l7 = s.indexOf("%2");
								if(l7 == -1)
									break;
								s = s.substring(0, l7) + interfaceIntToString(extractInterfaceValues(class9_1, 1)) + s.substring(l7 + 2);
							} while(true);
							do
							{
								int i8 = s.indexOf("%3");
								if(i8 == -1)
									break;
								s = s.substring(0, i8) + interfaceIntToString(extractInterfaceValues(class9_1, 2)) + s.substring(i8 + 2);
							} while(true);
							do
							{
								int j8 = s.indexOf("%4");
								if(j8 == -1)
									break;
								s = s.substring(0, j8) + interfaceIntToString(extractInterfaceValues(class9_1, 3)) + s.substring(j8 + 2);
							} while(true);
							do
							{
								int k8 = s.indexOf("%5");
								if(k8 == -1)
									break;
								s = s.substring(0, k8) + interfaceIntToString(extractInterfaceValues(class9_1, 4)) + s.substring(k8 + 2);
							} while(true);
						}
						int l8 = s.indexOf("\\n");
						String s1;
						if(l8 != -1)
						{
							s1 = s.substring(0, l8);
							s = s.substring(l8 + 2);
						} else
						{
							s1 = s;
							s = "";
						}
						if(class9_1.aBoolean223)
							textDrawingArea.method382(i4, k2 + class9_1.width / 2, s1, l6, class9_1.aBoolean268);
						else
							textDrawingArea.method389(class9_1.aBoolean268, k2, i4, s1, l6);
					}

				} else
				if(class9_1.type == 5)
				{
					AbstractSprite sprite;
					if(interfaceIsSelected(class9_1))
						sprite = class9_1.sprite2;
					else
						sprite = class9_1.sprite1;
					if(sprite != null)
						sprite.drawSprite(k2, l2);
				} else
				if(class9_1.type == 6)
				{
					int k3 = Rasterizer.textureInt1;
					int j4 = Rasterizer.textureInt2;
					Rasterizer.textureInt1 = k2 + class9_1.width / 2;
					Rasterizer.textureInt2 = l2 + class9_1.height / 2;
					int i5 = Rasterizer.anIntArray1470[class9_1.anInt270] * class9_1.anInt269 >> 16;
					int l5 = Rasterizer.anIntArray1471[class9_1.anInt270] * class9_1.anInt269 >> 16;
					boolean flag2 = interfaceIsSelected(class9_1);
					int i7;
					if(flag2)
						i7 = class9_1.anInt258;
					else
						i7 = class9_1.anInt257;
					Model model;
					if(i7 == -1)
					{
						model = class9_1.method209(-1, -1, flag2);
					} else
					{
						AbstractAnimation animation = AbstractAnimation.anims[i7];
						model = class9_1.method209(animation.anIntArray354[class9_1.anInt246], animation.anIntArray353[class9_1.anInt246], flag2);
					}
					if(model != null)
						model.method482(class9_1.anInt271, 0, class9_1.anInt270, 0, i5, l5);
					Rasterizer.textureInt1 = k3;
					Rasterizer.textureInt2 = j4;
				} else
				if(class9_1.type == 7)
				{
					TextDrawingArea textDrawingArea_1 = class9_1.textDrawingAreas;
					int k4 = 0;
					for(int j5 = 0; j5 < class9_1.height; j5++)
					{
						for(int i6 = 0; i6 < class9_1.width; i6++)
						{
							if(class9_1.inv[k4] > 0)
							{
								ItemDef itemDef = ItemDef.forID(class9_1.inv[k4] - 1);
								String s2 = itemDef.name;
								if(itemDef.stackable || class9_1.invStackSizes[k4] != 1)
									s2 = s2 + " x" + intToKOrMilLongName(class9_1.invStackSizes[k4]);
								int i9 = k2 + i6 * (115 + class9_1.invSpritePadX);
								int k9 = l2 + j5 * (12 + class9_1.invSpritePadY);
								if(class9_1.aBoolean223)
									textDrawingArea_1.method382(class9_1.textColor, i9 + class9_1.width / 2, s2, k9, class9_1.aBoolean268);
								else
									textDrawingArea_1.method389(class9_1.aBoolean268, i9, class9_1.textColor, s2, k9);
							}
							k4++;
						}

					}

				}
		}

		DrawingArea.setDrawingArea(l1, i1, k1, j1);
	}
	
	
	
	
	
	
	
	
	
	private boolean animateRSInterface(int i, int j)
	{
		boolean flag1 = false;
		RSInterface class9 = RSInterface.interfaceCache[j];
		for(int k = 0; k < class9.children.length; k++)
		{
			if(class9.children[k] == -1)
				break;
			RSInterface class9_1 = RSInterface.interfaceCache[class9.children[k]];
			if(class9_1.type == 1)
				flag1 |= animateRSInterface(i, class9_1.id);
			if(class9_1.type == 6 && (class9_1.anInt257 != -1 || class9_1.anInt258 != -1))
			{
				boolean flag2 = interfaceIsSelected(class9_1);
				int l;
				if(flag2)
					l = class9_1.anInt258;
				else
					l = class9_1.anInt257;
				if(l != -1)
				{
					AbstractAnimation animation = AbstractAnimation.anims[l];
					for(class9_1.anInt208 += i; class9_1.anInt208 > animation.method258(class9_1.anInt246);)
					{
						class9_1.anInt208 -= animation.method258(class9_1.anInt246) + 1;
						class9_1.anInt246++;
						if(class9_1.anInt246 >= animation.anInt352)
						{
							class9_1.anInt246 -= animation.anInt356;
							if(class9_1.anInt246 < 0 || class9_1.anInt246 >= animation.anInt352)
								class9_1.anInt246 = 0;
						}
						flag1 = true;
					}

				}
			}
		}

		return flag1;
	}








	
	private int extractInterfaceValues(RSInterface class9, int j)
	{
		if(class9.valueIndexArray == null || j >= class9.valueIndexArray.length)
			return -2;
		try
		{
			int ai[] = class9.valueIndexArray[j];
			int k = 0;
			int l = 0;
			int i1 = 0;
			do
			{
				int j1 = ai[l++];
				int k1 = 0;
				byte byte0 = 0;
				if(j1 == 0)
					return k;
				if(j1 == 1)
					k1 = currentStats[ai[l++]];
				if(j1 == 2)
					k1 = maxStats[ai[l++]];
				if(j1 == 3)
					k1 = currentExp[ai[l++]];
				if(j1 == 4)
				{
					RSInterface class9_1 = RSInterface.interfaceCache[ai[l++]];
					int k2 = ai[l++];
					if(k2 >= 0 && k2 < ItemDef.totalItems && (!ItemDef.forID(k2).membersObject || isMembers))
					{
						for(int j3 = 0; j3 < class9_1.inv.length; j3++)
							if(class9_1.inv[j3] == k2 + 1)
								k1 += class9_1.invStackSizes[j3];

					}
				}
				if(j1 == 5)
					k1 = variousSettings[ai[l++]];
				if(j1 == 6)
					k1 = anIntArray1019[maxStats[ai[l++]] - 1];
				if(j1 == 7)
					k1 = (variousSettings[ai[l++]] * 100) / 46875;
				if(j1 == 8)
					k1 = myPlayer.combatLevel;
				if(j1 == 9)
				{
					for(int l1 = 0; l1 < Skills.skillsCount; l1++)
						if(Skills.skillEnabled[l1])
							k1 += maxStats[l1];

				}
				if(j1 == 10)
				{
					RSInterface class9_2 = RSInterface.interfaceCache[ai[l++]];
					int l2 = ai[l++] + 1;
					if(l2 >= 0 && l2 < ItemDef.totalItems && (!ItemDef.forID(l2).membersObject || isMembers))
					{
						for(int k3 = 0; k3 < class9_2.inv.length; k3++)
						{
							if(class9_2.inv[k3] != l2)
								continue;
							k1 = 0x3b9ac9ff;
							break;
						}

					}
				}
				if(j1 == 11)
					k1 = energy;
				if(j1 == 12)
					k1 = weight;
				if(j1 == 13)
				{
					int i2 = variousSettings[ai[l++]];
					int i3 = ai[l++];
					k1 = (i2 & 1 << i3) == 0 ? 0 : 1;
				}
				if(j1 == 14)
				{
					int j2 = ai[l++];
					VarBit varBit = VarBit.cache[j2];
					int l3 = varBit.anInt648;
					int i4 = varBit.anInt649;
					int j4 = varBit.anInt650;
					int k4 = anIntArray1232[j4 - i4];
					k1 = variousSettings[l3] >> i4 & k4;
				}
				if(j1 == 15)
					byte0 = 1;
				if(j1 == 16)
					byte0 = 2;
				if(j1 == 17)
					byte0 = 3;
				if(j1 == 18)
					k1 = (myPlayer.x >> 7) + baseX;
				if(j1 == 19)
					k1 = (myPlayer.y >> 7) + baseY;
				if(j1 == 20)
					k1 = ai[l++];
				if(byte0 == 0)
				{
					if(i1 == 0)
						k += k1;
					if(i1 == 1)
						k -= k1;
					if(i1 == 2 && k1 != 0)
						k /= k1;
					if(i1 == 3)
						k *= k1;
					i1 = 0;
				} else
				{
					i1 = byte0;
				}
			} while(true);
		}
		catch(Exception _ex)
		{
			return -1;
		}
	}



	private boolean interfaceIsSelected(RSInterface class9)
	{
		if(class9.anIntArray245 == null)
			return false;
		for(int i = 0; i < class9.anIntArray245.length; i++)
		{
			int j = extractInterfaceValues(class9, i);
			int k = class9.anIntArray212[i];
			if(class9.anIntArray245[i] == 2)
			{
				if(j >= k)
					return false;
			} else
			if(class9.anIntArray245[i] == 3)
			{
				if(j <= k)
					return false;
			} else
			if(class9.anIntArray245[i] == 4)
			{
				if(j == k)
					return false;
			} else
			if(j != k)
				return false;
		}

		return true;
	}
	
	
	
	
	
	private void clearTopInterfaces()
	{
		stream.putPacketID(130);
		if(invOverlayInterfaceID != -1)
		{
			invOverlayInterfaceID = -1;
			needDrawTabArea = true;
			aBoolean1149 = false;
			tabAreaAltered = true;
		}
		if(backDialogID != -1)
		{
			backDialogID = -1;
			inputTaken = true;
			aBoolean1149 = false;
		}
		openInterfaceID = -1;
	}
}