package com.runescape.revised.client;


public class Emote {

	public void appendEmote(Entity entity)
	{
		entity.aBoolean1541 = false;
		if(entity.anInt1517 != -1)
		{
			AbstractAnimation animation = AbstractAnimation.anims[entity.anInt1517];
			entity.anInt1519++;
			if(entity.anInt1518 < animation.anInt352 && entity.anInt1519 > animation.method258(entity.anInt1518))
			{
				entity.anInt1519 = 0;
				entity.anInt1518++;
			}
			if(entity.anInt1518 >= animation.anInt352)
			{
				entity.anInt1519 = 0;
				entity.anInt1518 = 0;
			}
		}
		if(entity.anInt1520 != -1 && GameClient.loopCycle >= entity.anInt1523)
		{
			if(entity.anInt1521 < 0)
				entity.anInt1521 = 0;
			AbstractAnimation animation_1 = AbstractGraphic.cache[entity.anInt1520].aAnimation_407;
			for(entity.anInt1522++; entity.anInt1521 < animation_1.anInt352 && entity.anInt1522 > animation_1.method258(entity.anInt1521); entity.anInt1521++)
				entity.anInt1522 -= animation_1.method258(entity.anInt1521);

			if(entity.anInt1521 >= animation_1.anInt352 && (entity.anInt1521 < 0 || entity.anInt1521 >= animation_1.anInt352))
				entity.anInt1520 = -1;
		}
		if(entity.anim != -1 && entity.anInt1529 <= 1)
		{
			AbstractAnimation animation_2 = AbstractAnimation.anims[entity.anim];
			if(animation_2.anInt363 == 1 && entity.anInt1542 > 0 && entity.anInt1547 <= GameClient.loopCycle && entity.anInt1548 < GameClient.loopCycle)
			{
				entity.anInt1529 = 1;
				return;
			}
		}
		if(entity.anim != -1 && entity.anInt1529 == 0)
		{
			AbstractAnimation animation_3 = AbstractAnimation.anims[entity.anim];
			for(entity.anInt1528++; entity.anInt1527 < animation_3.anInt352 && entity.anInt1528 > animation_3.method258(entity.anInt1527); entity.anInt1527++)
				entity.anInt1528 -= animation_3.method258(entity.anInt1527);

			if(entity.anInt1527 >= animation_3.anInt352)
			{
				entity.anInt1527 -= animation_3.anInt356;
				entity.anInt1530++;
				if(entity.anInt1530 >= animation_3.anInt362)
					entity.anim = -1;
				if(entity.anInt1527 < 0 || entity.anInt1527 >= animation_3.anInt352)
					entity.anim = -1;
			}
			entity.aBoolean1541 = animation_3.aBoolean358;
		}
		if(entity.anInt1529 > 0)
			entity.anInt1529--;
	}
}