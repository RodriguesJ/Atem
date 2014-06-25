package com.runescape.revised.client;

public class Turn {

	private void getDegreesToTurn(Entity entity)
	{
		entity.anInt1517 = entity.anInt1511;
		if(entity.smallXYIndex == 0)
		{
			entity.anInt1503 = 0;
			return;
		}
		if(entity.anim != -1 && entity.anInt1529 == 0)
		{
			AbstractAnimation animation = AbstractAnimation.anims[entity.anim];
			if(entity.anInt1542 > 0 && animation.anInt363 == 0)
			{
				entity.anInt1503++;
				return;
			}
			if(entity.anInt1542 <= 0 && animation.anInt364 == 0)
			{
				entity.anInt1503++;
				return;
			}
		}
		int i = entity.x;
		int j = entity.y;
		int k = entity.smallX[entity.smallXYIndex - 1] * 128 + entity.anInt1540 * 64;
		int l = entity.smallY[entity.smallXYIndex - 1] * 128 + entity.anInt1540 * 64;
		if(k - i > 256 || k - i < -256 || l - j > 256 || l - j < -256)
		{
			entity.x = k;
			entity.y = l;
			return;
		}
		if(i < k)
		{
			if(j < l)
				entity.turnDirection = 1280;
			else
			if(j > l)
				entity.turnDirection = 1792;
			else
				entity.turnDirection = 1536;
		} else
		if(i > k)
		{
			if(j < l)
				entity.turnDirection = 768;
			else
			if(j > l)
				entity.turnDirection = 256;
			else
				entity.turnDirection = 512;
		} else
		if(j < l)
			entity.turnDirection = 1024;
		else
			entity.turnDirection = 0;
		int i1 = entity.turnDirection - entity.anInt1552 & 0x7ff;
		if(i1 > 1024)
			i1 -= 2048;
		int j1 = entity.anInt1555;
		if(i1 >= -256 && i1 <= 256)
			j1 = entity.anInt1554;
		else
		if(i1 >= 256 && i1 < 768)
			j1 = entity.anInt1557;
		else
		if(i1 >= -768 && i1 <= -256)
			j1 = entity.anInt1556;
		if(j1 == -1)
			j1 = entity.anInt1554;
		entity.anInt1517 = j1;
		int k1 = 4;
		if(entity.anInt1552 != entity.turnDirection && entity.interactingEntity == -1 && entity.anInt1504 != 0)
			k1 = 2;
		if(entity.smallXYIndex > 2)
			k1 = 6;
		if(entity.smallXYIndex > 3)
			k1 = 8;
		if(entity.anInt1503 > 0 && entity.smallXYIndex > 1)
		{
			k1 = 8;
			entity.anInt1503--;
		}
		if(entity.aBooleanArray1553[entity.smallXYIndex - 1])
			k1 <<= 1;
		if(k1 >= 8 && entity.anInt1517 == entity.anInt1554 && entity.anInt1505 != -1)
			entity.anInt1517 = entity.anInt1505;
		if(i < k)
		{
			entity.x += k1;
			if(entity.x > k)
				entity.x = k;
		} else
		if(i > k)
		{
			entity.x -= k1;
			if(entity.x < k)
				entity.x = k;
		}
		if(j < l)
		{
			entity.y += k1;
			if(entity.y > l)
				entity.y = l;
		} else
		if(j > l)
		{
			entity.y -= k1;
			if(entity.y < l)
				entity.y = l;
		}
		if(entity.x == k && entity.y == l)
		{
			entity.smallXYIndex--;
			if(entity.anInt1542 > 0)
				entity.anInt1542--;
		}
	}
}