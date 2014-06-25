package com.runescape.revised.config.definitions;

// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
import com.runescape.client.CacheArchive;
import com.runescape.client.Class36;
import com.runescape.client.revised.config.VarBit;
import com.runescape.client.revised.model.RSModel;

public final class NPCDef
{

	public static NPCDef forID(int i)
	{
		for(int j = 0; j < 20; j++)
			if(cache[j].typeNPCID == (long)i)
				return cache[j];

		cacheIndex = (cacheIndex + 1) % 20;
		NPCDef entityDef = cache[cacheIndex] = new NPCDef();
		stream.currentOffset = streamIndices[i];
		entityDef.typeNPCID = i;
		entityDef.readValues(stream);
		return entityDef;
	}

	public RSModel method160()
	{
		if(childrenIDs != null)
		{
			NPCDef entityDef = method161();
			if(entityDef == null)
				return null;
			else
				return entityDef.method160();
		}
		if(dialogueModels == null)
			return null;
		boolean flag1 = false;
		for(int i = 0; i < dialogueModels.length; i++)
			if(!RSModel.method463(dialogueModels[i]))
				flag1 = true;

		if(flag1)
			return null;
		RSModel aclass30_sub2_sub4_sub6s[] = new RSModel[dialogueModels.length];
		for(int j = 0; j < dialogueModels.length; j++)
			aclass30_sub2_sub4_sub6s[j] = RSModel.method462(dialogueModels[j]);

		RSModel model;
		if(aclass30_sub2_sub4_sub6s.length == 1)
			model = aclass30_sub2_sub4_sub6s[0];
		else
			model = new RSModel(aclass30_sub2_sub4_sub6s.length, aclass30_sub2_sub4_sub6s);
		if(originalModelColors != null)
		{
			for(int k = 0; k < originalModelColors.length; k++)
				model.method476(originalModelColors[k], changeModelColor[k]);

		}
		return model;
	}

	public NPCDef method161()
	{
		int j = -1;
		if(varBitChild != -1)
		{
			VarBit varBit = VarBit.cache[varBitChild];
			int k = varBit.anInt648;
			int l = varBit.anInt649;
			int i1 = varBit.anInt650;
			int j1 = GameClient.anIntArray1232[i1 - l];
			j = clientInstance.variousSettings[k] >> l & j1;
		} else
		if(configChild != -1)
			j = clientInstance.variousSettings[configChild];
		if(j < 0 || j >= childrenIDs.length || childrenIDs[j] == -1)
			return null;
		else
			return forID(childrenIDs[j]);
	}

	public static void unpackConfig(CacheArchive streamLoader)
	{
		stream = new RSBuffer(streamLoader.getDataForName("npc.dat"));
		RSBuffer stream2 = new RSBuffer(streamLoader.getDataForName("npc.idx"));
		int totalNPCs = stream2.readUnsignedWord();
		streamIndices = new int[totalNPCs];
		int i = 2;
		for(int j = 0; j < totalNPCs; j++)
		{
			streamIndices[j] = i;
			i += stream2.readUnsignedWord();
		}

		cache = new NPCDef[20];
		for(int k = 0; k < 20; k++)
			cache[k] = new NPCDef();

	}

	public static void nullLoader()
	{
		mruNodes = null;
		streamIndices = null;
		cache = null;
		stream = null;
	}

	public RSModel method164(int j, int k, int ai[])
	{
		if(childrenIDs != null)
		{
			NPCDef entityDef = method161();
			if(entityDef == null)
				return null;
			else
				return entityDef.method164(j, k, ai);
		}
		RSModel model = (RSModel) mruNodes.insertFromCache(typeNPCID);
		if(model == null)
		{
			boolean flag = false;
			for(int i1 = 0; i1 < npcModel.length; i1++)
				if(!RSModel.method463(npcModel[i1]))
					flag = true;

			if(flag)
				return null;
			RSModel aclass30_sub2_sub4_sub6s[] = new RSModel[npcModel.length];
			for(int j1 = 0; j1 < npcModel.length; j1++)
				aclass30_sub2_sub4_sub6s[j1] = RSModel.method462(npcModel[j1]);

			if(aclass30_sub2_sub4_sub6s.length == 1)
				model = aclass30_sub2_sub4_sub6s[0];
			else
				model = new RSModel(aclass30_sub2_sub4_sub6s.length, aclass30_sub2_sub4_sub6s);
			if(originalModelColors != null)
			{
				for(int k1 = 0; k1 < originalModelColors.length; k1++)
					model.method476(originalModelColors[k1], changeModelColor[k1]);

			}
			model.method469();
			model.method479(64 + modelLighting, 850 + modelShadowing, -30, -50, -30, true);
			mruNodes.removeFromCache(model, typeNPCID);
		}
		RSModel model_1 = RSModel.aModel_1621;
		model_1.method464(model, Class36.method532(k) & Class36.method532(j));
		if(k != -1 && j != -1)
			model_1.method471(ai, j, k);
		else
		if(k != -1)
			model_1.method470(k);
		if(adjustVertexPointsXY != 128 || adjustVertexPointZ != 128)
			model_1.method478(adjustVertexPointsXY, adjustVertexPointsXY, adjustVertexPointZ);
		model_1.method466();
		model_1.anIntArrayArray1658 = null;
		model_1.anIntArrayArray1657 = null;
		if(npcLength == 1)
			model_1.aBoolean1659 = true;
		return model_1;
	}

	private void readValues(RSBuffer stream)
	{
		do
		{
			int i = stream.readUnsignedByte();
			if(i == 0)
				return;
			if(i == 1)
			{
				int j = stream.readUnsignedByte();
				npcModel = new int[j];
				for(int j1 = 0; j1 < j; j1++)
					npcModel[j1] = stream.readUnsignedWord();

			} else
			if(i == 2)
				name = stream.readString();
			else
			if(i == 3)
				description = stream.readBytes();
			else
			if(i == 12)
				npcLength = stream.readSignedByte();
			else
			if(i == 13)
				standAnimation = stream.readUnsignedWord();
			else
			if(i == 14)
				normalWalkAnimation = stream.readUnsignedWord();
			else
			if(i == 17)
			{
				normalWalkAnimation = stream.readUnsignedWord();
				walkingBackwardsAnimation = stream.readUnsignedWord();
				walkLeftAnimation = stream.readUnsignedWord();
				walkingRightAnimation = stream.readUnsignedWord();
			} else
			if(i >= 30 && i < 40)
			{
				if(menuActions == null)
					menuActions = new String[5];
				menuActions[i - 30] = stream.readString();
				if(menuActions[i - 30].equalsIgnoreCase("hidden"))
					menuActions[i - 30] = null;
			} else
			if(i == 40)
			{
				int k = stream.readUnsignedByte();
				originalModelColors = new int[k];
				changeModelColor = new int[k];
				for(int k1 = 0; k1 < k; k1++)
				{
					originalModelColors[k1] = stream.readUnsignedWord();
					changeModelColor[k1] = stream.readUnsignedWord();
				}

			} else
			if(i == 60)
			{
				int l = stream.readUnsignedByte();
				dialogueModels = new int[l];
				for(int l1 = 0; l1 < l; l1++)
					dialogueModels[l1] = stream.readUnsignedWord();

			} else
			if(i == 90)
				stream.readUnsignedWord();
			else
			if(i == 91)
				stream.readUnsignedWord();
			else
			if(i == 92)
				stream.readUnsignedWord();
			else
			if(i == 93)
				drawYellowDot = false;
			else
			if(i == 95)
				combatLevel = stream.readUnsignedWord();
			else
			if(i == 97)
				adjustVertexPointsXY = stream.readUnsignedWord();
			else
			if(i == 98)
				adjustVertexPointZ = stream.readUnsignedWord();
			else
			if(i == 99)
				isVisible = true;
			else
			if(i == 100)
				modelLighting = stream.readSignedByte();
			else
			if(i == 101)
				modelShadowing = stream.readSignedByte() * 5;
			else
			if(i == 102)
				npcsHeadIcon = stream.readUnsignedWord();
			else
			if(i == 103)
				getDegreesToTurn = stream.readUnsignedWord();
			else
			if(i == 106)
			{
				varBitChild = stream.readUnsignedWord();
				if(varBitChild == 65535)
					varBitChild = -1;
				configChild = stream.readUnsignedWord();
				if(configChild == 65535)
					configChild = -1;
				int i1 = stream.readUnsignedByte();
				childrenIDs = new int[i1 + 1];
				for(int i2 = 0; i2 <= i1; i2++)
				{
					childrenIDs[i2] = stream.readUnsignedWord();
					if(childrenIDs[i2] == 65535)
						childrenIDs[i2] = -1;
				}

			} else
			if(i == 107)
				disableRightClicking = false;
		} while(true);
	}

	private NPCDef()
	{
		walkingRightAnimation = -1;
		varBitChild = -1;
		walkingBackwardsAnimation = -1;
		configChild = -1;
		combatLevel = -1;
		normalWalkAnimation = -1;
		npcLength = 1;
		npcsHeadIcon = -1;
		standAnimation = -1;
		typeNPCID = -1L;
		getDegreesToTurn = 32;
		walkLeftAnimation = -1;
		disableRightClicking = true;
		adjustVertexPointZ = 128;
		drawYellowDot = true;
		adjustVertexPointsXY = 128;
		isVisible = false;
	}

	public int walkingRightAnimation; // anInt55
	private static int cacheIndex; // anInt56
	private int varBitChild; // anInt57
	public int walkingBackwardsAnimation; // anInt58
	private int configChild; // anInt59
	private static RSBuffer stream;
	public int combatLevel; // anInt61
	public String name; // aString65
	public String menuActions[]; // aString66
	public int normalWalkAnimation; // anInt67
	public byte npcLength; // aByte68
	private int[] changeModelColor; // anIntArray70
	private static int[] streamIndices;
	private int[] dialogueModels; // anIntArray73
	public int npcsHeadIcon; // anInt75
	private int[] originalModelColors; // anIntArray76
	public int standAnimation; // anInt77
	public long typeNPCID; // aLong78
	public int getDegreesToTurn; // anInt79
	private static NPCDef[] cache;
	public static GameClient clientInstance;
	public int walkLeftAnimation; // anInt83
	public boolean disableRightClicking; // aBoolean84
	private int modelLighting; // anInt85
	private int adjustVertexPointZ; // anInt86
	public boolean drawYellowDot; // aBoolean87
	public int childrenIDs[]; // anInt88 or 89
	public byte description[]; // anInt89 or 90
	private int adjustVertexPointsXY; // anInt91
	private int modelShadowing; // anInt92
	public boolean isVisible; // aBoolean93 ?????
	private int[] npcModel; // anIntArray94
	public static Cache mruNodes = new Cache(30);
}