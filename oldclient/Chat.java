package com.runescape.client.revised.client;

import com.runescape.client.revised.config.definitions.IdentityKit;
import com.runescape.client.revised.graphics.image.DrawingArea;
import com.runescape.client.revised.media.IndexedImage;
import com.runescape.client.revised.util.Censor;
import com.runescape.client.revised.util.TextClass;

public class Chat {

	public final int[] chatTypes;
	public final String[] chatNames;
	public final String[] chatMessages;
	public final int[] chatTextColors = { // anIntArray965
			0xffff00, 0xff0000, 65280, 65535, 0xff00ff, 0xffffff
	};
	public String chatboxInputneededString; // aString1121
	public int[] chatLineOffsets; // anIntArray1180
	public int splitPrivateChat;
	public IndexedImage chatBack;
	public boolean chatOptionsNeedUpdating; // aBoolean1233
	public int chatEffectsEnabled; // anInt1249
	public int publicChatMode;
	public int privateChatMode;
	public String clickToContinueString; //aString844
	public String amountOrNameInput;

	public Chat() {
		this.chatTypes = new int[100];
		this.chatNames = new String[100];
		this.chatMessages = new String[100];
		this.chatboxInputneededString = "";
		this.chatOptionsNeedUpdating = false;
		this.amountOrNameInput = "";
	}

	private void drawChatArea(final GameClient client)
	{
		client.aRSImageProducer_1166.initDrawingArea();
		Rasterizer.anIntArray1472 = client.chatLineOffsets;
		client.chatBack.method361(0, 0);
		if(client.messagePromptRaised)
		{
			client.boldFont.drawText(0, client.chatboxInputneededString, 40, 239);
			client.boldFont.drawText(128, client.promptInput + "*", 60, 239);
		} else
			if(client.inputDialogState == 1)
			{
				client.boldFont.drawText(0, "Enter amount:", 40, 239);
				client.boldFont.drawText(128, client.amountOrNameInput + "*", 60, 239);
			} else
				if(client.inputDialogState == 2)
				{
					client.boldFont.drawText(0, "Enter name:", 40, 239);
					client.boldFont.drawText(128, client.amountOrNameInput + "*", 60, 239);
				} else
					if(client.clickToContinueString != null)
					{
						client.boldFont.drawText(0, client.clickToContinueString, 40, 239);
						client.boldFont.drawText(128, "Click to continue", 60, 239);
					} else
						if(client.backDialogID != -1) {
							drawInterface(0, 0, RSInterface.interfaceCache[client.backDialogID], 0);
						} else
							if(client.dialogID != -1)
							{
								drawInterface(0, 0, RSInterface.interfaceCache[client.dialogID], 0);
							} else
							{
								final TextDrawingArea textDrawingArea = aTextDrawingArea_1271;
								int j = 0;
								DrawingArea.setDrawingArea(77, 0, 463, 0);
								for(int k = 0; k < 100; k++) {
									if(client.chatMessages[k] != null)
									{
										final int l = client.chatTypes[k];
										final int i1 = (70 - (j * 14)) + anInt1089;
										String s1 = client.chatNames[k];
										byte byte0 = 0;
										if((s1 != null) && s1.startsWith("@cr1@"))
										{
											s1 = s1.substring(5);
											byte0 = 1;
										}
										if((s1 != null) && s1.startsWith("@cr2@"))
										{
											s1 = s1.substring(5);
											byte0 = 2;
										}
										if(l == 0)
										{
											if((i1 > 0) && (i1 < 110)) {
												textDrawingArea.method385(0, client.chatMessages[k], i1, 4);
											}
											j++;
										}
										if(((l == 1) || (l == 2)) && ((l == 1) || (this.publicChatMode == 0) || ((this.publicChatMode == 1) && isFriendOrSelf(s1))))
										{
											if((i1 > 0) && (i1 < 110))
											{
												int j1 = 4;
												if(byte0 == 1)
												{
													client.modIcons[0].method361(j1, i1 - 12);
													j1 += 14;
												}
												if(byte0 == 2)
												{
													client.modIcons[1].method361(j1, i1 - 12);
													j1 += 14;
												}
												textDrawingArea.method385(0, s1 + ":", i1, j1);
												j1 += textDrawingArea.getTextWidth(s1) + 8;
												textDrawingArea.method385(255, client.chatMessages[k], i1, j1);
											}
											j++;
										}
										if(((l == 3) || (l == 7)) && (client.splitPrivateChat == 0) && ((l == 7) || (this.privateChatMode == 0) || ((this.privateChatMode == 1) && isFriendOrSelf(s1))))
										{
											if((i1 > 0) && (i1 < 110))
											{
												int k1 = 4;
												textDrawingArea.method385(0, "From", i1, k1);
												k1 += textDrawingArea.getTextWidth("From ");
												if(byte0 == 1)
												{
													client.modIcons[0].method361(k1, i1 - 12);
													k1 += 14;
												}
												if(byte0 == 2)
												{
													client.modIcons[1].method361(k1, i1 - 12);
													k1 += 14;
												}
												textDrawingArea.method385(0, s1 + ":", i1, k1);
												k1 += textDrawingArea.getTextWidth(s1) + 8;
												textDrawingArea.method385(0x800000, client.chatMessages[k], i1, k1);
											}
											j++;
										}
										if((l == 4) && ((client.tradeMode == 0) || ((client.tradeMode == 1) && isFriendOrSelf(s1))))
										{
											if((i1 > 0) && (i1 < 110)) {
												textDrawingArea.method385(0x800080, s1 + " " + this.chatMessages[k], i1, 4);
											}
											j++;
										}
										if((l == 5) && (client.splitPrivateChat == 0) && (client.privateChatMode < 2))
										{
											if((i1 > 0) && (i1 < 110)) {
												textDrawingArea.method385(0x800000, client.chatMessages[k], i1, 4);
											}
											j++;
										}
										if((l == 6) && (client.splitPrivateChat == 0) && (client.privateChatMode < 2))
										{
											if((i1 > 0) && (i1 < 110))
											{
												textDrawingArea.method385(0, "To " + s1 + ":", i1, 4);
												textDrawingArea.method385(0x800000, client.chatMessages[k], i1, 12 + textDrawingArea.getTextWidth("To " + s1));
											}
											j++;
										}
										if((l == 8) && ((client.tradeMode == 0) || ((client.tradeMode == 1) && isFriendOrSelf(s1))))
										{
											if((i1 > 0) && (i1 < 110)) {
												textDrawingArea.method385(0x7e3200, s1 + " " + client.chatMessages[k], i1, 4);
											}
											j++;
										}
									}
								}

								DrawingArea.defaultDrawingAreaSize();
								anInt1211 = (j * 14) + 7;
								if(anInt1211 < 78) {
									anInt1211 = 78;
								}
								this.manageChatInterface(77, anInt1211 - anInt1089 - 77, 0, 463, anInt1211);
								String s;
								if((GameClient.myPlayer != null) && (GameClient.myPlayer.name != null)) {
									s = GameClient.myPlayer.name;
								} else {
									s = TextClass.fixName(client.myUsername);
								}
								textDrawingArea.method385(0, s + ":", 90, 4);
								textDrawingArea.method385(255, client.inputString + "*", 90, 6 + textDrawingArea.getTextWidth(s + ": "));
								DrawingArea.method339(77, 0, 479, 0);
							}
		if(client.menuOpen && (client.menuScreenArea == 2)) {
			drawMenu();
		}
		client.aRSImageProducer_1166.drawGraphics(357, super.graphics, 17);
		aRSImageProducer_1165.initDrawingArea();
		Rasterizer.anIntArray1472 = anIntArray1182;
	}

	private boolean promptUserForInput(final RSInterface class9)
	{
		final int j = class9.anInt214;
		if(anInt900 == 2)
		{
			if(j == 201)
			{
				inputTaken = true;
				inputDialogState = 0;
				messagePromptRaised = true;
				promptInput = "";
				friendsListAction = 1;
				aString1121 = "Enter name of friend to add to list";
			}
			if(j == 202)
			{
				inputTaken = true;
				inputDialogState = 0;
				messagePromptRaised = true;
				promptInput = "";
				friendsListAction = 2;
				aString1121 = "Enter name of friend to delete from list";
			}
		}
		if(j == 205)
		{
			anInt1011 = 250;
			return true;
		}
		if(j == 501)
		{
			inputTaken = true;
			inputDialogState = 0;
			messagePromptRaised = true;
			promptInput = "";
			friendsListAction = 4;
			aString1121 = "Enter name of player to add to list";
		}
		if(j == 502)
		{
			inputTaken = true;
			inputDialogState = 0;
			messagePromptRaised = true;
			promptInput = "";
			friendsListAction = 5;
			aString1121 = "Enter name of player to delete from list";
		}
		if((j >= 300) && (j <= 313))
		{
			final int k = (j - 300) / 2;
			final int j1 = j & 1;
			int i2 = anIntArray1065[k];
			if(i2 != -1)
			{
				do
				{
					if((j1 == 0) && (--i2 < 0)) {
						i2 = IdentityKit.length - 1;
					}
					if((j1 == 1) && (++i2 >= IdentityKit.length)) {
						i2 = 0;
					}
				} while(IdentityKit.cache[i2].notSelectable || (IdentityKit.cache[i2].bodyPartID != (k + (aBoolean1047 ? 0 : 7))));
				anIntArray1065[k] = i2;
				aBoolean1031 = true;
			}
		}
		if((j >= 314) && (j <= 323))
		{
			final int l = (j - 314) / 2;
			final int k1 = j & 1;
			int j2 = anIntArray990[l];
			if((k1 == 0) && (--j2 < 0)) {
				j2 = anIntArrayArray1003[l].length - 1;
			}
			if((k1 == 1) && (++j2 >= anIntArrayArray1003[l].length)) {
				j2 = 0;
			}
			anIntArray990[l] = j2;
			aBoolean1031 = true;
		}
		if((j == 324) && !aBoolean1047)
		{
			aBoolean1047 = true;
			method45();
		}
		if((j == 325) && aBoolean1047)
		{
			aBoolean1047 = false;
			method45();
		}
		if(j == 326)
		{
			stream.putPacketID(101);
			stream.putLEShort(aBoolean1047 ? 0 : 1);
			for(int i1 = 0; i1 < 7; i1++) {
				stream.putLEShort(anIntArray1065[i1]);
			}

			for(int l1 = 0; l1 < 5; l1++) {
				stream.putLEShort(anIntArray990[l1]);
			}

			return true;
		}
		if(j == 613) {
			canMute = !canMute;
		}
		if((j >= 601) && (j <= 612))
		{
			clearTopInterfaces();
			if(reportAbuseInput.length() > 0)
			{
				stream.putPacketID(218);
				stream.writeQWord(TextClass.longForName(reportAbuseInput));
				stream.putLEShort(j - 601);
				stream.putLEShort(canMute ? 1 : 0);
			}
		}
		return false;
	}






	private void managaTextInput() // 73
	{
		do
		{
			final int j = readChar(-796);
			if(j == -1) {
				break;
			}
			if((openInterfaceID != -1) && (openInterfaceID == reportAbuseInterfaceID))
			{
				if((j == 8) && (reportAbuseInput.length() > 0)) {
					reportAbuseInput = reportAbuseInput.substring(0, reportAbuseInput.length() - 1);
				}
				if((((j >= 97) && (j <= 122)) || ((j >= 65) && (j <= 90)) || ((j >= 48) && (j <= 57)) || (j == 32)) && (reportAbuseInput.length() < 12)) {
					reportAbuseInput += (char)j;
				}
			} else
				if(messagePromptRaised)
				{
					if((j >= 32) && (j <= 122) && (promptInput.length() < 80))
					{
						promptInput += (char)j;
						inputTaken = true;
					}
					if((j == 8) && (promptInput.length() > 0))
					{
						promptInput = promptInput.substring(0, promptInput.length() - 1);
						inputTaken = true;
					}
					if((j == 13) || (j == 10))
					{
						messagePromptRaised = false;
						inputTaken = true;
						if(friendsListAction == 1)
						{
							final long l = TextClass.longForName(promptInput);
							addFriend(l);
						}
						if((friendsListAction == 2) && (friendsCount > 0))
						{
							final long l1 = TextClass.longForName(promptInput);
							delFriend(l1);
						}
						if((friendsListAction == 3) && (promptInput.length() > 0))
						{
							stream.putPacketID(126);
							stream.putLEShort(0);
							final int k = stream.currentOffset;
							stream.writeQWord(aLong953);
							TextInput.method526(promptInput, stream);
							stream.writeBytes(stream.currentOffset - k);
							promptInput = TextInput.processText(promptInput);
							promptInput = Censor.doCensor(promptInput);
							this.pushMessage(promptInput, 6, TextClass.fixName(TextClass.nameForLong(aLong953)));
							if(this.privateChatMode == 2)
							{
								this.privateChatMode = 1;
								aBoolean1233 = true;
								stream.putPacketID(95);
								stream.putLEShort(this.publicChatMode);
								stream.putLEShort(this.privateChatMode);
								stream.putLEShort(tradeMode);
							}
						}
						if((friendsListAction == 4) && (ignoreCount < 100))
						{
							final long l2 = TextClass.longForName(promptInput);
							addIgnore(l2);
						}
						if((friendsListAction == 5) && (ignoreCount > 0))
						{
							final long l3 = TextClass.longForName(promptInput);
							delIgnore(l3);
						}
					}
				} else
					if(inputDialogState == 1)
					{
						if((j >= 48) && (j <= 57) && (this.amountOrNameInput.length() < 10))
						{
							this.amountOrNameInput += (char)j;
							inputTaken = true;
						}
						if((j == 8) && (this.amountOrNameInput.length() > 0))
						{
							this.amountOrNameInput = this.amountOrNameInput.substring(0, this.amountOrNameInput.length() - 1);
							inputTaken = true;
						}
						if((j == 13) || (j == 10))
						{
							if(this.amountOrNameInput.length() > 0)
							{
								int i1 = 0;
								try
								{
									i1 = Integer.parseInt(this.amountOrNameInput);
								}
								catch(final Exception _ex) { }
								stream.putPacketID(208);
								stream.writeDWord(i1);
							}
							inputDialogState = 0;
							inputTaken = true;
						}
					} else
						if(inputDialogState == 2)
						{
							if((j >= 32) && (j <= 122) && (this.amountOrNameInput.length() < 12))
							{
								this.amountOrNameInput += (char)j;
								inputTaken = true;
							}
							if((j == 8) && (this.amountOrNameInput.length() > 0))
							{
								this.amountOrNameInput = this.amountOrNameInput.substring(0, this.amountOrNameInput.length() - 1);
								inputTaken = true;
							}
							if((j == 13) || (j == 10))
							{
								if(this.amountOrNameInput.length() > 0)
								{
									stream.putPacketID(60);
									stream.writeQWord(TextClass.longForName(this.amountOrNameInput));
								}
								inputDialogState = 0;
								inputTaken = true;
							}
						} else
							if(backDialogID == -1)
							{
								if((j >= 32) && (j <= 122) && (inputString.length() < 80))
								{
									inputString += (char)j;
									inputTaken = true;
								}
								if((j == 8) && (inputString.length() > 0))
								{
									inputString = inputString.substring(0, inputString.length() - 1);
									inputTaken = true;
								}
								if(((j == 13) || (j == 10)) && (inputString.length() > 0))
								{
									if(myPrivilege == 2)
									{
										if(inputString.equals("::clientdrop")) {
											dropClient();
										}
										if(inputString.equals("::lag")) {
											printDebug();
										}
										if(inputString.equals("::prefetchmusic"))
										{
											for(int j1 = 0; j1 < onDemandFetcher.getVersionCount(2); j1++) {
												onDemandFetcher.method563((byte)1, 2, j1);
											}

										}
										if(inputString.equals("::fpson")) {
											fpsOn = true;
										}
										if(inputString.equals("::fpsoff")) {
											fpsOn = false;
										}
										if(inputString.equals("::noclip"))
										{
											for(int k1 = 0; k1 < 4; k1++)
											{
												for(int i2 = 1; i2 < 103; i2++)
												{
													for(int k2 = 1; k2 < 103; k2++) {
														aClass11Array1230[k1].anIntArrayArray294[i2][k2] = 0;
													}

												}

											}

										}
									}
									if(inputString.startsWith("::"))
									{
										stream.putPacketID(103);
										stream.putLEShort(inputString.length() - 1);
										stream.writeString(inputString.substring(2));
									} else
									{
										String s = inputString.toLowerCase();
										int j2 = 0;
										if(s.startsWith("yellow:"))
										{
											j2 = 0;
											inputString = inputString.substring(7);
										} else
											if(s.startsWith("red:"))
											{
												j2 = 1;
												inputString = inputString.substring(4);
											} else
												if(s.startsWith("green:"))
												{
													j2 = 2;
													inputString = inputString.substring(6);
												} else
													if(s.startsWith("cyan:"))
													{
														j2 = 3;
														inputString = inputString.substring(5);
													} else
														if(s.startsWith("purple:"))
														{
															j2 = 4;
															inputString = inputString.substring(7);
														} else
															if(s.startsWith("white:"))
															{
																j2 = 5;
																inputString = inputString.substring(6);
															} else
																if(s.startsWith("flash1:"))
																{
																	j2 = 6;
																	inputString = inputString.substring(7);
																} else
																	if(s.startsWith("flash2:"))
																	{
																		j2 = 7;
																		inputString = inputString.substring(7);
																	} else
																		if(s.startsWith("flash3:"))
																		{
																			j2 = 8;
																			inputString = inputString.substring(7);
																		} else
																			if(s.startsWith("glow1:"))
																			{
																				j2 = 9;
																				inputString = inputString.substring(6);
																			} else
																				if(s.startsWith("glow2:"))
																				{
																					j2 = 10;
																					inputString = inputString.substring(6);
																				} else
																					if(s.startsWith("glow3:"))
																					{
																						j2 = 11;
																						inputString = inputString.substring(6);
																					}
										s = inputString.toLowerCase();
										int i3 = 0;
										if(s.startsWith("wave:"))
										{
											i3 = 1;
											inputString = inputString.substring(5);
										} else
											if(s.startsWith("wave2:"))
											{
												i3 = 2;
												inputString = inputString.substring(6);
											} else
												if(s.startsWith("shake:"))
												{
													i3 = 3;
													inputString = inputString.substring(6);
												} else
													if(s.startsWith("scroll:"))
													{
														i3 = 4;
														inputString = inputString.substring(7);
													} else
														if(s.startsWith("slide:"))
														{
															i3 = 5;
															inputString = inputString.substring(6);
														}
										stream.putPacketID(4);
										stream.putLEShort(0);
										final int j3 = stream.currentOffset;
										stream.method425(i3);
										stream.method425(j2);
										aStream_834.currentOffset = 0;
										TextInput.method526(inputString, aStream_834);
										stream.method441(0, aStream_834.buffer, aStream_834.currentOffset);
										stream.writeBytes(stream.currentOffset - j3);
										inputString = TextInput.processText(inputString);
										inputString = Censor.doCensor(inputString);
										myPlayer.textSpoken = inputString;
										myPlayer.anInt1513 = j2;
										myPlayer.anInt1531 = i3;
										myPlayer.textCycle = 150;
										if(myPrivilege == 2) {
											this.pushMessage(myPlayer.textSpoken, 2, "@cr2@" + myPlayer.name);
										} else
											if(myPrivilege == 1) {
												this.pushMessage(myPlayer.textSpoken, 2, "@cr1@" + myPlayer.name);
											} else {
												this.pushMessage(myPlayer.textSpoken, 2, myPlayer.name);
											}
										if(this.publicChatMode == 2)
										{
											this.publicChatMode = 3;
											aBoolean1233 = true;
											stream.putPacketID(95);
											stream.putLEShort(this.publicChatMode);
											stream.putLEShort(this.privateChatMode);
											stream.putLEShort(tradeMode);
										}
									}
									inputString = "";
									inputTaken = true;
								}
							}
		} while(true);
	}




	private void drawSplitPrivateChat()
	{
		if(this.splitPrivateChat == 0) {
			return;
		}
		final TextDrawingArea textDrawingArea = aTextDrawingArea_1271;
		int i = 0;
		if(anInt1104 != 0) {
			i = 1;
		}
		for(int j = 0; j < 100; j++) {
			if(this.chatMessages[j] != null)
			{
				final int k = this.chatTypes[j];
				String s = this.chatNames[j];
				byte byte1 = 0;
				if((s != null) && s.startsWith("@cr1@"))
				{
					s = s.substring(5);
					byte1 = 1;
				}
				if((s != null) && s.startsWith("@cr2@"))
				{
					s = s.substring(5);
					byte1 = 2;
				}
				if(((k == 3) || (k == 7)) && ((k == 7) || (this.privateChatMode == 0) || ((this.privateChatMode == 1) && isFriendOrSelf(s))))
				{
					final int l = 329 - (i * 13);
					int k1 = 4;
					textDrawingArea.method385(0, "From", l, k1);
					textDrawingArea.method385(65535, "From", l - 1, k1);
					k1 += textDrawingArea.getTextWidth("From ");
					if(byte1 == 1)
					{
						modIcons[0].method361(k1, l - 12);
						k1 += 14;
					}
					if(byte1 == 2)
					{
						modIcons[1].method361(k1, l - 12);
						k1 += 14;
					}
					textDrawingArea.method385(0, s + ": " + this.chatMessages[j], l, k1);
					textDrawingArea.method385(65535, s + ": " + this.chatMessages[j], l - 1, k1);
					if(++i >= 5) {
						return;
					}
				}
				if((k == 5) && (this.privateChatMode < 2))
				{
					final int i1 = 329 - (i * 13);
					textDrawingArea.method385(0, this.chatMessages[j], i1, 4);
					textDrawingArea.method385(65535, this.chatMessages[j], i1 - 1, 4);
					if(++i >= 5) {
						return;
					}
				}
				if((k == 6) && (this.privateChatMode < 2))
				{
					final int j1 = 329 - (i * 13);
					textDrawingArea.method385(0, "To " + s + ": " + this.chatMessages[j], j1, 4);
					textDrawingArea.method385(65535, "To " + s + ": " + this.chatMessages[j], j1 - 1, 4);
					if(++i >= 5) {
						return;
					}
				}
			}
		}

	}

	public void pushMessage(final GameClient client, final String s, final int i, final String s1)
	{
		if((i == 0) && (client.dialogID != -1))
		{
			client.clickToContinueString = s;
			client.clickMode3 = 0;
		}
		if(client.backDialogID == -1) {
			client.inputTaken = true;
		}
		for(int j = 99; j > 0; j--)
		{
			client.chatTypes[j] = client.chatTypes[j - 1];
			client.chatNames[j] = client.chatNames[j - 1];
			client.chatMessages[j] = client.chatMessages[j - 1];
		}

		client.chatTypes[0] = i;
		client.chatNames[0] = s1;
		client.chatMessages[0] = s;
	}


	private void manageChatInterface(final GameClient client, final int j, final int k, final int l, final int i1, final int j1)
	{
		client.scrollBar1.method361(i1, l);
		client.scrollBar2.method361(i1, (l + j) - 16);
		DrawingArea.method336(j - 32, l + 16, i1, client.anInt1002, 16);
		int k1 = ((j - 32) * j) / j1;
		if(k1 < 8) {
			k1 = 8;
		}
		final int l1 = ((j - 32 - k1) * k) / (j1 - j);
		DrawingArea.method336(k1, l + 16 + l1, i1, client.anInt1063, 16);
		DrawingArea.method341(l + 16 + l1, client.anInt902, k1, i1);
		DrawingArea.method341(l + 16 + l1, client.anInt902, k1, i1 + 1);
		DrawingArea.method339(l + 16 + l1, client.anInt902, 16, i1);
		DrawingArea.method339(l + 17 + l1, client.anInt902, 16, i1);
		DrawingArea.method341(l + 16 + l1, client.anInt927, k1, i1 + 15);
		DrawingArea.method341(l + 17 + l1, client.anInt927, k1 - 1, i1 + 14);
		DrawingArea.method339(l + 15 + l1 + k1, client.anInt927, 16, i1);
		DrawingArea.method339(l + 14 + l1 + k1, client.anInt927, 15, i1 + 1);
	}



	private void processChatModeClick(final GameClient client)
	{
		if(client.clickMode3 == 1)
		{
			if((client.saveClickX >= 6) && (client.saveClickX <= 106) && (client.saveClickY >= 467) && (client.saveClickY <= 499))
			{
				client.publicChatMode = (client.publicChatMode + 1) % 4;
				aBoolean1233 = true;
				client.inputTaken = true;
				client.stream.putPacketID(95);
				client.stream.putLEShort(this.publicChatMode);
				client.stream.putLEShort(this.privateChatMode);
				client.stream.putLEShort(tradeMode);
			}
			if((client.saveClickX >= 135) && (client.saveClickX <= 235) && (client.saveClickY >= 467) && (client.saveClickY <= 499))
			{
				client.privateChatMode = (this.privateChatMode + 1) % 3;
				aBoolean1233 = true;
				client.inputTaken = true;
				client.stream.putPacketID(95);
				client.stream.putLEShort(this.publicChatMode);
				client.stream.putLEShort(this.privateChatMode);
				client.stream.putLEShort(tradeMode);
			}
			if((client.saveClickX >= 273) && (client.saveClickX <= 373) && (client.saveClickY >= 467) && (client.saveClickY <= 499))
			{
				client.tradeMode = (client.tradeMode + 1) % 3;
				aBoolean1233 = true;
				client.inputTaken = true;
				client.stream.putPacketID(95);
				client.stream.putLEShort(this.publicChatMode);
				client.stream.putLEShort(this.privateChatMode);
				client.stream.putLEShort(tradeMode);
			}
			if((client.saveClickX >= 412) && (client.saveClickX <= 512) && (client.saveClickY >= 467) && (client.saveClickY <= 499)) {
				if(openInterfaceID == -1)
				{
					clearTopInterfaces();
					reportAbuseInput = "";
					canMute = false;
					for(int i = 0; i < RSInterface.interfaceCache.length; i++)
					{
						if((RSInterface.interfaceCache[i] == null) || (RSInterface.interfaceCache[i].anInt214 != 600)) {
							continue;
						}
						reportAbuseInterfaceID = openInterfaceID = RSInterface.interfaceCache[i].parentID;
						break;
					}

				} else
				{
					this.pushMessage("Please close the interface you have open before using 'report abuse'", 0, "");
				}
			}
			anInt940++;
			if(anInt940 > 1386)
			{
				anInt940 = 0;
				stream.putPacketID(165);
				stream.putLEShort(0);
				final int j = stream.currentOffset;
				stream.putLEShort(139);
				stream.putLEShort(150);
				stream.putShort(32131);
				stream.putLEShort((int)(Math.random() * 256D));
				stream.putShort(3250);
				stream.putLEShort(177);
				stream.putShort(24859);
				stream.putLEShort(119);
				if((int)(Math.random() * 2D) == 0) {
					stream.putShort(47234);
				}
				if((int)(Math.random() * 2D) == 0) {
					stream.putLEShort(21);
				}
				stream.writeBytes(stream.currentOffset - j);
			}
		}
	}



	private void resetSpokenText(final GameClient client)
	{
		for(int i = -1; i < client.playerCount; i++)
		{
			int j;
			if(i == -1) {
				j = client.myPlayerIndex;
			} else {
				j = client.playerIndices[i];
			}
			final Player player = playerArray[j];
			if((player != null) && (player.textCycle > 0))
			{
				player.textCycle--;
				if(player.textCycle == 0) {
					player.textSpoken = null;
				}
			}
		}

		for(int k = 0; k < npcCount; k++)
		{
			final int l = npcIndices[k];
			final NPC npc = sessionNPCs[l];
			if((npc != null) && (npc.textCycle > 0))
			{
				npc.textCycle--;
				if(npc.textCycle == 0) {
					npc.textSpoken = null;
				}
			}
		}
	}
}