package com.runescape.client;

import com.runescape.client.revised.animation.AbstractAnimation;
import com.runescape.client.revised.config.VarBit;
import com.runescape.client.revised.config.definitions.ObjectDef;
import com.runescape.client.revised.model.RSModel;

class GameObject extends Renderable {

	@Override
	public RSModel getRotatedModel()
	{
		int j = -1;
		if(this.aAnimation_1607 != null)
		{
			int k = GameClient.loopCycle - this.anInt1608;
			if((k > 100) && (this.aAnimation_1607.anInt356 > 0)) {
				k = 100;
			}
			while(k > this.aAnimation_1607.method258(this.anInt1599))
			{
				k -= this.aAnimation_1607.method258(this.anInt1599);
				this.anInt1599++;
				if(this.anInt1599 < this.aAnimation_1607.anInt352) {
					continue;
				}
				this.anInt1599 -= this.aAnimation_1607.anInt356;
				if((this.anInt1599 >= 0) && (this.anInt1599 < this.aAnimation_1607.anInt352)) {
					continue;
				}
				this.aAnimation_1607 = null;
				break;
			}
			this.anInt1608 = GameClient.loopCycle - k;
			if(this.aAnimation_1607 != null) {
				j = this.aAnimation_1607.anIntArray353[this.anInt1599];
			}
		}
		ObjectDef class46;
		if(this.anIntArray1600 != null) {
			class46 = this.method457();
		} else {
			class46 = ObjectDef.forID(this.anInt1610);
		}
		if(class46 == null)
		{
			return null;
		} else
		{
			return class46.method578(this.anInt1611, this.anInt1612, this.anInt1603, this.anInt1604, this.anInt1605, this.anInt1606, j);
		}
	}

	private ObjectDef method457()
	{
		int i = -1;
		if(this.anInt1601 != -1)
		{
			final VarBit varBit = VarBit.cache[this.anInt1601];
			final int k = varBit.anInt648;
			final int l = varBit.anInt649;
			final int i1 = varBit.anInt650;
			final int j1 = GameClient.anIntArray1232[i1 - l];
			i = (GameObject.clientInstance.variousSettings[k] >> l) & j1;
		} else
			if(this.anInt1602 != -1) {
				i = GameObject.clientInstance.variousSettings[this.anInt1602];
			}
		if((i < 0) || (i >= this.anIntArray1600.length) || (this.anIntArray1600[i] == -1)) {
			return null;
		} else {
			return ObjectDef.forID(this.anIntArray1600[i]);
		}
	}

	public GameObject(final int i, final int j, final int k, final int l, final int i1, final int j1,
			final int k1, final int l1, final boolean flag)
	{
		this.anInt1610 = i;
		this.anInt1611 = k;
		this.anInt1612 = j;
		this.anInt1603 = j1;
		this.anInt1604 = l;
		this.anInt1605 = i1;
		this.anInt1606 = k1;
		if(l1 != -1)
		{
			this.aAnimation_1607 = AbstractAnimation.anims[l1];
			this.anInt1599 = 0;
			this.anInt1608 = GameClient.loopCycle;
			if(flag && (this.aAnimation_1607.anInt356 != -1))
			{
				this.anInt1599 = (int)(Math.random() * (double) this.aAnimation_1607.anInt352);
				this.anInt1608 -= (int)(Math.random() * (double) this.aAnimation_1607.method258(this.anInt1599));
			}
		}
		final ObjectDef class46 = ObjectDef.forID(this.anInt1610);
		this.anInt1601 = class46.variable_id_bitfield;
		this.anInt1602 = class46.variable_ID;
		this.anIntArray1600 = class46.childrenIDs;
	}

	private int anInt1599;
	private final int[] anIntArray1600;
	private final int anInt1601;
	private final int anInt1602;
	private final int anInt1603;
	private final int anInt1604;
	private final int anInt1605;
	private final int anInt1606;
	private AbstractAnimation aAnimation_1607;
	private int anInt1608;
	public static GameClient clientInstance;
	private final int anInt1610;
	private final int anInt1611;
	private final int anInt1612;
}