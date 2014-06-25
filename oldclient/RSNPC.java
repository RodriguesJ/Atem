package com.runescape.revised.model.entity.npc;

// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
import com.runescape.client.Class36;
import com.runescape.client.revised.config.definitions.NPCDef;
import com.runescape.client.revised.model.RSModel;
import com.runescape.client.revised.model.entity.RSEntity;

public final class RSNPC extends RSEntity
{

	private RSModel method450()
	{
		if(super.anim >= 0 && super.anInt1529 == 0)
		{
			int k = AbstractAnimation.anims[super.anim].anIntArray353[super.anInt1527];
			int i1 = -1;
			if(super.anInt1517 >= 0 && super.anInt1517 != super.anInt1511)
				i1 = AbstractAnimation.anims[super.anInt1517].anIntArray353[super.anInt1518];
			return desc.method164(i1, k, AbstractAnimation.anims[super.anim].anIntArray357);
		}
		int l = -1;
		if(super.anInt1517 >= 0)
			l = AbstractAnimation.anims[super.anInt1517].anIntArray353[super.anInt1518];
		return desc.method164(-1, l, null);
	}

	public RSModel getRotatedModel()
	{
		if(desc == null)
			return null;
		RSModel model = method450();
		if(model == null)
			return null;
		super.height = model.modelHeight;
		if(super.anInt1520 != -1 && super.anInt1521 != -1)
		{
			AbstractGraphic spotAnim = AbstractGraphic.cache[super.anInt1520];
			RSModel model_1 = spotAnim.getModel();
			if(model_1 != null)
			{
				int j = spotAnim.aAnimation_407.anIntArray353[super.anInt1521];
				RSModel model_2 = new RSModel(true, Class36.method532(j), false, model_1);
				model_2.method475(0, -super.anInt1524, 0);
				model_2.method469();
				model_2.method470(j);
				model_2.anIntArrayArray1658 = null;
				model_2.anIntArrayArray1657 = null;
				if(spotAnim.anInt410 != 128 || spotAnim.anInt411 != 128)
					model_2.method478(spotAnim.anInt410, spotAnim.anInt410, spotAnim.anInt411);
				model_2.method479(64 + spotAnim.anInt413, 850 + spotAnim.anInt414, -30, -50, -30, true);
				RSModel aModel[] = {
						model, model_2
				};
				model = new RSModel(aModel);
			}
		}
		if(desc.npcLength == 1)
			model.aBoolean1659 = true;
		return model;
	}

	public boolean isVisible()
	{
		return desc != null;
	}

	public RSNPC()
	{
	}

	public NPCDef desc;
}