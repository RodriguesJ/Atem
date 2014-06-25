package com.runescape.revised.client;

public class UpdateMask {

	private void refreshUpdateMasks(RSBuffer stream)
	{
		for(int j = 0; j < anInt893; j++)
		{
			int k = anIntArray894[j];
			Player player = playerArray[k];
			int l = stream.readUnsignedByte();
			if((l & 0x40) != 0)
				l += stream.readUnsignedByte() << 8;
			appendPlayerUpdateMask(l, k, stream, player);
		}

	}
	
	
	
	
	
	private void npcUpdateMask(RSBuffer stream)
	{
		for(int j = 0; j < anInt893; j++)
		{
			int k = anIntArray894[j];
			NPC npc = sessionNPCs[k];
			int l = stream.readUnsignedByte();
			if((l & 0x10) != 0)
			{
				int i1 = stream.method434();
				if(i1 == 65535)
					i1 = -1;
				int i2 = stream.readUnsignedByte();
				if(i1 == npc.anim && i1 != -1)
				{
					int l2 = Animation.anims[i1].anInt365;
					if(l2 == 1)
					{
						npc.anInt1527 = 0;
						npc.anInt1528 = 0;
						npc.anInt1529 = i2;
						npc.anInt1530 = 0;
					}
					if(l2 == 2)
						npc.anInt1530 = 0;
				} else
				if(i1 == -1 || npc.anim == -1 || Animation.anims[i1].anInt359 >= Animation.anims[npc.anim].anInt359)
				{
					npc.anim = i1;
					npc.anInt1527 = 0;
					npc.anInt1528 = 0;
					npc.anInt1529 = i2;
					npc.anInt1530 = 0;
					npc.anInt1542 = npc.smallXYIndex;
				}
			}
			if((l & 8) != 0)
			{
				int j1 = stream.method426();
				int j2 = stream.method427();
				npc.updateHitData(j2, j1, loopCycle);
				npc.loopCycleStatus = loopCycle + 300;
				npc.currentHealth = stream.method426();
				npc.maxHealth = stream.readUnsignedByte();
			}
			if((l & 0x80) != 0)
			{
				npc.anInt1520 = stream.readUnsignedWord();
				int k1 = stream.readDWord();
				npc.anInt1524 = k1 >> 16;
				npc.anInt1523 = loopCycle + (k1 & 0xffff);
				npc.anInt1521 = 0;
				npc.anInt1522 = 0;
				if(npc.anInt1523 > loopCycle)
					npc.anInt1521 = -1;
				if(npc.anInt1520 == 65535)
					npc.anInt1520 = -1;
			}
			if((l & 0x20) != 0)
			{
				npc.interactingEntity = stream.readUnsignedWord();
				if(npc.interactingEntity == 65535)
					npc.interactingEntity = -1;
			}
			if((l & 1) != 0)
			{
				npc.textSpoken = stream.readString();
				npc.textCycle = 100;
//	entityMessage(npc);
	
			}
			if((l & 0x40) != 0)
			{
				int l1 = stream.method427();
				int k2 = stream.method428();
				npc.updateHitData(k2, l1, loopCycle);
				npc.loopCycleStatus = loopCycle + 300;
				npc.currentHealth = stream.method428();
				npc.maxHealth = stream.method427();
			}
			if((l & 2) != 0)
			{
				npc.desc = NPCDef.forID(stream.method436());
				npc.anInt1540 = npc.desc.aByte68;
				npc.anInt1504 = npc.desc.anInt79;
				npc.anInt1554 = npc.desc.anInt67;
				npc.anInt1555 = npc.desc.anInt58;
				npc.anInt1556 = npc.desc.anInt83;
				npc.anInt1557 = npc.desc.anInt55;
				npc.anInt1511 = npc.desc.anInt77;
			}
			if((l & 4) != 0)
			{
				npc.anInt1538 = stream.method434();
				npc.anInt1539 = stream.method434();
			}
		}
	}

	private void updateOtherPlayerMovement(RSBuffer stream, int i)
	{
		while(stream.bitPosition + 10 < i * 8)
		{
			int j = stream.readBits(11);
			if(j == 2047)
				break;
			if(playerArray[j] == null)
			{
				playerArray[j] = new Player();
				if(aStreamArray895s[j] != null)
					playerArray[j].updatePlayer(aStreamArray895s[j]);
			}
			playerIndices[playerCount++] = j;
			Player player = playerArray[j];
			player.anInt1537 = loopCycle;
			int k = stream.readBits(1);
			if(k == 1)
				anIntArray894[anInt893++] = j;
			int l = stream.readBits(1);
			int i1 = stream.readBits(5);
			if(i1 > 15)
				i1 -= 32;
			int j1 = stream.readBits(5);
			if(j1 > 15)
				j1 -= 32;
			player.setPos(myPlayer.smallX[0] + j1, myPlayer.smallY[0] + i1, l == 1);
		}
		stream.finishBitAccess();
	}
	
	
	
	
	
	
	private void updatePlayerInstances()
	{
		for(int i = -1; i < playerCount; i++)
		{
			int j;
			if(i == -1)
				j = myPlayerIndex;
			else
				j = playerIndices[i];
			Player player = playerArray[j];
			if(player != null)
				entityUpdateBlock(player);
		}

	}





	private void updatePlayer(RSBuffer stream)
	{
		int j = stream.readBits(8);
		if(j < playerCount)
		{
			for(int k = j; k < playerCount; k++)
				anIntArray840[anInt839++] = playerIndices[k];

		}
		if(j > playerCount)
		{
			SignLink.reporterror(myUsername + " Too many players");
			throw new RuntimeException("eek");
		}
		playerCount = 0;
		for(int l = 0; l < j; l++)
		{
			int i1 = playerIndices[l];
			Player player = playerArray[i1];
			int j1 = stream.readBits(1);
			if(j1 == 0)
			{
				playerIndices[playerCount++] = i1;
				player.anInt1537 = loopCycle;
			} else
			{
				int k1 = stream.readBits(2);
				if(k1 == 0)
				{
					playerIndices[playerCount++] = i1;
					player.anInt1537 = loopCycle;
					anIntArray894[anInt893++] = i1;
				} else
				if(k1 == 1)
				{
					playerIndices[playerCount++] = i1;
					player.anInt1537 = loopCycle;
					int l1 = stream.readBits(3);
					player.moveInDir(false, l1);
					int j2 = stream.readBits(1);
					if(j2 == 1)
						anIntArray894[anInt893++] = i1;
				} else
				if(k1 == 2)
				{
					playerIndices[playerCount++] = i1;
					player.anInt1537 = loopCycle;
					int i2 = stream.readBits(3);
					player.moveInDir(true, i2);
					int k2 = stream.readBits(3);
					player.moveInDir(true, k2);
					int l2 = stream.readBits(1);
					if(l2 == 1)
						anIntArray894[anInt893++] = i1;
				} else
				if(k1 == 3)
					anIntArray840[anInt839++] = i1;
			}
		}
	}



	
	private void appendPlayerUpdateMask(int i, int j, RSBuffer stream, Player player)
	{
		if((i & 0x400) != 0)
		{
			player.anInt1543 = stream.method428();
			player.anInt1545 = stream.method428();
			player.anInt1544 = stream.method428();
			player.anInt1546 = stream.method428();
			player.anInt1547 = stream.method436() + loopCycle;
			player.anInt1548 = stream.method435() + loopCycle;
			player.anInt1549 = stream.method428();
			player.method446();
		}
		if((i & 0x100) != 0)
		{
			player.anInt1520 = stream.method434();
			int k = stream.readDWord();
			player.anInt1524 = k >> 16;
			player.anInt1523 = loopCycle + (k & 0xffff);
			player.anInt1521 = 0;
			player.anInt1522 = 0;
			if(player.anInt1523 > loopCycle)
				player.anInt1521 = -1;
			if(player.anInt1520 == 65535)
				player.anInt1520 = -1;
		}
		if((i & 8) != 0)
		{
			int l = stream.method434();
			if(l == 65535)
				l = -1;
			int i2 = stream.method427();
			if(l == player.anim && l != -1)
			{
				int i3 = Animation.anims[l].anInt365;
				if(i3 == 1)
				{
					player.anInt1527 = 0;
					player.anInt1528 = 0;
					player.anInt1529 = i2;
					player.anInt1530 = 0;
				}
				if(i3 == 2)
					player.anInt1530 = 0;
			} else
			if(l == -1 || player.anim == -1 || Animation.anims[l].anInt359 >= Animation.anims[player.anim].anInt359)
			{
				player.anim = l;
				player.anInt1527 = 0;
				player.anInt1528 = 0;
				player.anInt1529 = i2;
				player.anInt1530 = 0;
				player.anInt1542 = player.smallXYIndex;
			}
		}
		if((i & 4) != 0)
		{
			player.textSpoken = stream.readString();
			if(player.textSpoken.charAt(0) == '~')
			{
				player.textSpoken = player.textSpoken.substring(1);
				pushMessage(player.textSpoken, 2, player.name);
			} else
			if(player == myPlayer)
				pushMessage(player.textSpoken, 2, player.name);
			player.anInt1513 = 0;
			player.anInt1531 = 0;
			player.textCycle = 150;
		}
		if((i & 0x80) != 0)
		{
			int i1 = stream.method434();
			int j2 = stream.readUnsignedByte();
			int j3 = stream.method427();
			int k3 = stream.currentOffset;
			if(player.name != null && player.visible)
			{
				long l3 = TextClass.longForName(player.name);
				boolean flag = false;
				if(j2 <= 1)
				{
					for(int i4 = 0; i4 < ignoreCount; i4++)
					{
						if(ignoreListAsLongs[i4] != l3)
							continue;
						flag = true;
						break;
					}

				}
				if(!flag && anInt1251 == 0)
					try
					{
						aStream_834.currentOffset = 0;
						stream.method442(j3, 0, aStream_834.buffer);
						aStream_834.currentOffset = 0;
						String s = TextInput.method525(j3, aStream_834);
						s = Censor.doCensor(s);
						player.textSpoken = s;
						player.anInt1513 = i1 >> 8;
						player.privelage = j2;

						//entityMessage(player);
	
						player.anInt1531 = i1 & 0xff;
						player.textCycle = 150;
						if(j2 == 2 || j2 == 3)
							pushMessage(s, 1, "@cr2@" + player.name);
						else
						if(j2 == 1)
							pushMessage(s, 1, "@cr1@" + player.name);
						else
							pushMessage(s, 2, player.name);
					}
					catch(Exception exception)
					{
						SignLink.reporterror("cde2");
					}
			}
			stream.currentOffset = k3 + j3;
		}
		if((i & 1) != 0)
		{
			player.interactingEntity = stream.method434();
			if(player.interactingEntity == 65535)
				player.interactingEntity = -1;
		}
		if((i & 0x10) != 0)
		{
			int j1 = stream.method427();
			byte abyte0[] = new byte[j1];
			RSBuffer stream_1 = new RSBuffer(abyte0);
			stream.readBytes(j1, 0, abyte0);
			aStreamArray895s[j] = stream_1;
			player.updatePlayer(stream_1);
		}
		if((i & 2) != 0)
		{
			player.anInt1538 = stream.method436();
			player.anInt1539 = stream.method434();
		}
		if((i & 0x20) != 0)
		{
			int k1 = stream.readUnsignedByte();
			int k2 = stream.method426();
			player.updateHitData(k2, k1, loopCycle);
			player.loopCycleStatus = loopCycle + 300;
			player.currentHealth = stream.method427();
			player.maxHealth = stream.readUnsignedByte();
		}
		if((i & 0x200) != 0)
		{
			int l1 = stream.readUnsignedByte();
			int l2 = stream.method428();
			player.updateHitData(l2, l1, loopCycle);
			player.loopCycleStatus = loopCycle + 300;
			player.currentHealth = stream.readUnsignedByte();
			player.maxHealth = stream.method427();
		}
	}
	
	
	
	
	public void updatePlayers(int i, RSBuffer stream)
	{
		anInt839 = 0;
		anInt893 = 0;
		updatePlayerMovement(stream);
		updatePlayer(stream);
		updateOtherPlayerMovement(stream, i);
		refreshUpdateMasks(stream);
		for(int k = 0; k < anInt839; k++)
		{
			int l = anIntArray840[k];
			if(playerArray[l].anInt1537 != loopCycle)
				playerArray[l] = null;
		}

		if(stream.currentOffset != i)
		{
			SignLink.reporterror("Error packet size mismatch in getplayer pos:" + stream.currentOffset + " psize:" + i);
			throw new RuntimeException("eek");
		}
		for(int i1 = 0; i1 < playerCount; i1++)
			if(playerArray[playerIndices[i1]] == null)
			{
				SignLink.reporterror(myUsername + " null entry in pl list - pos:" + i1 + " size:" + playerCount);
				throw new RuntimeException("eek");
			}

	}
}
}