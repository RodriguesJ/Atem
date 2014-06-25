package com.runescape.revised.client;

import com.runescape.client.revised.AbstractSprite;
import com.runescape.client.revised.config.definitions.NPCDef;
import com.runescape.client.revised.config.definitions.ObjectDef;
import com.runescape.client.revised.graphics.image.DrawingArea;
import com.runescape.client.revised.graphics.image.RSImageProducer;
import com.runescape.client.revised.media.IndexedImage;
import com.runescape.client.revised.util.TextClass;

public class Map {

	public AbstractSprite mapDotItem;
	public AbstractSprite mapDotNPC;
	public AbstractSprite mapDotPlayer;
	public AbstractSprite mapDotFriend;
	public AbstractSprite mapDotTeam;
	public AbstractSprite mapFlag;
	public AbstractSprite mapMarker;
	public AbstractSprite mapEdge;
	public int miniMapLock; // anInt1021
	public AbstractSprite[] mapFunctions;
	public int[] minimapShape1; // anIntArray1052
	public int[] compassWidthMap; // anIntArray1057
	public IndexedImage[] mapScenes;
	public int numOfMapMarkers; // anInt1071
	public boolean loadingMap; // aBoolean1080
	public boolean loadGeneratedMap; // aBoolean1159
	public RSImageProducer minimapIP; // aRSImageProducer_1164
	public int minimapZoom; // minimapInt3
	public int nextMinimapZoomOffset; // anInt1171
	public IndexedImage mapBack;
	public int minimapRotation; // minimapInt2
	public int nextMinimapRotationOffset; // anInt1210
	public int[] minimapShape2; // anIntArray1229
	public int[] mapCoordinates; // anIntArray1234
	public AbstractSprite minimapImage; // aClass30_Sub2_Sub1_Sub1_1263
	
	public void Map() {
		mapDotItem = null;
		mapDotNPC = null;
		mapDotPlayer = null;
		mapDotFriend = null;
		mapDotTeam = null;
		mapFunctions = new AbstractSprite[100];
		minimapShape1 = new int[151];
		compassWidthMap = new int[33];
		mapScenes = new IndexedImage[100];
		loadingMap = false;
		loadGeneratedMap = false;
		nextMinimapZoomOffset = 1;
		nextMinimapRotationOffset = 2;
		minimapShape2 = new int[151];
	}

	public void drawMapScenes(GameClient client, int i, int k, int l, int i1, int j1)
	{
		int k1 = client.worldController.method300(j1, l, i);
		if(k1 != 0)
		{
			int l1 = client.worldController.method304(j1, l, i, k1);
			int k2 = l1 >> 6 & 3;
			int i3 = l1 & 0x1f;
			int k3 = k;
			if(k1 > 0)
				k3 = i1;
			int ai[] = aClass30_Sub2_Sub1_Sub1_1263.myPixels;
			int k4 = 24624 + l * 4 + (103 - i) * 512 * 4;
			int i5 = k1 >> 14 & 0x7fff;
			ObjectDef class46_2 = ObjectDef.forID(i5);
			if(class46_2.mapSceneID != -1)
			{
				IndexedImage background_2 = client.mapScenes[class46_2.mapSceneID];
				if(background_2 != null)
				{
					int i6 = (class46_2.width * 4 - background_2.anInt1452) / 2;
					int j6 = (class46_2.height * 4 - background_2.anInt1453) / 2;
					background_2.method361(48 + l * 4 + i6, 48 + (104 - i - class46_2.height) * 4 + j6);
				}
			} else
			{
				if(i3 == 0 || i3 == 2)
					if(k2 == 0)
					{
						ai[k4] = k3;
						ai[k4 + 512] = k3;
						ai[k4 + 1024] = k3;
						ai[k4 + 1536] = k3;
					} else
					if(k2 == 1)
					{
						ai[k4] = k3;
						ai[k4 + 1] = k3;
						ai[k4 + 2] = k3;
						ai[k4 + 3] = k3;
					} else
					if(k2 == 2)
					{
						ai[k4 + 3] = k3;
						ai[k4 + 3 + 512] = k3;
						ai[k4 + 3 + 1024] = k3;
						ai[k4 + 3 + 1536] = k3;
					} else
					if(k2 == 3)
					{
						ai[k4 + 1536] = k3;
						ai[k4 + 1536 + 1] = k3;
						ai[k4 + 1536 + 2] = k3;
						ai[k4 + 1536 + 3] = k3;
					}
				if(i3 == 3)
					if(k2 == 0)
						ai[k4] = k3;
					else
					if(k2 == 1)
						ai[k4 + 3] = k3;
					else
					if(k2 == 2)
						ai[k4 + 3 + 1536] = k3;
					else
					if(k2 == 3)
						ai[k4 + 1536] = k3;
				if(i3 == 2)
					if(k2 == 3)
					{
						ai[k4] = k3;
						ai[k4 + 512] = k3;
						ai[k4 + 1024] = k3;
						ai[k4 + 1536] = k3;
					} else
					if(k2 == 0)
					{
						ai[k4] = k3;
						ai[k4 + 1] = k3;
						ai[k4 + 2] = k3;
						ai[k4 + 3] = k3;
					} else
					if(k2 == 1)
					{
						ai[k4 + 3] = k3;
						ai[k4 + 3 + 512] = k3;
						ai[k4 + 3 + 1024] = k3;
						ai[k4 + 3 + 1536] = k3;
					} else
					if(k2 == 2)
					{
						ai[k4 + 1536] = k3;
						ai[k4 + 1536 + 1] = k3;
						ai[k4 + 1536 + 2] = k3;
						ai[k4 + 1536 + 3] = k3;
					}
			}
		}
		k1 = client.worldController.method302(j1, l, i);
		if(k1 != 0)
		{
			int i2 = client.worldController.method304(j1, l, i, k1);
			int l2 = i2 >> 6 & 3;
			int j3 = i2 & 0x1f;
			int l3 = k1 >> 14 & 0x7fff;
			ObjectDef class46_1 = ObjectDef.forID(l3);
			if(class46_1.mapSceneID != -1)
			{
				IndexedImage background_1 = client.mapScenes[class46_1.mapSceneID];
				if(background_1 != null)
				{
					int j5 = (class46_1.width * 4 - background_1.anInt1452) / 2;
					int k5 = (class46_1.height * 4 - background_1.anInt1453) / 2;
					background_1.method361(48 + l * 4 + j5, 48 + (104 - i - class46_1.height) * 4 + k5);
				}
			} else
			if(j3 == 9)
			{
				int l4 = 0xeeeeee;
				if(k1 > 0)
					l4 = 0xee0000;
				int ai1[] = aClass30_Sub2_Sub1_Sub1_1263.myPixels;
				int l5 = 24624 + l * 4 + (103 - i) * 512 * 4;
				if(l2 == 0 || l2 == 2)
				{
					ai1[l5 + 1536] = l4;
					ai1[l5 + 1024 + 1] = l4;
					ai1[l5 + 512 + 2] = l4;
					ai1[l5 + 3] = l4;
				} else
				{
					ai1[l5] = l4;
					ai1[l5 + 512 + 1] = l4;
					ai1[l5 + 1024 + 2] = l4;
					ai1[l5 + 1536 + 3] = l4;
				}
			}
		}
		k1 = client.worldController.method303(j1, l, i);
		if(k1 != 0)
		{
			int j2 = k1 >> 14 & 0x7fff;
			ObjectDef class46 = ObjectDef.forID(j2);
			if(class46.mapSceneID != -1)
			{
				IndexedImage background = client.mapScenes[class46.mapSceneID];
				if(background != null)
				{
					int i4 = (class46.width * 4 - background.anInt1452) / 2;
					int j4 = (class46.height * 4 - background.anInt1453) / 2;
					background.method361(48 + l * 4 + i4, 48 + (104 - i - class46.height) * 4 + j4);
				}
			}
		}
	}
	
	
	
	
	
	
	private void drawMinimap(GameClient client)
	{
		aRSImageProducer_1164.initDrawingArea();
		if(anInt1021 == 2)
		{
			byte abyte0[] = client.mapBack.aByteArray1450;
			int ai[] = DrawingArea.pixels;
			int k2 = abyte0.length;
			for(int i5 = 0; i5 < k2; i5++)
				if(abyte0[i5] == 0)
					ai[i5] = 0;

			client.compass.method352(33, minimapInt1, anIntArray1057, 256, anIntArray968, 25, 0, 0, 33, 25);
			aRSImageProducer_1165.initDrawingArea();
			return;
		}
		int i = minimapInt1 + minimapInt2 & 0x7ff;
		int j = 48 + GameClient.myPlayer.x / 32;
		int l2 = 464 - GameClient.myPlayer.y / 32;
		aClass30_Sub2_Sub1_Sub1_1263.method352(151, i, anIntArray1229, 256 + minimapInt3, anIntArray1052, l2, 5, 25, 146, j);
		compass.method352(33, minimapInt1, anIntArray1057, 256, anIntArray968, 25, 0, 0, 33, 25);
		for(int j5 = 0; j5 < anInt1071; j5++)
		{
			int k = (anIntArray1072[j5] * 4 + 2) - GameClient.myPlayer.x / 32;
			int i3 = (anIntArray1073[j5] * 4 + 2) - GameClient.myPlayer.y / 32;
			markMinimap(aClass30_Sub2_Sub1_Sub1Array1140[j5], k, i3);
		}

		for(int k5 = 0; k5 < 104; k5++)
		{
			for(int l5 = 0; l5 < 104; l5++)
			{
				Deque class19 = groundArray[plane][k5][l5];
				if(class19 != null)
				{
					int l = (k5 * 4 + 2) - GameClient.myPlayer.x / 32;
					int j3 = (l5 * 4 + 2) - GameClient.myPlayer.y / 32;
					markMinimap(client.mapDotItem, l, j3);
				}
			}

		}

		for(int i6 = 0; i6 < npcCount; i6++)
		{
			NPC npc = sessionNPCs[npcIndices[i6]];
			if(npc != null && npc.isVisible())
			{
				NPCDef entityDef = npc.desc;
				if(entityDef.childrenIDs != null)
					entityDef = entityDef.method161();
				if(entityDef != null && entityDef.aBoolean87 && entityDef.aBoolean84)
				{
					int i1 = npc.x / 32 - myPlayer.x / 32;
					int k3 = npc.y / 32 - myPlayer.y / 32;
					markMinimap(mapDotNPC, i1, k3);
				}
			}
		}

		for(int j6 = 0; j6 < playerCount; j6++)
		{
			Player player = playerArray[playerIndices[j6]];
			if(player != null && player.isVisible())
			{
				int j1 = player.x / 32 - myPlayer.x / 32;
				int l3 = player.y / 32 - myPlayer.y / 32;
				boolean flag1 = false;
				long l6 = TextClass.longForName(player.name);
				for(int k6 = 0; k6 < friendsCount; k6++)
				{
					if(l6 != friendsListAsLongs[k6] || friendsNodeIDs[k6] == 0)
						continue;
					flag1 = true;
					break;
				}

				boolean flag2 = false;
				if(myPlayer.team != 0 && player.team != 0 && myPlayer.team == player.team)
					flag2 = true;
				if(flag1)
					markMinimap(mapDotFriend, j1, l3);
				else
				if(flag2)
					markMinimap(mapDotTeam, j1, l3);
				else
					markMinimap(mapDotPlayer, j1, l3);
			}
		}

		if(headiconDrawType != 0 && loopCycle % 20 < 10)
		{
			if(headiconDrawType == 1 && anInt1222 >= 0 && anInt1222 < sessionNPCs.length)
			{
				NPC class30_sub2_sub4_sub1_sub1_1 = sessionNPCs[anInt1222];
				if(class30_sub2_sub4_sub1_sub1_1 != null)
				{
					int k1 = class30_sub2_sub4_sub1_sub1_1.x / 32 - myPlayer.x / 32;
					int i4 = class30_sub2_sub4_sub1_sub1_1.y / 32 - myPlayer.y / 32;
					method81(mapMarker, i4, k1);
				}
			}
			if(client.headiconDrawType == 2)
			{
				int l1 = ((anInt934 - client.baseX) * 4 + 2) - GameClient.myPlayer.x / 32;
				int j4 = ((anInt935 - client.baseY) * 4 + 2) - GameClient.myPlayer.y / 32;
				method81(mapMarker, j4, l1);
			}
			if(client.headiconDrawType == 10 && anInt933 >= 0 && anInt933 < playerArray.length)
			{
				Player class30_sub2_sub4_sub1_sub2_1 = playerArray[anInt933];
				if(class30_sub2_sub4_sub1_sub2_1 != null)
				{
					int i2 = class30_sub2_sub4_sub1_sub2_1.x / 32 - myPlayer.x / 32;
					int k4 = class30_sub2_sub4_sub1_sub2_1.y / 32 - myPlayer.y / 32;
					method81(client.mapMarker, k4, i2);
				}
			}
		}
		if(client.destX != 0)
		{
			int j2 = (client.destX * 4 + 2) - GameClient.myPlayer.x / 32;
			int l4 = (client.destY * 4 + 2) - GameClient.myPlayer.y / 32;
			markMinimap(client.mapFlag, j2, l4);
		}
		DrawingArea.method336(3, 78, 97, 0xffffff, 3);
		aRSImageProducer_1165.initDrawingArea();
	}
	
	
	
	
	public void renderMapScene(GameClient client, int i)
	{
		int ai[] = aClass30_Sub2_Sub1_Sub1_1263.myPixels;
		int j = ai.length;
		for(int k = 0; k < j; k++)
			ai[k] = 0;

		for(int l = 1; l < 103; l++)
		{
			int i1 = 24628 + (103 - l) * 512 * 4;
			for(int k1 = 1; k1 < 103; k1++)
			{
				if((byteGroundArray[i][k1][l] & 0x18) == 0)
					client.worldController.method309(ai, i1, i, k1, l);
				if(i < 3 && (byteGroundArray[i + 1][k1][l] & 8) != 0)
					worldController.method309(ai, i1, i + 1, k1, l);
				i1 += 4;
			}

		}

		int j1 = ((238 + (int)(Math.random() * 20D)) - 10 << 16) + ((238 + (int)(Math.random() * 20D)) - 10 << 8) + ((238 + (int)(Math.random() * 20D)) - 10);
		int l1 = (238 + (int)(Math.random() * 20D)) - 10 << 16;
		aClass30_Sub2_Sub1_Sub1_1263.method343();
		for(int i2 = 1; i2 < 103; i2++)
		{
			for(int j2 = 1; j2 < 103; j2++)
			{
				if((byteGroundArray[i][j2][i2] & 0x18) == 0)
					drawMapScenes(i2, j1, j2, l1, i);
				if(i < 3 && (byteGroundArray[i + 1][j2][i2] & 8) != 0)
					drawMapScenes(i2, j1, j2, l1, i + 1);
			}

		}

		aRSImageProducer_1165.initDrawingArea();
		anInt1071 = 0;
		for(int k2 = 0; k2 < 104; k2++)
		{
			for(int l2 = 0; l2 < 104; l2++)
			{
				int i3 = worldController.method303(plane, k2, l2);
				if(i3 != 0)
				{
					i3 = i3 >> 14 & 0x7fff;
					int j3 = ObjectDef.forID(i3).mapFunctionID;
					if(j3 >= 0)
					{
						int k3 = k2;
						int l3 = l2;
						if(j3 != 22 && j3 != 29 && j3 != 34 && j3 != 36 && j3 != 46 && j3 != 47 && j3 != 48)
						{
							byte byte0 = 104;
							byte byte1 = 104;
							int ai1[][] = aClass11Array1230[plane].anIntArrayArray294;
							for(int i4 = 0; i4 < 10; i4++)
							{
								int j4 = (int)(Math.random() * 4D);
								if(j4 == 0 && k3 > 0 && k3 > k2 - 3 && (ai1[k3 - 1][l3] & 0x1280108) == 0)
									k3--;
								if(j4 == 1 && k3 < byte0 - 1 && k3 < k2 + 3 && (ai1[k3 + 1][l3] & 0x1280180) == 0)
									k3++;
								if(j4 == 2 && l3 > 0 && l3 > l2 - 3 && (ai1[k3][l3 - 1] & 0x1280102) == 0)
									l3--;
								if(j4 == 3 && l3 < byte1 - 1 && l3 < l2 + 3 && (ai1[k3][l3 + 1] & 0x1280120) == 0)
									l3++;
							}
						}
						aClass30_Sub2_Sub1_Sub1Array1140[anInt1071] = mapFunctions[j3];
						anIntArray1072[anInt1071] = k3;
						anIntArray1073[anInt1071] = l3;
						anInt1071++;
					}
				}
			}
		}
	}
	
	
	
	public void refreshMinimap(AbstractSprite sprite, int j, int k) // method81
	{
		int l = k * k + j * j;
		if(l > 4225 && l < 0x15f90)
		{
			int i1 = minimapInt1 + minimapInt2 & 0x7ff;
			int j1 = Model.modelIntArray1[i1];
			int k1 = Model.modelIntArray2[i1];
			j1 = (j1 * 256) / (minimapInt3 + 256);
			k1 = (k1 * 256) / (minimapInt3 + 256);
			int l1 = j * j1 + k * k1 >> 16;
			int i2 = j * k1 - k * j1 >> 16;
			double d = Math.atan2(l1, i2);
			int j2 = (int)(Math.sin(d) * 63D);
			int k2 = (int)(Math.cos(d) * 57D);
			mapEdge.method353(83 - k2 - 20, d, (94 + j2 + 4) - 10);
		} else
		{
			markMinimap(sprite, k, j);
		}
	}
	
	
	
	
	public void markMinimap(AbstractSprite sprite, int i, int j)
	{
		int k = minimapInt1 + minimapInt2 & 0x7ff;
		int l = i * i + j * j;
		if(l > 6400)
			return;
		int i1 = Model.modelIntArray1[k];
		int j1 = Model.modelIntArray2[k];
		i1 = (i1 * 256) / (minimapInt3 + 256);
		j1 = (j1 * 256) / (minimapInt3 + 256);
		int k1 = j * i1 + i * j1 >> 16;
		int l1 = j * j1 - i * i1 >> 16;
		if(l > 2500)
		{
			sprite.method354(mapBack, 83 - l1 - sprite.anInt1445 / 2 - 4, ((94 + k1) - sprite.anInt1444 / 2) + 4);
		} else
		{
			sprite.drawSprite(((94 + k1) - sprite.anInt1444 / 2) + 4, 83 - l1 - sprite.anInt1445 / 2 - 4);
		}
	}
}