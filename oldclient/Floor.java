package com.runescape.revised.config.definitions;

// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
import com.runescape.client.CacheArchive;

public final class Floor { // loads the floors

	public static void unpackConfig(CacheArchive streamLoader)
	{
		RSBuffer stream = new RSBuffer(streamLoader.getDataForName("flo.dat"));
		int cacheSize = stream.readUnsignedWord();
		if(cache == null)
			cache = new Floor[cacheSize];
		for(int j = 0; j < cacheSize; j++)
		{
			if(cache[j] == null)
				cache[j] = new Floor();
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
			else
			if(i == 1)
			{
				rgbColor2 = stream.read3Bytes();
				getColor(rgbColor2);
			} else
			if(i == 2)
				textureID = stream.readUnsignedByte();
			else
			if(i == 5)
				aBoolean393 = false;
			else
			if(i == 6)
				stream.readString();
			else
			if(i == 7)
			{
				int j = anInt394;
				int k = saturation;
				int l = lightness;
				int i1 = hue;
				int j1 = stream.read3Bytes();
				getColor(j1);
				anInt394 = j;
				saturation = k;
				lightness = l;
				hue = i1;
				hueDivisor = i1;
			} else
			{
				System.out.println("Error unrecognised config code: " + i);
			}
		} while(true);
	}

	private void getColor(int i)
	{
		double d = (double)(i >> 16 & 0xff) / 256D;
		double d1 = (double)(i >> 8 & 0xff) / 256D;
		double d2 = (double)(i & 0xff) / 256D;
		double d3 = d;
		if(d1 < d3)
			d3 = d1;
		if(d2 < d3)
			d3 = d2;
		double d4 = d;
		if(d1 > d4)
			d4 = d1;
		if(d2 > d4)
			d4 = d2;
		double d5 = 0.0D;
		double d6 = 0.0D;
		double d7 = (d3 + d4) / 2D;
		if(d3 != d4)
		{
			if(d7 < 0.5D)
				d6 = (d4 - d3) / (d4 + d3);
			if(d7 >= 0.5D)
				d6 = (d4 - d3) / (2D - d4 - d3);
			if(d == d4)
				d5 = (d1 - d2) / (d4 - d3);
			else
			if(d1 == d4)
				d5 = 2D + (d2 - d) / (d4 - d3);
			else
			if(d2 == d4)
				d5 = 4D + (d - d1) / (d4 - d3);
		}
		d5 /= 6D;
		anInt394 = (int)(d5 * 256D);
		saturation = (int)(d6 * 256D);
		lightness = (int)(d7 * 256D);
		if(saturation < 0)
			saturation = 0;
		else
		if(saturation > 255)
			saturation = 255;
		if(lightness < 0)
			lightness = 0;
		else
		if(lightness > 255)
			lightness = 255;
		if(d7 > 0.5D)
			hueDivisor = (int)((1.0D - d7) * d6 * 512D);
		else
			hueDivisor = (int)(d7 * d6 * 512D);
		if(hueDivisor < 1)
			hueDivisor = 1;
		hue = (int)(d5 * (double)hueDivisor);
		int k = (anInt394 + (int)(Math.random() * 16D)) - 8;
		if(k < 0)
			k = 0;
		else
		if(k > 255)
			k = 255;
		int l = (saturation + (int)(Math.random() * 48D)) - 24;
		if(l < 0)
			l = 0;
		else
		if(l > 255)
			l = 255;
		int i1 = (lightness + (int)(Math.random() * 48D)) - 24;
		if(i1 < 0)
			i1 = 0;
		else
		if(i1 > 255)
			i1 = 255;
		mapColor = getMapColor(k, l, i1);
	}

	private int getMapColor(int i, int j, int k)
	{ // method263
		if(k > 179)
			j /= 2;
		if(k > 192)
			j /= 2;
		if(k > 217)
			j /= 2;
		if(k > 243)
			j /= 2;
		return (i / 4 << 10) + (j / 32 << 7) + k / 2;
	}

	private Floor()
	{
		textureID = -1;
		aBoolean393 = true;
	}

	public static Floor cache[];
	public int rgbColor2;
	public int textureID;
	public boolean aBoolean393;
	public int anInt394;
	public int saturation;
	public int lightness;
	public int hue;
	public int hueDivisor; // 398
	public int mapColor; // 399
}
