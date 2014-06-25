package com.runescape.revised.config.definitions;

import com.runescape.client.CacheArchive;
import com.runescape.client.revised.model.RSModel;

public final class IdentityKit { // loads the player?

	public static void unpackConfig(CacheArchive streamLoader)
	{
		RSBuffer stream = new RSBuffer(streamLoader.getDataForName("idk.dat"));
		length = stream.readUnsignedWord();
		if(cache == null)
			cache = new IdentityKit[length];
		for(int j = 0; j < length; j++)
		{
			if(cache[j] == null)
				cache[j] = new IdentityKit();
			cache[j].readValues(stream);
		}
	}

	private void readValues(RSBuffer stream)
	{
		do
		{
			int i = stream.readUnsignedByte();
			if(i == 0)
				return;
			if(i == 1)
				bodyPartID = stream.readUnsignedByte();
			else
			if(i == 2)
			{
				int j = stream.readUnsignedByte();
				bodyModelIDs = new int[j];
				for(int k = 0; k < j; k++)
					bodyModelIDs[k] = stream.readUnsignedWord();

			} else
			if(i == 3)
				notSelectable = true;
			else
			if(i >= 40 && i < 50)
				recolourOriginal[i - 40] = stream.readUnsignedWord();
			else
			if(i >= 50 && i < 60)
				recolourTarget[i - 50] = stream.readUnsignedWord();
			else
			if(i >= 60 && i < 70)
				headModelIDs[i - 60] = stream.readUnsignedWord();
			else
				System.out.println("Error unrecognised config code: " + i);
		} while(true);
	}

	public boolean method537()
	{
		if(bodyModelIDs == null)
			return true;
		boolean flag = true;
		for(int j = 0; j < bodyModelIDs.length; j++)
			if(!RSModel.method463(bodyModelIDs[j]))
				flag = false;

		return flag;
	}

	public RSModel method538()
	{
		if(bodyModelIDs == null)
			return null;
		RSModel aclass30_sub2_sub4_sub6s[] = new RSModel[bodyModelIDs.length];
		for(int i = 0; i < bodyModelIDs.length; i++)
			aclass30_sub2_sub4_sub6s[i] = RSModel.method462(bodyModelIDs[i]);

		RSModel model;
		if(aclass30_sub2_sub4_sub6s.length == 1)
			model = aclass30_sub2_sub4_sub6s[0];
		else
			model = new RSModel(aclass30_sub2_sub4_sub6s.length, aclass30_sub2_sub4_sub6s);
		for(int j = 0; j < 6; j++)
		{
			if(recolourOriginal[j] == 0)
				break;
			model.method476(recolourOriginal[j], recolourTarget[j]);
		}

		return model;
	}

	public boolean method539()
	{
		boolean flag1 = true;
		for(int i = 0; i < 5; i++)
			if(headModelIDs[i] != -1 && !RSModel.method463(headModelIDs[i]))
				flag1 = false;

		return flag1;
	}

	public RSModel method540()
	{
		RSModel aclass30_sub2_sub4_sub6s[] = new RSModel[5];
		int j = 0;
		for(int k = 0; k < 5; k++)
			if(headModelIDs[k] != -1)
				aclass30_sub2_sub4_sub6s[j++] = RSModel.method462(headModelIDs[k]);

		RSModel model = new RSModel(j, aclass30_sub2_sub4_sub6s);
		for(int l = 0; l < 6; l++)
		{
			if(recolourOriginal[l] == 0)
				break;
			model.method476(recolourOriginal[l], recolourTarget[l]);
		}

		return model;
	}

	private IdentityKit()
	{
		bodyPartID = -1;
		recolourOriginal = new int[6];
		recolourTarget = new int[6];
		notSelectable = false;
	}

	public static int length;
	public static IdentityKit cache[];
	public int bodyPartID;
	private int[] bodyModelIDs;
	private final int[] recolourOriginal;
	private final int[] recolourTarget;
	private final int[] headModelIDs = {
		-1, -1, -1, -1, -1
	};
	public boolean notSelectable;
}
