package com.runescape.revised.client;

import com.runescape.client.revised.config.definitions.ItemDef;

public class Item {

	private void spawnGroundItem(int i, int j)
	{
		Deque class19 = groundArray[plane][i][j];
		if(class19 == null)
		{
			worldController.method295(plane, i, j);
			return;
		}
		int k = 0xfa0a1f01;
		Object obj = null;
		for(Item item = (Item)class19.reverseGetFirst(); item != null; item = (Item)class19.reverseGetNext())
		{
			ItemDef itemDef = ItemDef.forID(item.ID);
			int l = itemDef.value;
			if(itemDef.stackable)
				l *= item.anInt1559 + 1;
//	notifyItemSpawn(item, i + baseX, j + baseY);
	
			if(l > k)
			{
				k = l;
				obj = item;
			}
		}

		class19.insertTail(((Node) (obj)));
		Object obj1 = null;
		Object obj2 = null;
		for(Item class30_sub2_sub4_sub2_1 = (Item)class19.reverseGetFirst(); class30_sub2_sub4_sub2_1 != null; class30_sub2_sub4_sub2_1 = (Item)class19.reverseGetNext())
		{
			if(class30_sub2_sub4_sub2_1.ID != ((Item) (obj)).ID && obj1 == null)
				obj1 = class30_sub2_sub4_sub2_1;
			if(class30_sub2_sub4_sub2_1.ID != ((Item) (obj)).ID && class30_sub2_sub4_sub2_1.ID != ((Item) (obj1)).ID && obj2 == null)
				obj2 = class30_sub2_sub4_sub2_1;
		}

		int i1 = i + (j << 7) + 0x60000000;
		worldController.method281(i, i1, ((Renderable) (obj1)), method42(plane, j * 128 + 64, i * 128 + 64), ((Renderable) (obj2)), ((Renderable) (obj)), plane, j);
	}
}