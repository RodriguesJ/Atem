package com.runescape.revised.client;

import com.runescape.client.revised.config.definitions.ItemDef;
import com.runescape.client.revised.config.definitions.NPCDef;
import com.runescape.client.revised.config.definitions.ObjectDef;
import com.runescape.client.revised.graphics.image.DrawingArea;
import com.runescape.client.revised.util.TextClass;

public class Menu {
	
	public int menuScreenArea;
	public int menuOffsetX;
	public int menuOffsetY;
	public int menuWidth;
	public int menuHeight; // anInt952
	public int[] menuActionCmd2;
	public int[] menuActionCmd3;
	public int[] menuActionID;
	public int[] menuActionCmd1;
	public int menuActionRow;
	public String[] menuActionName;
	public boolean menuOpen;
	
	public Menu() {
		menuOpen = false;
		menuActionCmd2 = new int[500];
		menuActionCmd3 = new int[500];
		menuActionID = new int[500];
		menuActionCmd1 = new int[500];
		menuActionName = new String[500];
	}

	private boolean menuHasAddFriend(int j)
	{
		if(j < 0)
			return false;
		int k = menuActionID[j];
		if(k >= 2000)
			k -= 2000;
		return k == 337;
	}
	
	
	
	
	
	private void buildAtNPCMenu(NPCDef entityDef, int i, int j, int k)
	{
		if(menuActionRow >= 400)
			return;
		if(entityDef.childrenIDs != null)
			entityDef = entityDef.method161();
		if(entityDef == null)
			return;
		if(!entityDef.aBoolean84)
			return;
		String s = entityDef.name;
		if(entityDef.combatLevel != 0)
			s = s + combatDiffColor(myPlayer.combatLevel, entityDef.combatLevel) + " (level-" + entityDef.combatLevel + ")";
		if(itemSelected == 1)
		{
			menuActionName[menuActionRow] = "Use " + selectedItemName + " with @yel@" + s;
			menuActionID[menuActionRow] = 582;
			menuActionCmd1[menuActionRow] = i;
			menuActionCmd2[menuActionRow] = k;
			menuActionCmd3[menuActionRow] = j;
			menuActionRow++;
			return;
		}
		if(spellSelected == 1)
		{
			if((spellUsableOn & 2) == 2)
			{
				menuActionName[menuActionRow] = spellTooltip + " @yel@" + s;
				menuActionID[menuActionRow] = 413;
				menuActionCmd1[menuActionRow] = i;
				menuActionCmd2[menuActionRow] = k;
				menuActionCmd3[menuActionRow] = j;
				menuActionRow++;
			}
		} else
		{
			if(entityDef.actions != null)
			{
				for(int l = 4; l >= 0; l--)
					if(entityDef.actions[l] != null && !entityDef.actions[l].equalsIgnoreCase("attack"))
					{
						menuActionName[menuActionRow] = entityDef.actions[l] + " @yel@" + s;
						if(l == 0)
							menuActionID[menuActionRow] = 20;
						if(l == 1)
							menuActionID[menuActionRow] = 412;
						if(l == 2)
							menuActionID[menuActionRow] = 225;
						if(l == 3)
							menuActionID[menuActionRow] = 965;
						if(l == 4)
							menuActionID[menuActionRow] = 478;
						menuActionCmd1[menuActionRow] = i;
						menuActionCmd2[menuActionRow] = k;
						menuActionCmd3[menuActionRow] = j;
						menuActionRow++;
					}

			}
			if(entityDef.actions != null)
			{
				for(int i1 = 4; i1 >= 0; i1--)
					if(entityDef.actions[i1] != null && entityDef.actions[i1].equalsIgnoreCase("attack"))
					{
						char c = '\0';
						if(entityDef.combatLevel > myPlayer.combatLevel)
							c = '\u07D0';
						menuActionName[menuActionRow] = entityDef.actions[i1] + " @yel@" + s;
						if(i1 == 0)
							menuActionID[menuActionRow] = 20 + c;
						if(i1 == 1)
							menuActionID[menuActionRow] = 412 + c;
						if(i1 == 2)
							menuActionID[menuActionRow] = 225 + c;
						if(i1 == 3)
							menuActionID[menuActionRow] = 965 + c;
						if(i1 == 4)
							menuActionID[menuActionRow] = 478 + c;
						menuActionCmd1[menuActionRow] = i;
						menuActionCmd2[menuActionRow] = k;
						menuActionCmd3[menuActionRow] = j;
						menuActionRow++;
					}

			}
			menuActionName[menuActionRow] = "Examine @yel@" + s + " @gre@(@whi@" + entityDef.type + "@gre@)";
			menuActionID[menuActionRow] = 1025;
			menuActionCmd1[menuActionRow] = i;
			menuActionCmd2[menuActionRow] = k;
			menuActionCmd3[menuActionRow] = j;
			menuActionRow++;
		}
	}





	private void processMenuClick()
	{
		if(activeInterfaceType != 0)
			return;
		int j = super.clickMode3;
		if(spellSelected == 1 && super.saveClickX >= 516 && super.saveClickY >= 160 && super.saveClickX <= 765 && super.saveClickY <= 205)
			j = 0;
		if(menuOpen)
		{
			if(j != 1)
			{
				int k = super.mouseX;
				int j1 = super.mouseY;
				if(menuScreenArea == 0)
				{
					k -= 4;
					j1 -= 4;
				}
				if(menuScreenArea == 1)
				{
					k -= 553;
					j1 -= 205;
				}
				if(menuScreenArea == 2)
				{
					k -= 17;
					j1 -= 357;
				}
				if(k < menuOffsetX - 10 || k > menuOffsetX + menuWidth + 10 || j1 < menuOffsetY - 10 || j1 > menuOffsetY + anInt952 + 10)
				{
					menuOpen = false;
					if(menuScreenArea == 1)
						needDrawTabArea = true;
					if(menuScreenArea == 2)
						inputTaken = true;
				}
			}
			if(j == 1)
			{
				int l = menuOffsetX;
				int k1 = menuOffsetY;
				int i2 = menuWidth;
				int k2 = super.saveClickX;
				int l2 = super.saveClickY;
				if(menuScreenArea == 0)
				{
					k2 -= 4;
					l2 -= 4;
				}
				if(menuScreenArea == 1)
				{
					k2 -= 553;
					l2 -= 205;
				}
				if(menuScreenArea == 2)
				{
					k2 -= 17;
					l2 -= 357;
				}
				int i3 = -1;
				for(int j3 = 0; j3 < menuActionRow; j3++)
				{
					int k3 = k1 + 31 + (menuActionRow - 1 - j3) * 15;
					if(k2 > l && k2 < l + i2 && l2 > k3 - 13 && l2 < k3 + 3)
						i3 = j3;
				}

				if(i3 != -1)
					doAction(i3);
				menuOpen = false;
				if(menuScreenArea == 1)
					needDrawTabArea = true;
				if(menuScreenArea == 2)
				{
					inputTaken = true;
				}
			}
		} else
		{
			if(j == 1 && menuActionRow > 0)
			{
				int i1 = menuActionID[menuActionRow - 1];
				if(i1 == 632 || i1 == 78 || i1 == 867 || i1 == 431 || i1 == 53 || i1 == 74 || i1 == 454 || i1 == 539 || i1 == 493 || i1 == 847 || i1 == 447 || i1 == 1125)
				{
					int l1 = menuActionCmd2[menuActionRow - 1];
					int j2 = menuActionCmd3[menuActionRow - 1];
					RSInterface class9 = RSInterface.interfaceCache[j2];
					if(class9.aBoolean259 || class9.aBoolean235)
					{
						aBoolean1242 = false;
						anInt989 = 0;
						anInt1084 = j2;
						anInt1085 = l1;
						activeInterfaceType = 2;
						anInt1087 = super.saveClickX;
						anInt1088 = super.saveClickY;
						if(RSInterface.interfaceCache[j2].parentID == openInterfaceID)
							activeInterfaceType = 1;
						if(RSInterface.interfaceCache[j2].parentID == backDialogID)
							activeInterfaceType = 3;
						return;
					}
				}
			}
			if(j == 1 && (anInt1253 == 1 || menuHasAddFriend(menuActionRow - 1)) && menuActionRow > 2)
				j = 2;
			if(j == 1 && menuActionRow > 0)
				doAction(menuActionRow - 1);
			if(j == 2 && menuActionRow > 0)
				determineMenuSize();
		}
	}
	
	
	
	
	
	
	
	
	private void buildInterfaceMenu(int i, RSInterface class9, int k, int l, int i1, int j1)
	{
		if(class9.type != 0 || class9.children == null || class9.aBoolean266)
			return;
		if(k < i || i1 < l || k > i + class9.width || i1 > l + class9.height)
			return;
		int k1 = class9.children.length;
		for(int l1 = 0; l1 < k1; l1++)
		{
			int i2 = class9.childX[l1] + i;
			int j2 = (class9.childY[l1] + l) - j1;
			RSInterface class9_1 = RSInterface.interfaceCache[class9.children[l1]];
			i2 += class9_1.anInt263;
			j2 += class9_1.anInt265;
			if((class9_1.anInt230 >= 0 || class9_1.anInt216 != 0) && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
				if(class9_1.anInt230 >= 0)
					anInt886 = class9_1.anInt230;
				else
					anInt886 = class9_1.id;
			if(class9_1.type == 0)
			{
				buildInterfaceMenu(i2, class9_1, k, j2, i1, class9_1.scrollPosition);
				if(class9_1.scrollMax > class9_1.height)
					method65(i2 + class9_1.width, class9_1.height, k, i1, class9_1, j2, true, class9_1.scrollMax);
			} else
			{
				if(class9_1.atActionType == 1 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
				{
					boolean flag = false;
					if(class9_1.anInt214 != 0)
						flag = buildFriendsListMenu(class9_1);
					if(!flag)
					{
						//System.out.println("1"+class9_1.tooltip + ", " + class9_1.interfaceID);
						menuActionName[menuActionRow] = class9_1.tooltip + ", " + class9_1.id;
						menuActionID[menuActionRow] = 315;
						menuActionCmd3[menuActionRow] = class9_1.id;
						menuActionRow++;
					}
				}
				if(class9_1.atActionType == 2 && spellSelected == 0 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
				{
					String s = class9_1.selectedActionName;
					if(s.indexOf(" ") != -1)
						s = s.substring(0, s.indexOf(" "));
					menuActionName[menuActionRow] = s + " @gre@" + class9_1.spellName;
					menuActionID[menuActionRow] = 626;
					menuActionCmd3[menuActionRow] = class9_1.id;
					menuActionRow++;
				}
				if(class9_1.atActionType == 3 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
				{
					menuActionName[menuActionRow] = "Close";
					menuActionID[menuActionRow] = 200;
					menuActionCmd3[menuActionRow] = class9_1.id;
					menuActionRow++;
				}
				if(class9_1.atActionType == 4 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
				{
					//System.out.println("2"+class9_1.tooltip + ", " + class9_1.interfaceID);
					menuActionName[menuActionRow] = class9_1.tooltip + ", " + class9_1.id;
					menuActionID[menuActionRow] = 169;
					menuActionCmd3[menuActionRow] = class9_1.id;
					menuActionRow++;
				}
				if(class9_1.atActionType == 5 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
				{
					//System.out.println("3"+class9_1.tooltip + ", " + class9_1.interfaceID);
					menuActionName[menuActionRow] = class9_1.tooltip + ", " + class9_1.id;
					menuActionID[menuActionRow] = 646;
					menuActionCmd3[menuActionRow] = class9_1.id;
					menuActionRow++;
				}
				if(class9_1.atActionType == 6 && !aBoolean1149 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
				{
					//System.out.println("4"+class9_1.tooltip + ", " + class9_1.interfaceID);
					menuActionName[menuActionRow] = class9_1.tooltip + ", " + class9_1.id;
					menuActionID[menuActionRow] = 679;
					menuActionCmd3[menuActionRow] = class9_1.id;
					menuActionRow++;
				}
				if(class9_1.type == 2)
				{
					int k2 = 0;
					for(int l2 = 0; l2 < class9_1.height; l2++)
					{
						for(int i3 = 0; i3 < class9_1.width; i3++)
						{
							int j3 = i2 + i3 * (32 + class9_1.invSpritePadX);
							int k3 = j2 + l2 * (32 + class9_1.invSpritePadY);
							if(k2 < 20)
							{
								j3 += class9_1.spritesX[k2];
								k3 += class9_1.spritesY[k2];
							}
							if(k >= j3 && i1 >= k3 && k < j3 + 32 && i1 < k3 + 32)
							{
								mouseInvInterfaceIndex = k2;
								lastActiveInvInterface = class9_1.id;
								if(class9_1.inv[k2] > 0)
								{
									ItemDef itemDef = ItemDef.forID(class9_1.inv[k2] - 1);
									if(itemSelected == 1 && class9_1.isInventoryInterface)
									{
										if(class9_1.id != anInt1284 || k2 != anInt1283)
										{
											menuActionName[menuActionRow] = "Use " + selectedItemName + " with @lre@" + itemDef.name;
											menuActionID[menuActionRow] = 870;
											menuActionCmd1[menuActionRow] = itemDef.id;
											menuActionCmd2[menuActionRow] = k2;
											menuActionCmd3[menuActionRow] = class9_1.id;
											menuActionRow++;
										}
									} else
									if(spellSelected == 1 && class9_1.isInventoryInterface)
									{
										if((spellUsableOn & 0x10) == 16)
										{
											menuActionName[menuActionRow] = spellTooltip + " @lre@" + itemDef.name;
											menuActionID[menuActionRow] = 543;
											menuActionCmd1[menuActionRow] = itemDef.id;
											menuActionCmd2[menuActionRow] = k2;
											menuActionCmd3[menuActionRow] = class9_1.id;
											menuActionRow++;
										}
									} else
									{
										if(class9_1.isInventoryInterface)
										{
											for(int l3 = 4; l3 >= 3; l3--)
												if(itemDef.actions != null && itemDef.actions[l3] != null)
												{
													menuActionName[menuActionRow] = itemDef.actions[l3] + " @lre@" + itemDef.name;
													if(l3 == 3)
														menuActionID[menuActionRow] = 493;
													if(l3 == 4)
														menuActionID[menuActionRow] = 847;
													menuActionCmd1[menuActionRow] = itemDef.id;
													menuActionCmd2[menuActionRow] = k2;
													menuActionCmd3[menuActionRow] = class9_1.id;
													menuActionRow++;
												} else
												if(l3 == 4)
												{
													menuActionName[menuActionRow] = "Drop @lre@" + itemDef.name;
													menuActionID[menuActionRow] = 847;
													menuActionCmd1[menuActionRow] = itemDef.id;
													menuActionCmd2[menuActionRow] = k2;
													menuActionCmd3[menuActionRow] = class9_1.id;
													menuActionRow++;
												}

										}
										if(class9_1.usableItemInterface)
										{
											menuActionName[menuActionRow] = "Use @lre@" + itemDef.name;
											menuActionID[menuActionRow] = 447;
											menuActionCmd1[menuActionRow] = itemDef.id;
											menuActionCmd2[menuActionRow] = k2;
											menuActionCmd3[menuActionRow] = class9_1.id;
											menuActionRow++;
										}
										if(class9_1.isInventoryInterface && itemDef.actions != null)
										{
											for(int i4 = 2; i4 >= 0; i4--)
												if(itemDef.actions[i4] != null)
												{
													menuActionName[menuActionRow] = itemDef.actions[i4] + " @lre@" + itemDef.name;
													if(i4 == 0)
														menuActionID[menuActionRow] = 74;
													if(i4 == 1)
														menuActionID[menuActionRow] = 454;
													if(i4 == 2)
														menuActionID[menuActionRow] = 539;
													menuActionCmd1[menuActionRow] = itemDef.id;
													menuActionCmd2[menuActionRow] = k2;
													menuActionCmd3[menuActionRow] = class9_1.id;
													menuActionRow++;
												}

										}
										if(class9_1.actions != null)
										{
											for(int j4 = 4; j4 >= 0; j4--)
												if(class9_1.actions[j4] != null)
												{
													menuActionName[menuActionRow] = class9_1.actions[j4] + " @lre@" + itemDef.name;
													if(j4 == 0)
														menuActionID[menuActionRow] = 632;
													if(j4 == 1)
														menuActionID[menuActionRow] = 78;
													if(j4 == 2)
														menuActionID[menuActionRow] = 867;
													if(j4 == 3)
														menuActionID[menuActionRow] = 431;
													if(j4 == 4)
														menuActionID[menuActionRow] = 53;
													menuActionCmd1[menuActionRow] = itemDef.id;
													menuActionCmd2[menuActionRow] = k2;
													menuActionCmd3[menuActionRow] = class9_1.id;
													menuActionRow++;
												}

										}
										menuActionName[menuActionRow] = "Examine @lre@" + itemDef.name + " @gre@(@whi@" + (class9_1.inv[k2] - 1) + "@gre@)";
										menuActionID[menuActionRow] = 1125;
										menuActionCmd1[menuActionRow] = itemDef.id;
										menuActionCmd2[menuActionRow] = k2;
										menuActionCmd3[menuActionRow] = class9_1.id;
										menuActionRow++;
									}
								}
							}
							k2++;
						}
					}
				}
			}
		}
	}




	private void drawMenu()
	{
		int i = menuOffsetX;
		int j = menuOffsetY;
		int k = menuWidth;
		int l = anInt952;
		int i1 = 0x5d5447;
		DrawingArea.method336(l, j, i, i1, k);
		DrawingArea.method336(16, j + 1, i + 1, 0, k - 2);
		DrawingArea.fillPixels(i + 1, k - 2, l - 19, 0, j + 18);
		chatTextDrawingArea.method385(i1, "Choose Option", j + 14, i + 3);
		int j1 = super.mouseX;
		int k1 = super.mouseY;
		if(menuScreenArea == 0)
		{
			j1 -= 4;
			k1 -= 4;
		}
		if(menuScreenArea == 1)
		{
			j1 -= 553;
			k1 -= 205;
		}
		if(menuScreenArea == 2)
		{
			j1 -= 17;
			k1 -= 357;
		}
		for(int l1 = 0; l1 < menuActionRow; l1++)
		{
			int i2 = j + 31 + (menuActionRow - 1 - l1) * 15;
			int j2 = 0xffffff;
			if(j1 > i && j1 < i + k && k1 > i2 - 13 && k1 < i2 + 3)
				j2 = 0xffff00;
			chatTextDrawingArea.method389(true, i + 3, j2, menuActionName[l1], i2);
		}

	}
	
	
	
	
	
	
	private void doAction(int i)
	{
		if(i < 0)
			return;
		if(inputDialogState != 0)
		{
			inputDialogState = 0;
			inputTaken = true;
		}
		int j = menuActionCmd2[i];
		int k = menuActionCmd3[i];
		int l = menuActionID[i];
		int i1 = menuActionCmd1[i];
		if(l >= 2000)
			l -= 2000;
		if(l == 582)
		{
			NPC npc = sessionNPCs[i1];
			if(npc != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, npc.smallY[0], myPlayer.smallX[0], false, npc.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.putPacketID(57);
				stream.method432(anInt1285);
				stream.method432(i1);
				stream.method431(anInt1283);
				stream.method432(anInt1284);
			}
		}
		if(l == 234)
		{
			boolean flag1 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
			if(!flag1)
				flag1 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.putPacketID(236);
			stream.method431(k + baseY);
			stream.putShort(i1);
			stream.method431(j + baseX);
		}
		if(l == 62 && method66(i1, k, j))
		{
			stream.putPacketID(192);
			stream.putShort(anInt1284);
			stream.method431(i1 >> 14 & 0x7fff);
			stream.method433(k + baseY);
			stream.method431(anInt1283);
			stream.method433(j + baseX);
			stream.putShort(anInt1285);
		}
		if(l == 511)
		{
			boolean flag2 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
			if(!flag2)
				flag2 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.putPacketID(25);
			stream.method431(anInt1284);
			stream.method432(anInt1285);
			stream.putShort(i1);
			stream.method432(k + baseY);
			stream.method433(anInt1283);
			stream.putShort(j + baseX);
		}
		if(l == 74)
		{
			stream.putPacketID(122);
			stream.method433(k);
			stream.method432(j);
			stream.method431(i1);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 315)
		{
			RSInterface class9 = RSInterface.interfaceCache[k];
			boolean flag8 = true;
			if(class9.anInt214 > 0)
				flag8 = promptUserForInput(class9);
			if(flag8)
			{
				stream.putPacketID(185);
				stream.putShort(k);
			}
		}
		if(l == 561)
		{
			Player player = playerArray[i1];
			if(player != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, player.smallY[0], myPlayer.smallX[0], false, player.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				anInt1188 += i1;
				if(anInt1188 >= 90)
				{
					stream.putPacketID(136);
					anInt1188 = 0;
				}
				stream.putPacketID(128);
				stream.putShort(i1);
			}
		}
		if(l == 20)
		{
			NPC class30_sub2_sub4_sub1_sub1_1 = sessionNPCs[i1];
			if(class30_sub2_sub4_sub1_sub1_1 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_1.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_1.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.putPacketID(155);
				stream.method431(i1);
			}
		}
		if(l == 779)
		{
			Player class30_sub2_sub4_sub1_sub2_1 = playerArray[i1];
			if(class30_sub2_sub4_sub1_sub2_1 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_1.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_1.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.putPacketID(153);
				stream.method431(i1);
			}
		}
		if(l == 516)
			if(!menuOpen)
				worldController.method312(super.saveClickY - 4, super.saveClickX - 4);
			else
				worldController.method312(k - 4, j - 4);
		if(l == 1062)
		{
			anInt924 += baseX;
			if(anInt924 >= 113)
			{
				stream.putPacketID(183);
				stream.writeDWordBigEndian(0xe63271);
				anInt924 = 0;
			}
			method66(i1, k, j);
			stream.putPacketID(228);
			stream.method432(i1 >> 14 & 0x7fff);
			stream.method432(k + baseY);
			stream.putShort(j + baseX);
		}
		if(l == 679 && !aBoolean1149)
		{
			stream.putPacketID(40);
			stream.putShort(k);
			aBoolean1149 = true;
		}
		if(l == 431)
		{
			stream.putPacketID(129);
			stream.method432(j);
			stream.putShort(k);
			stream.method432(i1);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 337 || l == 42 || l == 792 || l == 322)
		{
			String s = menuActionName[i];
			int k1 = s.indexOf("@whi@");
			if(k1 != -1)
			{
				long l3 = TextClass.longForName(s.substring(k1 + 5).trim());
				if(l == 337)
					addFriend(l3);
				if(l == 42)
					addIgnore(l3);
				if(l == 792)
					delFriend(l3);
				if(l == 322)
					delIgnore(l3);
			}
		}
		if(l == 53)
		{
			stream.putPacketID(135);
			stream.method431(j);
			stream.method432(k);
			stream.method431(i1);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 539)
		{
			stream.putPacketID(16);
			stream.method432(i1);
			stream.method433(j);
			stream.method433(k);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 484 || l == 6)
		{
			String s1 = menuActionName[i];
			int l1 = s1.indexOf("@whi@");
			if(l1 != -1)
			{
				s1 = s1.substring(l1 + 5).trim();
				String s7 = TextClass.fixName(TextClass.nameForLong(TextClass.longForName(s1)));
				boolean flag9 = false;
				for(int j3 = 0; j3 < playerCount; j3++)
				{
					Player class30_sub2_sub4_sub1_sub2_7 = playerArray[playerIndices[j3]];
					if(class30_sub2_sub4_sub1_sub2_7 == null || class30_sub2_sub4_sub1_sub2_7.name == null || !class30_sub2_sub4_sub1_sub2_7.name.equalsIgnoreCase(s7))
						continue;
					doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_7.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_7.smallX[0]);
					if(l == 484)
					{
						stream.putPacketID(139);
						stream.method431(playerIndices[j3]);
					}
					if(l == 6)
					{
						anInt1188 += i1;
						if(anInt1188 >= 90)
						{
							stream.putPacketID(136);
							anInt1188 = 0;
						}
						stream.putPacketID(128);
						stream.putShort(playerIndices[j3]);
					}
					flag9 = true;
					break;
				}

				if(!flag9)
					pushMessage("Unable to find " + s7, 0, "");
			}
		}
		if(l == 870)
		{
			stream.putPacketID(53);
			stream.putShort(j);
			stream.method432(anInt1283);
			stream.method433(i1);
			stream.putShort(anInt1284);
			stream.method431(anInt1285);
			stream.putShort(k);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 847)
		{
			stream.putPacketID(87);
			stream.method432(i1);
			stream.putShort(k);
			stream.method432(j);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 626)
		{
			RSInterface class9_1 = RSInterface.interfaceCache[k];
			spellSelected = 1;
			anInt1137 = k;
			spellUsableOn = class9_1.spellUsableOn;
			itemSelected = 0;
			needDrawTabArea = true;
			String s4 = class9_1.selectedActionName;
			if(s4.indexOf(" ") != -1)
				s4 = s4.substring(0, s4.indexOf(" "));
			String s8 = class9_1.selectedActionName;
			if(s8.indexOf(" ") != -1)
				s8 = s8.substring(s8.indexOf(" ") + 1);
			spellTooltip = s4 + " " + class9_1.spellName + " " + s8;
			if(spellUsableOn == 16)
			{
				needDrawTabArea = true;
				tabID = 3;
				tabAreaAltered = true;
			}
			return;
		}
		if(l == 78)
		{
			stream.putPacketID(117);
			stream.method433(k);
			stream.method433(i1);
			stream.method431(j);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 27)
		{
			Player class30_sub2_sub4_sub1_sub2_2 = playerArray[i1];
			if(class30_sub2_sub4_sub1_sub2_2 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_2.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_2.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				anInt986 += i1;
				if(anInt986 >= 54)
				{
					stream.putPacketID(189);
					stream.putLEShort(234);
					anInt986 = 0;
				}
				stream.putPacketID(73);
				stream.method431(i1);
			}
		}
		if(l == 213)
		{
			boolean flag3 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
			if(!flag3)
				flag3 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.putPacketID(79);
			stream.method431(k + baseY);
			stream.putShort(i1);
			stream.method432(j + baseX);
		}
		if(l == 632)
		{
			stream.putPacketID(145);
			stream.method432(k);
			stream.method432(j);
			stream.method432(i1);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 493)
		{
			stream.putPacketID(75);
			stream.method433(k);
			stream.method431(j);
			stream.method432(i1);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 652)
		{
			boolean flag4 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
			if(!flag4)
				flag4 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.putPacketID(156);
			stream.method432(j + baseX);
			stream.method431(k + baseY);
			stream.method433(i1);
		}
		if(l == 94)
		{
			boolean flag5 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
			if(!flag5)
				flag5 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.putPacketID(181);
			stream.method431(k + baseY);
			stream.putShort(i1);
			stream.method431(j + baseX);
			stream.method432(anInt1137);
		}
		if(l == 646)
		{
			stream.putPacketID(185);
			stream.putShort(k);
			RSInterface class9_2 = RSInterface.interfaceCache[k];
			if(class9_2.valueIndexArray != null && class9_2.valueIndexArray[0][0] == 5)
			{
				int i2 = class9_2.valueIndexArray[0][1];
				if(variousSettings[i2] != class9_2.anIntArray212[0])
				{
					variousSettings[i2] = class9_2.anIntArray212[0];
					adjustVolume(i2);
					needDrawTabArea = true;
				}
			}
		}
		if(l == 225)
		{
			NPC class30_sub2_sub4_sub1_sub1_2 = sessionNPCs[i1];
			if(class30_sub2_sub4_sub1_sub1_2 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_2.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_2.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				anInt1226 += i1;
				if(anInt1226 >= 85)
				{
					stream.putPacketID(230);
					stream.putLEShort(239);
					anInt1226 = 0;
				}
				stream.putPacketID(17);
				stream.method433(i1);
			}
		}
		if(l == 965)
		{
			NPC class30_sub2_sub4_sub1_sub1_3 = sessionNPCs[i1];
			if(class30_sub2_sub4_sub1_sub1_3 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_3.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_3.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				anInt1134++;
				if(anInt1134 >= 96)
				{
					stream.putPacketID(152);
					stream.putLEShort(88);
					anInt1134 = 0;
				}
				stream.putPacketID(21);
				stream.putShort(i1);
			}
		}
		if(l == 413)
		{
			NPC class30_sub2_sub4_sub1_sub1_4 = sessionNPCs[i1];
			if(class30_sub2_sub4_sub1_sub1_4 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_4.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_4.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.putPacketID(131);
				stream.method433(i1);
				stream.method432(anInt1137);
			}
		}
		if(l == 200)
			clearTopInterfaces();
		if(l == 1025)
		{
			NPC class30_sub2_sub4_sub1_sub1_5 = sessionNPCs[i1];
			if(class30_sub2_sub4_sub1_sub1_5 != null)
			{
				NPCDef entityDef = class30_sub2_sub4_sub1_sub1_5.desc;
				if(entityDef.childrenIDs != null)
					entityDef = entityDef.method161();
				if(entityDef != null)
				{
					String s9;
					if(entityDef.description != null)
						s9 = new String(entityDef.description);
					else
						s9 = "It's a " + entityDef.name + ".";
					pushMessage(s9, 0, "");
				}
			}
		}
		if(l == 900)
		{
			method66(i1, k, j);
			stream.putPacketID(252);
			stream.method433(i1 >> 14 & 0x7fff);
			stream.method431(k + baseY);
			stream.method432(j + baseX);
		}
		if(l == 412)
		{
			NPC class30_sub2_sub4_sub1_sub1_6 = sessionNPCs[i1];
			if(class30_sub2_sub4_sub1_sub1_6 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_6.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_6.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.putPacketID(72);
				stream.method432(i1);
			}
		}
		if(l == 365)
		{
			Player class30_sub2_sub4_sub1_sub2_3 = playerArray[i1];
			if(class30_sub2_sub4_sub1_sub2_3 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_3.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_3.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.putPacketID(249);
				stream.method432(i1);
				stream.method431(anInt1137);
			}
		}
		if(l == 729)
		{
			Player class30_sub2_sub4_sub1_sub2_4 = playerArray[i1];
			if(class30_sub2_sub4_sub1_sub2_4 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_4.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_4.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.putPacketID(39);
				stream.method431(i1);
			}
		}
		if(l == 577)
		{
			Player class30_sub2_sub4_sub1_sub2_5 = playerArray[i1];
			if(class30_sub2_sub4_sub1_sub2_5 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_5.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_5.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.putPacketID(139);
				stream.method431(i1);
			}
		}
		if(l == 956 && method66(i1, k, j))
		{
			stream.putPacketID(35);
			stream.method431(j + baseX);
			stream.method432(anInt1137);
			stream.method432(k + baseY);
			stream.method431(i1 >> 14 & 0x7fff);
		}
		if(l == 567)
		{
			boolean flag6 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
			if(!flag6)
				flag6 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.putPacketID(23);
			stream.method431(k + baseY);
			stream.method431(i1);
			stream.method431(j + baseX);
		}
		if(l == 867)
		{
			if((i1 & 3) == 0)
				anInt1175++;
			if(anInt1175 >= 59)
			{
				stream.putPacketID(200);
				stream.putShort(25501);
				anInt1175 = 0;
			}
			stream.putPacketID(43);
			stream.method431(k);
			stream.method432(i1);
			stream.method432(j);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 543)
		{
			stream.putPacketID(237);
			stream.putShort(j);
			stream.method432(i1);
			stream.putShort(k);
			stream.method432(anInt1137);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 606)
		{
			String s2 = menuActionName[i];
			int j2 = s2.indexOf("@whi@");
			if(j2 != -1)
				if(openInterfaceID == -1)
				{
					clearTopInterfaces();
					reportAbuseInput = s2.substring(j2 + 5).trim();
					canMute = false;
					for(int i3 = 0; i3 < RSInterface.interfaceCache.length; i3++)
					{
						if(RSInterface.interfaceCache[i3] == null || RSInterface.interfaceCache[i3].anInt214 != 600)
							continue;
						reportAbuseInterfaceID = openInterfaceID = RSInterface.interfaceCache[i3].parentID;
						break;
					}

				} else
				{
					pushMessage("Please close the interface you have open before using 'report abuse'", 0, "");
				}
		}
		if(l == 491)
		{
			Player class30_sub2_sub4_sub1_sub2_6 = playerArray[i1];
			if(class30_sub2_sub4_sub1_sub2_6 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_6.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_6.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.putPacketID(14);
				stream.method432(anInt1284);
				stream.putShort(i1);
				stream.putShort(anInt1285);
				stream.method431(anInt1283);
			}
		}
		if(l == 639)
		{
			String s3 = menuActionName[i];
			int k2 = s3.indexOf("@whi@");
			if(k2 != -1)
			{
				long l4 = TextClass.longForName(s3.substring(k2 + 5).trim());
				int k3 = -1;
				for(int i4 = 0; i4 < friendsCount; i4++)
				{
					if(friendsListAsLongs[i4] != l4)
						continue;
					k3 = i4;
					break;
				}

				if(k3 != -1 && friendsNodeIDs[k3] > 0)
				{
					inputTaken = true;
					inputDialogState = 0;
					messagePromptRaised = true;
					promptInput = "";
					friendsListAction = 3;
					aLong953 = friendsListAsLongs[k3];
					aString1121 = "Enter message to send to " + friendsList[k3];
				}
			}
		}
		if(l == 454)
		{
			stream.putPacketID(41);
			stream.putShort(i1);
			stream.method432(j);
			stream.method432(k);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 478)
		{
			NPC class30_sub2_sub4_sub1_sub1_7 = sessionNPCs[i1];
			if(class30_sub2_sub4_sub1_sub1_7 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_7.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_7.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				if((i1 & 3) == 0)
					anInt1155++;
				if(anInt1155 >= 53)
				{
					stream.putPacketID(85);
					stream.putLEShort(66);
					anInt1155 = 0;
				}
				stream.putPacketID(18);
				stream.method431(i1);
			}
		}
		if(l == 113)
		{
			method66(i1, k, j);
			stream.putPacketID(70);
			stream.method431(j + baseX);
			stream.putShort(k + baseY);
			stream.method433(i1 >> 14 & 0x7fff);
		}
		if(l == 872)
		{
			method66(i1, k, j);
			stream.putPacketID(234);
			stream.method433(j + baseX);
			stream.method432(i1 >> 14 & 0x7fff);
			stream.method433(k + baseY);
		}
		if(l == 502)
		{
			method66(i1, k, j);
			stream.putPacketID(132);
			stream.method433(j + baseX);
			stream.putShort(i1 >> 14 & 0x7fff);
			stream.method432(k + baseY);
		}
		if(l == 1125)
		{
			ItemDef itemDef = ItemDef.forID(i1);
			RSInterface class9_4 = RSInterface.interfaceCache[k];
			String s5;
			if(class9_4 != null && class9_4.invStackSizes[j] >= 0x186a0)
				s5 = class9_4.invStackSizes[j] + " x " + itemDef.name;
			else
			if(itemDef.description != null)
				s5 = new String(itemDef.description);
			else
				s5 = "It's a " + itemDef.name + ".";
			pushMessage(s5, 0, "");
		}
		if(l == 169)
		{
			stream.putPacketID(185);
			stream.putShort(k);
			RSInterface class9_3 = RSInterface.interfaceCache[k];
			if(class9_3.valueIndexArray != null && class9_3.valueIndexArray[0][0] == 5)
			{
				int l2 = class9_3.valueIndexArray[0][1];
				variousSettings[l2] = 1 - variousSettings[l2];
				adjustVolume(l2);
				needDrawTabArea = true;
			}
		}
		if(l == 447)
		{
			itemSelected = 1;
			anInt1283 = j;
			anInt1284 = k;
			anInt1285 = i1;
			selectedItemName = ItemDef.forID(i1).name;
			spellSelected = 0;
			needDrawTabArea = true;
			return;
		}
		if(l == 1226)
		{
			int j1 = i1 >> 14 & 0x7fff;
			ObjectDef class46 = ObjectDef.forID(j1);
			String s10;
			if(class46.description != null)
				s10 = new String(class46.description);
			else
				s10 = "It's a " + class46.name + ".";
			pushMessage(s10, 0, "");
		}
		if(l == 244)
		{
			boolean flag7 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
			if(!flag7)
				flag7 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.putPacketID(253);
			stream.method431(j + baseX);
			stream.method433(k + baseY);
			stream.method432(i1);
		}
		if(l == 1448)
		{
			ItemDef itemDef_1 = ItemDef.forID(i1);
			String s6;
			if(itemDef_1.description != null)
				s6 = new String(itemDef_1.description);
			else
				s6 = "It's a " + itemDef_1.name + ".";
			pushMessage(s6, 0, "");
		}
		itemSelected = 0;
			spellSelected = 0;
			needDrawTabArea = true;

	}
	
	
	
	
	
	
	private void processRightClick()
	{
		if(activeInterfaceType != 0)
			return;
		menuActionName[0] = "Cancel";
		menuActionID[0] = 1107;
		menuActionRow = 1;
		buildSplitPrivateChatMenu();
		anInt886 = 0;
		if(super.mouseX > 4 && super.mouseY > 4 && super.mouseX < 516 && super.mouseY < 338)
			if(openInterfaceID != -1)
				buildInterfaceMenu(4, RSInterface.interfaceCache[openInterfaceID], super.mouseX, 4, super.mouseY, 0);
			else
				build3dScreenMenu();
		if(anInt886 != anInt1026)
			anInt1026 = anInt886;
		anInt886 = 0;
		if(super.mouseX > 553 && super.mouseY > 205 && super.mouseX < 743 && super.mouseY < 466)
			if(invOverlayInterfaceID != -1)
				buildInterfaceMenu(553, RSInterface.interfaceCache[invOverlayInterfaceID], super.mouseX, 205, super.mouseY, 0);
			else
			if(tabInterfaceIDs[tabID] != -1)
				buildInterfaceMenu(553, RSInterface.interfaceCache[tabInterfaceIDs[tabID]], super.mouseX, 205, super.mouseY, 0);
		if(anInt886 != anInt1048)
		{
			needDrawTabArea = true;
			anInt1048 = anInt886;
		}
		anInt886 = 0;
		if(super.mouseX > 17 && super.mouseY > 357 && super.mouseX < 496 && super.mouseY < 453)
			if(backDialogID != -1)
				buildInterfaceMenu(17, RSInterface.interfaceCache[backDialogID], super.mouseX, 357, super.mouseY, 0);
			else
			if(super.mouseY < 434 && super.mouseX < 426)
				buildChatAreaMenu(super.mouseY - 357);
		if(backDialogID != -1 && anInt886 != anInt1039)
		{
			inputTaken = true;
			anInt1039 = anInt886;
		}
		boolean flag = false;
		while(!flag) 
		{
			flag = true;
			for(int j = 0; j < menuActionRow - 1; j++)
				if(menuActionID[j] < 1000 && menuActionID[j + 1] > 1000)
				{
					String s = menuActionName[j];
					menuActionName[j] = menuActionName[j + 1];
					menuActionName[j + 1] = s;
					int k = menuActionID[j];
					menuActionID[j] = menuActionID[j + 1];
					menuActionID[j + 1] = k;
					k = menuActionCmd2[j];
					menuActionCmd2[j] = menuActionCmd2[j + 1];
					menuActionCmd2[j + 1] = k;
					k = menuActionCmd3[j];
					menuActionCmd3[j] = menuActionCmd3[j + 1];
					menuActionCmd3[j + 1] = k;
					k = menuActionCmd1[j];
					menuActionCmd1[j] = menuActionCmd1[j + 1];
					menuActionCmd1[j + 1] = k;
					flag = false;
				}

		}
	}



	
	
	
	private void buildAtPlayerMenu(int i, int j, Player player, int k)
	{
		if(player == myPlayer)
			return;
		if(menuActionRow >= 400)
			return;
		String s;
		if(player.skill == 0)
			s = player.name + combatDiffColor(myPlayer.combatLevel, player.combatLevel) + " (level-" + player.combatLevel + ")";
		else
			s = player.name + " (skill-" + player.skill + ")";
		if(itemSelected == 1)
		{
			menuActionName[menuActionRow] = "Use " + selectedItemName + " with @whi@" + s;
			menuActionID[menuActionRow] = 491;
			menuActionCmd1[menuActionRow] = j;
			menuActionCmd2[menuActionRow] = i;
			menuActionCmd3[menuActionRow] = k;
			menuActionRow++;
		} else
		if(spellSelected == 1)
		{
			if((spellUsableOn & 8) == 8)
			{
				menuActionName[menuActionRow] = spellTooltip + " @whi@" + s;
				menuActionID[menuActionRow] = 365;
				menuActionCmd1[menuActionRow] = j;
				menuActionCmd2[menuActionRow] = i;
				menuActionCmd3[menuActionRow] = k;
				menuActionRow++;
			}
		} else
		{
			for(int l = 4; l >= 0; l--)
				if(atPlayerActions[l] != null)
				{
					menuActionName[menuActionRow] = atPlayerActions[l] + " @whi@" + s;
					char c = '\0';
					if(atPlayerActions[l].equalsIgnoreCase("attack"))
					{
						if(player.combatLevel > myPlayer.combatLevel)
							c = '\u07D0';
						if(myPlayer.team != 0 && player.team != 0)
							if(myPlayer.team == player.team)
								c = '\u07D0';
							else
								c = '\0';
					} else
					if(atPlayerArray[l])
						c = '\u07D0';
					if(l == 0)
						menuActionID[menuActionRow] = 561 + c;
					if(l == 1)
						menuActionID[menuActionRow] = 779 + c;
					if(l == 2)
						menuActionID[menuActionRow] = 27 + c;
					if(l == 3)
						menuActionID[menuActionRow] = 577 + c;
					if(l == 4)
						menuActionID[menuActionRow] = 729 + c;
					menuActionCmd1[menuActionRow] = j;
					menuActionCmd2[menuActionRow] = i;
					menuActionCmd3[menuActionRow] = k;
					menuActionRow++;
				}

		}
		for(int i1 = 0; i1 < menuActionRow; i1++)
			if(menuActionID[i1] == 516)
			{
				menuActionName[i1] = "Walk here @whi@" + s;
				return;
			}

	}
	
	
	
	
	
	
	
	private void buildChatAreaMenu(int j)
	{
		int l = 0;
		for(int i1 = 0; i1 < 100; i1++)
		{
			if(chatMessages[i1] == null)
				continue;
			int j1 = chatTypes[i1];
			int k1 = (70 - l * 14) + anInt1089 + 4;
			if(k1 < -20)
				break;
			String s = chatNames[i1];
			if(s != null && s.startsWith("@cr1@"))
			{
				s = s.substring(5);
			}
			if(s != null && s.startsWith("@cr2@"))
			{
				s = s.substring(5);
			}
			if(j1 == 0)
				l++;
			if((j1 == 1 || j1 == 2) && (j1 == 1 || publicChatMode == 0 || publicChatMode == 1 && isFriendOrSelf(s)))
			{
				if(j > k1 - 14 && j <= k1 && !s.equals(myPlayer.name))
				{
					if(myPrivilege >= 1)
					{
						menuActionName[menuActionRow] = "Report abuse @whi@" + s;
						menuActionID[menuActionRow] = 606;
						menuActionRow++;
					}
					menuActionName[menuActionRow] = "Add ignore @whi@" + s;
					menuActionID[menuActionRow] = 42;
					menuActionRow++;
					menuActionName[menuActionRow] = "Add friend @whi@" + s;
					menuActionID[menuActionRow] = 337;
					menuActionRow++;
				}
				l++;
			}
			if((j1 == 3 || j1 == 7) && splitPrivateChat == 0 && (j1 == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s)))
			{
				if(j > k1 - 14 && j <= k1)
				{
					if(myPrivilege >= 1)
					{
						menuActionName[menuActionRow] = "Report abuse @whi@" + s;
						menuActionID[menuActionRow] = 606;
						menuActionRow++;
					}
					menuActionName[menuActionRow] = "Add ignore @whi@" + s;
					menuActionID[menuActionRow] = 42;
					menuActionRow++;
					menuActionName[menuActionRow] = "Add friend @whi@" + s;
					menuActionID[menuActionRow] = 337;
					menuActionRow++;
				}
				l++;
			}
			if(j1 == 4 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s)))
			{
				if(j > k1 - 14 && j <= k1)
				{
					menuActionName[menuActionRow] = "Accept trade @whi@" + s;
					menuActionID[menuActionRow] = 484;
					menuActionRow++;
				}
				l++;
			}
			if((j1 == 5 || j1 == 6) && splitPrivateChat == 0 && privateChatMode < 2)
				l++;
			if(j1 == 8 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s)))
			{
				if(j > k1 - 14 && j <= k1)
				{
					menuActionName[menuActionRow] = "Accept challenge @whi@" + s;
					menuActionID[menuActionRow] = 6;
					menuActionRow++;
				}
				l++;
			}
		}

	}
	
	
	
	
	
	
	private void determineMenuSize()
	{
		int i = chatTextDrawingArea.getTextWidth("Choose Option");
		for(int j = 0; j < menuActionRow; j++)
		{
			int k = chatTextDrawingArea.getTextWidth(menuActionName[j]);
			if(k > i)
				i = k;
		}

		i += 8;
		int l = 15 * menuActionRow + 21;
		if(super.saveClickX > 4 && super.saveClickY > 4 && super.saveClickX < 516 && super.saveClickY < 338)
		{
			int i1 = super.saveClickX - 4 - i / 2;
			if(i1 + i > 512)
				i1 = 512 - i;
			if(i1 < 0)
				i1 = 0;
			int l1 = super.saveClickY - 4;
			if(l1 + l > 334)
				l1 = 334 - l;
			if(l1 < 0)
				l1 = 0;
			menuOpen = true;
			menuScreenArea = 0;
			menuOffsetX = i1;
			menuOffsetY = l1;
			menuWidth = i;
			anInt952 = 15 * menuActionRow + 22;
		}
		if(super.saveClickX > 553 && super.saveClickY > 205 && super.saveClickX < 743 && super.saveClickY < 466)
		{
			int j1 = super.saveClickX - 553 - i / 2;
			if(j1 < 0)
				j1 = 0;
			else
			if(j1 + i > 190)
				j1 = 190 - i;
			int i2 = super.saveClickY - 205;
			if(i2 < 0)
				i2 = 0;
			else
			if(i2 + l > 261)
				i2 = 261 - l;
			menuOpen = true;
			menuScreenArea = 1;
			menuOffsetX = j1;
			menuOffsetY = i2;
			menuWidth = i;
			anInt952 = 15 * menuActionRow + 22;
		}
		if(super.saveClickX > 17 && super.saveClickY > 357 && super.saveClickX < 496 && super.saveClickY < 453)
		{
			int k1 = super.saveClickX - 17 - i / 2;
			if(k1 < 0)
				k1 = 0;
			else
			if(k1 + i > 479)
				k1 = 479 - i;
			int j2 = super.saveClickY - 357;
			if(j2 < 0)
				j2 = 0;
			else
			if(j2 + l > 96)
				j2 = 96 - l;
			menuOpen = true;
			menuScreenArea = 2;
			menuOffsetX = k1;
			menuOffsetY = j2;
			menuWidth = i;
			anInt952 = 15 * menuActionRow + 22;
		}
	}



		private boolean buildFriendsListMenu(RSInterface class9)
	{
		int i = class9.anInt214;
		if(i >= 1 && i <= 200 || i >= 701 && i <= 900)
		{
			if(i >= 801)
				i -= 701;
			else
			if(i >= 701)
				i -= 601;
			else
			if(i >= 101)
				i -= 101;
			else
				i--;
			menuActionName[menuActionRow] = "Remove @whi@" + friendsList[i];
			menuActionID[menuActionRow] = 792;
			menuActionRow++;
			menuActionName[menuActionRow] = "Message @whi@" + friendsList[i];
			menuActionID[menuActionRow] = 639;
			menuActionRow++;
			return true;
		}
		if(i >= 401 && i <= 500)
		{
			menuActionName[menuActionRow] = "Remove @whi@" + class9.message;
			menuActionID[menuActionRow] = 322;
			menuActionRow++;
			return true;
		} else
		{
			return false;
		}
	}
	
	
	
	
	private void buildSplitPrivateChatMenu()
	{
		if(splitPrivateChat == 0)
			return;
		int i = 0;
		if(anInt1104 != 0)
			i = 1;
		for(int j = 0; j < 100; j++)
			if(chatMessages[j] != null)
			{
				int k = chatTypes[j];
				String s = chatNames[j];
				if(s != null && s.startsWith("@cr1@"))
				{
					s = s.substring(5);
				}
				if(s != null && s.startsWith("@cr2@"))
				{
					s = s.substring(5);
				}
				if((k == 3 || k == 7) && (k == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s)))
				{
					int l = 329 - i * 13;
					if(super.mouseX > 4 && super.mouseY - 4 > l - 10 && super.mouseY - 4 <= l + 3)
					{
						int i1 = aTextDrawingArea_1271.getTextWidth("From:  " + s + chatMessages[j]) + 25;
						if(i1 > 450)
							i1 = 450;
						if(super.mouseX < 4 + i1)
						{
							if(myPrivilege >= 1)
							{
								menuActionName[menuActionRow] = "Report abuse @whi@" + s;
								menuActionID[menuActionRow] = 2606;
								menuActionRow++;
							}
							menuActionName[menuActionRow] = "Add ignore @whi@" + s;
							menuActionID[menuActionRow] = 2042;
							menuActionRow++;
							menuActionName[menuActionRow] = "Add friend @whi@" + s;
							menuActionID[menuActionRow] = 2337;
							menuActionRow++;
						}
					}
					if(++i >= 5)
						return;
				}
				if((k == 5 || k == 6) && privateChatMode < 2 && ++i >= 5)
					return;
			}
	}
}