package com.runescape.revised.client;

import com.runescape.client.revised.config.definitions.ObjectDef;
import com.runescape.client.revised.graphics.cache.FloorDecoration;
import com.runescape.client.revised.graphics.cache.InteractiveObject;
import com.runescape.client.revised.graphics.cache.WallDecoration;
import com.runescape.client.revised.packets.server.StillGraphic;

public class Model {

	private void manageModelCreations(RSBuffer stream, int j)
	{
		if(j == 84)
		{
			int k = stream.readUnsignedByte();
			int j3 = anInt1268 + (k >> 4 & 7);
			int i6 = anInt1269 + (k & 7);
			int l8 = stream.readUnsignedWord();
			int k11 = stream.readUnsignedWord();
			int l13 = stream.readUnsignedWord();
			if(j3 >= 0 && i6 >= 0 && j3 < 104 && i6 < 104)
			{
				Deque class19_1 = groundArray[plane][j3][i6];
				if(class19_1 != null)
				{
					for(Item class30_sub2_sub4_sub2_3 = (Item)class19_1.reverseGetFirst(); class30_sub2_sub4_sub2_3 != null; class30_sub2_sub4_sub2_3 = (Item)class19_1.reverseGetNext())
					{
						if(class30_sub2_sub4_sub2_3.ID != (l8 & 0x7fff) || class30_sub2_sub4_sub2_3.anInt1559 != k11)
							continue;
						class30_sub2_sub4_sub2_3.anInt1559 = l13;
						break;
					}

					spawnGroundItem(j3, i6);
				}
			}
			return;
		}
		if(j == 105)
		{
			int l = stream.readUnsignedByte();
			int k3 = anInt1268 + (l >> 4 & 7);
			int j6 = anInt1269 + (l & 7);
			int i9 = stream.readUnsignedWord();
			int l11 = stream.readUnsignedByte();
			int i14 = l11 >> 4 & 0xf;
			int i16 = l11 & 7;
			if(myPlayer.smallX[0] >= k3 - i14 && myPlayer.smallX[0] <= k3 + i14 && myPlayer.smallY[0] >= j6 - i14 && myPlayer.smallY[0] <= j6 + i14 && wave_on && !lowMem && anInt1062 < 50)
			{
				anIntArray1207[anInt1062] = i9;
				anIntArray1241[anInt1062] = i16;
				anIntArray1250[anInt1062] = Sounds.anIntArray326[i9];
				anInt1062++;
			}
		}
		if(j == 215)
		{
			int i1 = stream.method435();
			int l3 = stream.method428();
			int k6 = anInt1268 + (l3 >> 4 & 7);
			int j9 = anInt1269 + (l3 & 7);
			int i12 = stream.method435();
			int j14 = stream.readUnsignedWord();
			if(k6 >= 0 && j9 >= 0 && k6 < 104 && j9 < 104 && i12 != unknownInt10)
			{
				Item class30_sub2_sub4_sub2_2 = new Item();
				class30_sub2_sub4_sub2_2.ID = i1;
				class30_sub2_sub4_sub2_2.anInt1559 = j14;
				if(groundArray[plane][k6][j9] == null)
					groundArray[plane][k6][j9] = new Deque();
				groundArray[plane][k6][j9].insertHead(class30_sub2_sub4_sub2_2);
				spawnGroundItem(k6, j9);
			}
			return;
		}
		if(j == 156)
		{
			int j1 = stream.method426();
			int i4 = anInt1268 + (j1 >> 4 & 7);
			int l6 = anInt1269 + (j1 & 7);
			int k9 = stream.readUnsignedWord();
			if(i4 >= 0 && l6 >= 0 && i4 < 104 && l6 < 104)
			{
				Deque class19 = groundArray[plane][i4][l6];
				if(class19 != null)
				{
					for(Item item = (Item)class19.reverseGetFirst(); item != null; item = (Item)class19.reverseGetNext())
					{
						if(item.ID != (k9 & 0x7fff))
							continue;
						item.unlink();
						break;
					}

					if(class19.reverseGetFirst() == null)
						groundArray[plane][i4][l6] = null;
					spawnGroundItem(i4, l6);
				}
			}
			return;
		}
		if(j == 160)
		{
			int k1 = stream.method428();
			int j4 = anInt1268 + (k1 >> 4 & 7);
			int i7 = anInt1269 + (k1 & 7);
			int l9 = stream.method428();
			int j12 = l9 >> 2;
			int k14 = l9 & 3;
			int j16 = anIntArray1177[j12];
			int j17 = stream.method435();
			if(j4 >= 0 && i7 >= 0 && j4 < 103 && i7 < 103)
			{
				int j18 = intGroundArray[plane][j4][i7];
				int i19 = intGroundArray[plane][j4 + 1][i7];
				int l19 = intGroundArray[plane][j4 + 1][i7 + 1];
				int k20 = intGroundArray[plane][j4][i7 + 1];
				if(j16 == 0)
				{
					Wall class10 = worldController.method296(plane, j4, i7);
					if(class10 != null)
					{
						int k21 = class10.uid >> 14 & 0x7fff;
						if(j12 == 2)
						{
							class10.aClass30_Sub2_Sub4_278 = new GameObject(k21, 4 + k14, 2, i19, l19, j18, k20, j17, false);
							class10.aClass30_Sub2_Sub4_279 = new GameObject(k21, k14 + 1 & 3, 2, i19, l19, j18, k20, j17, false);
						} else
						{
							class10.aClass30_Sub2_Sub4_278 = new GameObject(k21, k14, j12, i19, l19, j18, k20, j17, false);
						}
					}
				}
				if(j16 == 1)
				{
					WallDecoration class26 = worldController.method297(j4, i7, plane);
					if(class26 != null)
						class26.aClass30_Sub2_Sub4_504 = new GameObject(class26.uid >> 14 & 0x7fff, 0, 4, i19, l19, j18, k20, j17, false);
				}
				if(j16 == 2)
				{
					InteractiveObject class28 = worldController.method298(j4, i7, plane);
					if(j12 == 11)
						j12 = 10;
					if(class28 != null)
						class28.aClass30_Sub2_Sub4_521 = new GameObject(class28.uid >> 14 & 0x7fff, k14, j12, i19, l19, j18, k20, j17, false);
				}
				if(j16 == 3)
				{
					FloorDecoration class49 = worldController.method299(i7, j4, plane);
					if(class49 != null)
						class49.aClass30_Sub2_Sub4_814 = new GameObject(class49.uid >> 14 & 0x7fff, k14, 22, i19, l19, j18, k20, j17, false);
				}
			}
			return;
		}
		if(j == 147)
		{
			int l1 = stream.method428();
			int k4 = anInt1268 + (l1 >> 4 & 7);
			int j7 = anInt1269 + (l1 & 7);
			int i10 = stream.readUnsignedWord();
			byte byte0 = stream.method430();
			int l14 = stream.method434();
			byte byte1 = stream.method429();
			int k17 = stream.readUnsignedWord();
			int k18 = stream.method428();
			int j19 = k18 >> 2;
			int i20 = k18 & 3;
			int l20 = anIntArray1177[j19];
			byte byte2 = stream.readSignedByte();
			int l21 = stream.readUnsignedWord();
			byte byte3 = stream.method429();
			Player player;
			if(i10 == unknownInt10)
				player = myPlayer;
			else
				player = playerArray[i10];
			if(player != null)
			{
				ObjectDef class46 = ObjectDef.forID(l21);
				int i22 = intGroundArray[plane][k4][j7];
				int j22 = intGroundArray[plane][k4 + 1][j7];
				int k22 = intGroundArray[plane][k4 + 1][j7 + 1];
				int l22 = intGroundArray[plane][k4][j7 + 1];
				Model model = class46.method578(j19, i20, i22, j22, k22, l22, -1);
				if(model != null)
				{
					createObject(k17 + 1, -1, 0, l20, j7, 0, plane, k4, l14 + 1);
					player.anInt1707 = l14 + loopCycle;
					player.anInt1708 = k17 + loopCycle;
					player.aModel_1714 = model;
					int i23 = class46.width;
					int j23 = class46.height;
					if(i20 == 1 || i20 == 3)
					{
						i23 = class46.height;
						j23 = class46.width;
					}
					player.anInt1711 = k4 * 128 + i23 * 64;
					player.anInt1713 = j7 * 128 + j23 * 64;
					player.anInt1712 = method42(plane, player.anInt1713, player.anInt1711);
					if(byte2 > byte0)
					{
						byte byte4 = byte2;
						byte2 = byte0;
						byte0 = byte4;
					}
					if(byte3 > byte1)
					{
						byte byte5 = byte3;
						byte3 = byte1;
						byte1 = byte5;
					}
					player.anInt1719 = k4 + byte2;
					player.anInt1721 = k4 + byte0;
					player.anInt1720 = j7 + byte3;
					player.anInt1722 = j7 + byte1;
				}
			}
		}
		if(j == 151)
		{
			int i2 = stream.method426();
			int l4 = anInt1268 + (i2 >> 4 & 7);
			int k7 = anInt1269 + (i2 & 7);
			int j10 = stream.method434();
			int k12 = stream.method428();
			int i15 = k12 >> 2;
			int k16 = k12 & 3;
			int l17 = anIntArray1177[i15];
			if(l4 >= 0 && k7 >= 0 && l4 < 104 && k7 < 104)
				createObject(-1, j10, k16, l17, k7, i15, plane, l4, 0);
			return;
		}
		if(j == 4)
		{
			int j2 = stream.readUnsignedByte();
			int i5 = anInt1268 + (j2 >> 4 & 7);
			int l7 = anInt1269 + (j2 & 7);
			int k10 = stream.readUnsignedWord();
			int l12 = stream.readUnsignedByte();
			int j15 = stream.readUnsignedWord();
			if(i5 >= 0 && l7 >= 0 && i5 < 104 && l7 < 104)
			{
				i5 = i5 * 128 + 64;
				l7 = l7 * 128 + 64;
				StillGraphic class30_sub2_sub4_sub3 = new StillGraphic(plane, loopCycle, j15, k10, method42(plane, l7, i5) - l12, l7, i5);
				aClass19_1056.insertHead(class30_sub2_sub4_sub3);
			}
			return;
		}
		if(j == 44)
		{
			int k2 = stream.method436();
			int j5 = stream.readUnsignedWord();
			int i8 = stream.readUnsignedByte();
			int l10 = anInt1268 + (i8 >> 4 & 7);
			int i13 = anInt1269 + (i8 & 7);
			if(l10 >= 0 && i13 >= 0 && l10 < 104 && i13 < 104)
			{
				Item class30_sub2_sub4_sub2_1 = new Item();
				class30_sub2_sub4_sub2_1.ID = k2;
				class30_sub2_sub4_sub2_1.anInt1559 = j5;
				if(groundArray[plane][l10][i13] == null)
					groundArray[plane][l10][i13] = new Deque();
				groundArray[plane][l10][i13].insertHead(class30_sub2_sub4_sub2_1);
				spawnGroundItem(l10, i13);
			}
			return;
		}
		if(j == 101)
		{
			int l2 = stream.method427();
			int k5 = l2 >> 2;
			int j8 = l2 & 3;
			int i11 = anIntArray1177[k5];
			int j13 = stream.readUnsignedByte();
			int k15 = anInt1268 + (j13 >> 4 & 7);
			int l16 = anInt1269 + (j13 & 7);
			if(k15 >= 0 && l16 >= 0 && k15 < 104 && l16 < 104)
				createObject(-1, -1, j8, i11, l16, k5, plane, k15, 0);
			return;
		}
		if(j == 117)
		{
			int i3 = stream.readUnsignedByte();
			int l5 = anInt1268 + (i3 >> 4 & 7);
			int k8 = anInt1269 + (i3 & 7);
			int j11 = l5 + stream.readSignedByte();
			int k13 = k8 + stream.readSignedByte();
			int l15 = stream.readSignedWord();
			int i17 = stream.readUnsignedWord();
			int i18 = stream.readUnsignedByte() * 4;
			int l18 = stream.readUnsignedByte() * 4;
			int k19 = stream.readUnsignedWord();
			int j20 = stream.readUnsignedWord();
			int i21 = stream.readUnsignedByte();
			int j21 = stream.readUnsignedByte();
			if(l5 >= 0 && k8 >= 0 && l5 < 104 && k8 < 104 && j11 >= 0 && k13 >= 0 && j11 < 104 && k13 < 104 && i17 != 65535)
			{
				l5 = l5 * 128 + 64;
				k8 = k8 * 128 + 64;
				j11 = j11 * 128 + 64;
				k13 = k13 * 128 + 64;
				Projectile class30_sub2_sub4_sub4 = new Projectile(i21, l18, k19 + loopCycle, j20 + loopCycle, j21, plane, method42(plane, k8, l5) - i18, k8, l5, l15, i17);
				class30_sub2_sub4_sub4.method455(k19 + loopCycle, k13, method42(plane, k13, j11) - l18, j11);
				aClass19_1013.insertHead(class30_sub2_sub4_sub4);
			}
		}
	}
}