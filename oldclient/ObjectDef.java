package com.runescape.revised.config.definitions;

// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
import com.runescape.client.CacheArchive;
import com.runescape.client.Class36;
import com.runescape.client.revised.config.VarBit;
import com.runescape.client.revised.model.RSModel;

public final class ObjectDef // load objects' locations.dat file
{

	public static ObjectDef forID(int i)
	{
		for(int j = 0; j < 20; j++)
			if(cache[j].type == i)
				return cache[j];

		cacheIndex = (cacheIndex + 1) % 20;
		ObjectDef class46 = cache[cacheIndex];
		stream.currentOffset = streamIndices[i];
		class46.type = i;
		class46.setDefaults();
		class46.readValues(stream);
		return class46;
	}

	private void setDefaults()
	{
		objectModelIDs = null;
		object_model_type = null;
		name = null;
		description = null;
		modifiedModelColors = null;
		originalModelColors = null;
		width = 1;
		height = 1;
		isUnwalkable = true;
		aBoolean757 = true;
		hasActions = false;
		adjustToTerrain = false;
		nonFlatShading = false;
		aBoolean764 = false;
		animation_id = -1;
		anInt775 = 16;
		brightness = 0;
		contrast = 0;
		actions = null;
		mapFunctionID = -1;
		mapSceneID = -1;
		aBoolean751 = false;
		aBoolean779 = true;
		modelSizeX = 128;
		modelSizeH = 128;
		modelSizeY = 128;
		anInt768 = 0;
		offsetX = 0;
		offsetH = 0;
		offsetY = 0;
		aBoolean736 = false;
		isSolidObject = false;
		anInt760 = -1;
		variable_id_bitfield = -1;
		variable_ID = -1;
		childrenIDs = null;
	}

	public void method574(OnDemandFetcher class42_sub1)
	{
		if(objectModelIDs == null)
			return;
		for(int j = 0; j < objectModelIDs.length; j++)
			class42_sub1.method560(objectModelIDs[j] & 0xffff, 0);
	}

	public static void nullLoader()
	{
		modelCache = null;
		modelCache2 = null;
		streamIndices = null;
		cache = null;
stream = null;
	}

	public static void unpackConfig(CacheArchive streamLoader)
	{
		stream = new RSBuffer(streamLoader.getDataForName("loc.dat"));
		RSBuffer stream = new RSBuffer(streamLoader.getDataForName("loc.idx"));
		int totalObjects = stream.readUnsignedWord();
		streamIndices = new int[totalObjects];
		int i = 2;
		for(int j = 0; j < totalObjects; j++)
		{
			streamIndices[j] = i;
			i += stream.readUnsignedWord();
		}

		cache = new ObjectDef[20];
		for(int k = 0; k < 20; k++)
			cache[k] = new ObjectDef();

	}

	public boolean method577(int i)
	{
		if(object_model_type == null)
		{
			if(objectModelIDs == null)
				return true;
			if(i != 10)
				return true;
			boolean flag1 = true;
			for(int k = 0; k < objectModelIDs.length; k++)
				flag1 &= RSModel.method463(objectModelIDs[k] & 0xffff);

			return flag1;
		}
		for(int j = 0; j < object_model_type.length; j++)
			if(object_model_type[j] == i)
				return RSModel.method463(objectModelIDs[j] & 0xffff);

		return true;
	}

	public RSModel method578(int i, int j, int k, int l, int i1, int j1, int k1)
	{
		RSModel model = method581(i, k1, j);
		if(model == null)
			return null;
		if(adjustToTerrain || nonFlatShading)
			model = new RSModel(adjustToTerrain, nonFlatShading, model);
		if(adjustToTerrain)
		{
			int l1 = (k + l + i1 + j1) / 4;
			for(int i2 = 0; i2 < model.anInt1626; i2++)
			{
				int j2 = model.anIntArray1627[i2];
				int k2 = model.anIntArray1629[i2];
				int l2 = k + ((l - k) * (j2 + 64)) / 128;
				int i3 = j1 + ((i1 - j1) * (j2 + 64)) / 128;
				int j3 = l2 + ((i3 - l2) * (k2 + 64)) / 128;
				model.anIntArray1628[i2] += j3 - l1;
			}

			model.method467();
		}
		return model;
	}

	public boolean method579()
	{
		if(objectModelIDs == null)
			return true;
		boolean flag1 = true;
		for(int i = 0; i < objectModelIDs.length; i++)
			flag1 &= RSModel.method463(objectModelIDs[i] & 0xffff);
			return flag1;
	}

	public ObjectDef method580()
	{
		int i = -1;
		if(variable_id_bitfield != -1)
		{
			VarBit varBit = VarBit.cache[variable_id_bitfield];
			int j = varBit.anInt648;
			int k = varBit.anInt649;
			int l = varBit.anInt650;
			int i1 = GameClient.anIntArray1232[l - k];
			i = clientInstance.variousSettings[j] >> k & i1;
		} else
		if(variable_ID != -1)
			i = clientInstance.variousSettings[variable_ID];
		if(i < 0 || i >= childrenIDs.length || childrenIDs[i] == -1)
			return null;
		else
			return forID(childrenIDs[i]);
	}

	private RSModel method581(int j, int k, int l)
	{
		RSModel model = null;
		long l1;
		if(object_model_type == null)
		{
			if(j != 10)
				return null;
			l1 = (long)((type << 6) + l) + ((long)(k + 1) << 32);
			RSModel model_1 = (RSModel) modelCache2.insertFromCache(l1);
			if(model_1 != null)
				return model_1;
			if(objectModelIDs == null)
				return null;
			boolean flag1 = aBoolean751 ^ (l > 3);
			int k1 = objectModelIDs.length;
			for(int i2 = 0; i2 < k1; i2++)
			{
				int l2 = objectModelIDs[i2];
				if(flag1)
					l2 += 0x10000;
				model = (RSModel) modelCache.insertFromCache(l2);
				if(model == null)
				{
					model = RSModel.method462(l2 & 0xffff);
					if(model == null)
						return null;
					if(flag1)
						model.method477();
					modelCache.removeFromCache(model, l2);
				}
				if(k1 > 1)
					modelParts[i2] = model;
			}

			if(k1 > 1)
				model = new RSModel(k1, modelParts);
		} else
		{
			int i1 = -1;
			for(int j1 = 0; j1 < object_model_type.length; j1++)
			{
				if(object_model_type[j1] != j)
					continue;
				i1 = j1;
				break;
			}

			if(i1 == -1)
				return null;
			l1 = (long)((type << 6) + (i1 << 3) + l) + ((long)(k + 1) << 32);
			RSModel model_2 = (RSModel) modelCache2.insertFromCache(l1);
			if(model_2 != null)
				return model_2;
			int j2 = objectModelIDs[i1];
			boolean flag3 = aBoolean751 ^ (l > 3);
			if(flag3)
				j2 += 0x10000;
			model = (RSModel) modelCache.insertFromCache(j2);
			if(model == null)
			{
				model = RSModel.method462(j2 & 0xffff);
				if(model == null)
					return null;
				if(flag3)
					model.method477();
				modelCache.removeFromCache(model, j2);
			}
		}
		boolean flag;
		flag = modelSizeX != 128 || modelSizeH != 128 || modelSizeY != 128;
		boolean flag2;
		flag2 = offsetX != 0 || offsetH != 0 || offsetY != 0;
		RSModel model_3 = new RSModel(modifiedModelColors == null, Class36.method532(k), l == 0 && k == -1 && !flag && !flag2, model);
		if(k != -1)
		{
			model_3.method469();
			model_3.method470(k);
			model_3.anIntArrayArray1658 = null;
			model_3.anIntArrayArray1657 = null;
		}
		while(l-- > 0) 
			model_3.method473();
		if(modifiedModelColors != null)
		{
			for(int k2 = 0; k2 < modifiedModelColors.length; k2++)
				model_3.method476(modifiedModelColors[k2], originalModelColors[k2]);

		}
		if(flag)
			model_3.method478(modelSizeX, modelSizeY, modelSizeH);
		if(flag2)
			model_3.method475(offsetX, offsetH, offsetY);
		model_3.method479(64 + brightness, 768 + contrast * 5, -50, -10, -50, !nonFlatShading);
		if(anInt760 == 1)
			model_3.anInt1654 = model_3.modelHeight;
		modelCache2.removeFromCache(model_3, l1);
		return model_3;
	}

	private void readValues(RSBuffer stream)
	{
		int i = -1;
label0:
		do
		{
			int j;
			do
			{
				j = stream.readUnsignedByte();
				if(j == 0)
					break label0;
				if(j == 1)
				{
					int k = stream.readUnsignedByte();
					if(k > 0)
						if(objectModelIDs == null || lowMem)
						{
							object_model_type = new int[k];
							objectModelIDs = new int[k];
							for(int k1 = 0; k1 < k; k1++)
							{
								objectModelIDs[k1] = stream.readUnsignedWord();
								object_model_type[k1] = stream.readUnsignedByte();
							}

						} else
						{
							stream.currentOffset += k * 3;
						}
				} else
				if(j == 2)
					name = stream.readString();
				else
				if(j == 3)
					description = stream.readBytes();
				else
				if(j == 5)
				{
					int l = stream.readUnsignedByte();
					if(l > 0)
						if(objectModelIDs == null || lowMem)
						{
							object_model_type = null;
							objectModelIDs = new int[l];
							for(int l1 = 0; l1 < l; l1++)
								objectModelIDs[l1] = stream.readUnsignedWord();

						} else
						{
							stream.currentOffset += l * 2;
						}
				} else
				if(j == 14)
					width = stream.readUnsignedByte();
				else
				if(j == 15)
					height = stream.readUnsignedByte();
				else
				if(j == 17)
					isUnwalkable = false;
				else
				if(j == 18)
					aBoolean757 = false;
				else
				if(j == 19)
				{
					i = stream.readUnsignedByte();
					if(i == 1)
						hasActions = true;
				} else
				if(j == 21)
					adjustToTerrain = true;
				else
				if(j == 22)
					nonFlatShading = true;
				else
				if(j == 23)
					aBoolean764 = true;
				else
				if(j == 24)
				{
					animation_id = stream.readUnsignedWord();
					if(animation_id == 65535)
						animation_id = -1;
				} else
				if(j == 28)
					anInt775 = stream.readUnsignedByte();
				else
				if(j == 29)
					brightness = stream.readSignedByte();
				else
				if(j == 39)
					contrast = stream.readSignedByte();
				else
				if(j >= 30 && j < 39)
				{
					if(actions == null)
						actions = new String[5];
					actions[j - 30] = stream.readString();
					if(actions[j - 30].equalsIgnoreCase("hidden"))
						actions[j - 30] = null;
				} else
				if(j == 40)
				{
					int i1 = stream.readUnsignedByte();
					modifiedModelColors = new int[i1];
					originalModelColors = new int[i1];
					for(int i2 = 0; i2 < i1; i2++)
					{
						modifiedModelColors[i2] = stream.readUnsignedWord();
						originalModelColors[i2] = stream.readUnsignedWord();
					}

				} else
				if(j == 60)
					mapFunctionID = stream.readUnsignedWord();
				else
				if(j == 62)
					aBoolean751 = true;
				else
				if(j == 64)
					aBoolean779 = false;
				else
				if(j == 65)
					modelSizeX = stream.readUnsignedWord();
				else
				if(j == 66)
					modelSizeH = stream.readUnsignedWord();
				else
				if(j == 67)
					modelSizeY = stream.readUnsignedWord();
				else
				if(j == 68)
					mapSceneID = stream.readUnsignedWord();
				else
				if(j == 69)
					anInt768 = stream.readUnsignedByte();
				else
				if(j == 70)
					offsetX = stream.readSignedWord();
				else
				if(j == 71)
					offsetH = stream.readSignedWord();
				else
				if(j == 72)
					offsetY = stream.readSignedWord();
				else
				if(j == 73)
					aBoolean736 = true;
				else
				if(j == 74)
				{
					isSolidObject = true;
				} else
				{
					if(j != 75)
						continue;
					anInt760 = stream.readUnsignedByte();
				}
				continue label0;
			} while(j != 77);
			variable_id_bitfield = stream.readUnsignedWord();
			if(variable_id_bitfield == 65535)
				variable_id_bitfield = -1;
			variable_ID = stream.readUnsignedWord();
			if(variable_ID == 65535)
				variable_ID = -1;
			int j1 = stream.readUnsignedByte();
			childrenIDs = new int[j1 + 1];
			for(int j2 = 0; j2 <= j1; j2++)
			{
				childrenIDs[j2] = stream.readUnsignedWord();
				if(childrenIDs[j2] == 65535)
					childrenIDs[j2] = -1;
			}

		} while(true);
		if(i == -1)
		{
			hasActions = objectModelIDs != null && (object_model_type == null || object_model_type[0] == 10);
			if(actions != null)
				hasActions = true;
		}
		if(isSolidObject)
		{
			isUnwalkable = false;
			aBoolean757 = false;
		}
		if(anInt760 == -1)
			anInt760 = isUnwalkable ? 1 : 0;
	}

	private ObjectDef()
	{
		type = -1;
	}

	public boolean aBoolean736;
	private byte brightness;
	private int offsetX;
	public String name;
	private int modelSizeY;
	private static final RSModel[] modelParts = new RSModel[4];
	private byte contrast;
	public int width;
	private int offsetH;
	public int mapFunctionID;
	private int[] originalModelColors;
	private int modelSizeX;
	public int variable_ID;
	private boolean aBoolean751;
	public static boolean lowMem;
	private static RSBuffer stream;
	public int type;
	private static int[] streamIndices;
	public boolean aBoolean757;
	public int mapSceneID;
	public int childrenIDs[];
	private int anInt760;
	public int height;
	public boolean adjustToTerrain;
	public boolean aBoolean764;
	public static GameClient clientInstance;
	private boolean isSolidObject;
	public boolean isUnwalkable;
	public int anInt768;
	private boolean nonFlatShading;
	private static int cacheIndex;
	private int modelSizeH;
	private int[] objectModelIDs;
	public int variable_id_bitfield;
	public int anInt775;
	private int[] object_model_type;
	public byte description[];
	public boolean hasActions;
	public boolean aBoolean779;
	public static Cache modelCache2 = new Cache(30);
	public int animation_id;
	private static ObjectDef[] cache;
	private int offsetY;
	private int[] modifiedModelColors;
	public static Cache modelCache = new Cache(500);
	public String actions[];

}
