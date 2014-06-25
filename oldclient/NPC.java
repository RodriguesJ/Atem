package com.runescape.revised.client;

import com.runescape.client.SignLink;
import com.runescape.client.revised.config.definitions.NPCDef;

public class NPC {
	
	public NPC[] sessionNPCs; // npcArray
	public int npcCount;
	public int[] npcIndices;
	
	public NPC() {
		sessionNPCs = new NPC[16384];
		npcIndices = new int[16384];
	}

	private void showNPCs(boolean flag)
	{
		for(int j = 0; j < npcCount; j++)
		{
			NPC npc = sessionNPCs[npcIndices[j]];
			int k = 0x20000000 + (npcIndices[j] << 14);
			if(npc == null || !npc.isVisible() || npc.desc.aBoolean93 != flag)
				continue;
			int l = npc.x >> 7;
			int i1 = npc.y >> 7;
			if(l < 0 || l >= 104 || i1 < 0 || i1 >= 104)
				continue;
			if(npc.anInt1540 == 1 && (npc.x & 0x7f) == 64 && (npc.y & 0x7f) == 64)
			{
				if(anIntArrayArray929[l][i1] == anInt1265)
					continue;
				anIntArrayArray929[l][i1] = anInt1265;
			}
			if(!npc.desc.aBoolean84)
				k += 0x80000000;
			worldController.method285(plane, npc.anInt1552, method42(plane, npc.y, npc.x), k, npc.y, (npc.anInt1540 - 1) * 64 + 60, npc.x, npc, npc.aBoolean1541);
		}
	}
	
	
	
	
	
	private void updateNPCMovement(int i, RSBuffer stream)
	{
		while(stream.bitPosition + 21 < i * 8)
		{
			int k = stream.readBits(14);
			if(k == 16383)
				break;
			if(sessionNPCs[k] == null)
				sessionNPCs[k] = new NPC();
			NPC npc = sessionNPCs[k];
			npcIndices[npcCount++] = k;
			npc.anInt1537 = loopCycle;
			int l = stream.readBits(5);
			if(l > 15)
				l -= 32;
			int i1 = stream.readBits(5);
			if(i1 > 15)
				i1 -= 32;
			int j1 = stream.readBits(1);
			npc.desc = NPCDef.forID(stream.readBits(12));
			int k1 = stream.readBits(1);
			if(k1 == 1)
				anIntArray894[anInt893++] = k;
			npc.anInt1540 = npc.desc.aByte68;
			npc.anInt1504 = npc.desc.anInt79;
			npc.anInt1554 = npc.desc.anInt67;
			npc.anInt1555 = npc.desc.anInt58;
			npc.anInt1556 = npc.desc.anInt83;
			npc.anInt1557 = npc.desc.anInt55;
			npc.anInt1511 = npc.desc.anInt77;
			npc.setPos(myPlayer.smallX[0] + i1, myPlayer.smallY[0] + l, j1 == 1);
		}
		stream.finishBitAccess();
	}
	
	
	
	
	
	
	private void forceNPCUpdateBlock()
	{
		for(int j = 0; j < npcCount; j++)
		{
			int k = npcIndices[j];
			NPC npc = sessionNPCs[k];
			if(npc != null)
				entityUpdateBlock(npc);
		}
	}



	

	private void updateNPCs(RSBuffer stream, int i)
	{
		anInt839 = 0;
		anInt893 = 0;
		method139(stream);
		updateNPCMovement(i, stream);
		npcUpdateMask(stream);
		for(int k = 0; k < anInt839; k++)
		{
			int l = anIntArray840[k];
			if(sessionNPCs[l].anInt1537 != loopCycle)
			{
				sessionNPCs[l].desc = null;
				sessionNPCs[l] = null;
			}
		}

		if(stream.currentOffset != i)
		{
			SignLink.reporterror(myUsername + " size mismatch in getnpcpos - pos:" + stream.currentOffset + " psize:" + i);
			throw new RuntimeException("eek");
		}
		for(int i1 = 0; i1 < npcCount; i1++)
			if(sessionNPCs[npcIndices[i1]] == null)
			{
				SignLink.reporterror(myUsername + " null entry in npc list - pos:" + i1 + " size:" + npcCount);
				throw new RuntimeException("eek");
			}

	}
	
	
	
	private void npcScreenPos(Entity entity, int i)
	{
		calcEntityScreenPos(entity.x, i, entity.y);

//aryan	entity.entScreenX = spriteDrawX; entity.entScreenY = spriteDrawY;
	}




	
	private void updateMoreNPCs(RSBuffer stream) //method139
	{
		stream.initBitAccess();
		int k = stream.readBits(8);
		if(k < npcCount)
		{
			for(int l = k; l < npcCount; l++)
				anIntArray840[anInt839++] = npcIndices[l];

		}
		if(k > npcCount)
		{
			SignLink.reporterror(myUsername + " Too many npcs");
			throw new RuntimeException("eek");
		}
		npcCount = 0;
		for(int i1 = 0; i1 < k; i1++)
		{
			int j1 = npcIndices[i1];
			NPC npc = sessionNPCs[j1];
			int k1 = stream.readBits(1);
			if(k1 == 0)
			{
				npcIndices[npcCount++] = j1;
				npc.anInt1537 = loopCycle;
			} else
			{
				int l1 = stream.readBits(2);
				if(l1 == 0)
				{
					npcIndices[npcCount++] = j1;
					npc.anInt1537 = loopCycle;
					anIntArray894[anInt893++] = j1;
				} else
				if(l1 == 1)
				{
					npcIndices[npcCount++] = j1;
					npc.anInt1537 = loopCycle;
					int i2 = stream.readBits(3);
					npc.moveInDir(false, i2);
					int k2 = stream.readBits(1);
					if(k2 == 1)
						anIntArray894[anInt893++] = j1;
				} else
				if(l1 == 2)
				{
					npcIndices[npcCount++] = j1;
					npc.anInt1537 = loopCycle;
					int j2 = stream.readBits(3);
					npc.moveInDir(true, j2);
					int l2 = stream.readBits(3);
					npc.moveInDir(true, l2);
					int i3 = stream.readBits(1);
					if(i3 == 1)
						anIntArray894[anInt893++] = j1;
				} else
				if(l1 == 3)
					anIntArray840[anInt839++] = j1;
			}
		}
	}
}