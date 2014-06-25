package com.runescape.client;

import com.runescape.client.revised.graphics.image.DrawingArea;
import com.runescape.client.revised.media.IndexedImage;

public class Rasterizer extends DrawingArea {

	public static void nullLoader()
	{
		Rasterizer.anIntArray1468 = null;
		Rasterizer.anIntArray1468 = null;
		Rasterizer.anIntArray1470 = null;
		Rasterizer.anIntArray1471 = null;
		Rasterizer.anIntArray1472 = null;
		Rasterizer.textureSprite = null;
		Rasterizer.aBooleanArray1475 = null;
		Rasterizer.anIntArray1476 = null;
		Rasterizer.anIntArrayArray1478 = null;
		Rasterizer.anIntArrayArray1479 = null;
		Rasterizer.textureID = null;
		Rasterizer.hslToRgbLookupTable = null;
		Rasterizer.anIntArrayArray1483 = null;
	}

	public static void method364()
	{
		Rasterizer.anIntArray1472 = new int[DrawingArea.height];
		for(int j = 0; j < DrawingArea.height; j++) {
			Rasterizer.anIntArray1472[j] = DrawingArea.width * j;
		}

		Rasterizer.textureInt1 = DrawingArea.width / 2;
		Rasterizer.textureInt2 = DrawingArea.height / 2;
	}

	public static void method365(final int j, final int k)
	{
		Rasterizer.anIntArray1472 = new int[k];
		for(int l = 0; l < k; l++) {
			Rasterizer.anIntArray1472[l] = j * l;
		}

		Rasterizer.textureInt1 = j / 2;
		Rasterizer.textureInt2 = k / 2;
	}

	public static void method366()
	{
		Rasterizer.anIntArrayArray1478 = null;
		for(int j = 0; j < 50; j++) {
			Rasterizer.anIntArrayArray1479[j] = null;
		}

	}

	public static void method367()
	{
		if(Rasterizer.anIntArrayArray1478 == null)
		{
			Rasterizer.anInt1477 = 20;//was parameter
			if(Rasterizer.lowMem) {
				Rasterizer.anIntArrayArray1478 = new int[Rasterizer.anInt1477][16384];
			} else {
				Rasterizer.anIntArrayArray1478 = new int[Rasterizer.anInt1477][0x10000];
			}
			for(int k = 0; k < 50; k++) {
				Rasterizer.anIntArrayArray1479[k] = null;
			}

		}
	}

	public static void method368(final CacheArchive streamLoader)
	{
		Rasterizer.anInt1473 = 0;
		for(int j = 0; j < 50; j++) {
			try
			{
				Rasterizer.textureSprite[j] = new IndexedImage(streamLoader, String.valueOf(j), 0);
				if(Rasterizer.lowMem && (Rasterizer.textureSprite[j].imageWidth == 128)) {
					Rasterizer.textureSprite[j].load128x128Image();
				} else {
					Rasterizer.textureSprite[j].load64x64Image();
				}
				Rasterizer.anInt1473++;
			}
			catch(final Exception _ex) { }
		}

	}

	public static int getAverageRgbColourForTexture(final int i)
	{
		if(Rasterizer.anIntArray1476[i] != 0) {
			return Rasterizer.anIntArray1476[i];
		}
		int k = 0;
		int l = 0;
		int i1 = 0;
		final int j1 = Rasterizer.anIntArrayArray1483[i].length;
		for(int k1 = 0; k1 < j1; k1++)
		{
			k += (Rasterizer.anIntArrayArray1483[i][k1] >> 16) & 0xff;
			l += (Rasterizer.anIntArrayArray1483[i][k1] >> 8) & 0xff;
			i1 += Rasterizer.anIntArrayArray1483[i][k1] & 0xff;
		}

		int l1 = ((k / j1) << 16) + ((l / j1) << 8) + (i1 / j1);
		l1 = Rasterizer.method373(l1, 1.3999999999999999D);
		if(l1 == 0) {
			l1 = 1;
		}
		Rasterizer.anIntArray1476[i] = l1;
		return l1;
	}

	public static void method370(final int i)
	{
		if(Rasterizer.anIntArrayArray1479[i] == null) {
			return;
		}
		Rasterizer.anIntArrayArray1478[Rasterizer.anInt1477++] = Rasterizer.anIntArrayArray1479[i];
		Rasterizer.anIntArrayArray1479[i] = null;
	}

	private static int[] method371(final int i)
	{
		Rasterizer.textureID[i] = Rasterizer.anInt1481++;
		if(Rasterizer.anIntArrayArray1479[i] != null) {
			return Rasterizer.anIntArrayArray1479[i];
		}
		int ai[];
		if(Rasterizer.anInt1477 > 0)
		{
			ai = Rasterizer.anIntArrayArray1478[--Rasterizer.anInt1477];
			Rasterizer.anIntArrayArray1478[Rasterizer.anInt1477] = null;
		} else
		{
			int j = 0;
			int k = -1;
			for(int l = 0; l < Rasterizer.anInt1473; l++) {
				if((Rasterizer.anIntArrayArray1479[l] != null) && ((Rasterizer.textureID[l] < j) || (k == -1)))
				{
					j = Rasterizer.textureID[l];
					k = l;
				}
			}

			ai = Rasterizer.anIntArrayArray1479[k];
			Rasterizer.anIntArrayArray1479[k] = null;
		}
		Rasterizer.anIntArrayArray1479[i] = ai;
		final IndexedImage background = Rasterizer.textureSprite[i];
		final int ai1[] = Rasterizer.anIntArrayArray1483[i];
		if(Rasterizer.lowMem)
		{
			Rasterizer.aBooleanArray1475[i] = false;
			for(int i1 = 0; i1 < 4096; i1++)
			{
				final int i2 = ai[i1] = ai1[background.myPixels[i1]] & 0xf8f8ff;
				if(i2 == 0) {
					Rasterizer.aBooleanArray1475[i] = true;
				}
				ai[4096 + i1] = (i2 - (i2 >>> 3)) & 0xf8f8ff;
				ai[8192 + i1] = (i2 - (i2 >>> 2)) & 0xf8f8ff;
				ai[12288 + i1] = (i2 - (i2 >>> 2) - (i2 >>> 3)) & 0xf8f8ff;
			}

		} else
		{
			if(background.myWidth == 64)
			{
				for(int j1 = 0; j1 < 128; j1++)
				{
					for(int j2 = 0; j2 < 128; j2++) {
						ai[j2 + (j1 << 7)] = ai1[background.myPixels[(j2 >> 1) + ((j1 >> 1) << 6)]];
					}

				}

			} else
			{
				for(int k1 = 0; k1 < 16384; k1++) {
					ai[k1] = ai1[background.myPixels[k1]];
				}

			}
			Rasterizer.aBooleanArray1475[i] = false;
			for(int l1 = 0; l1 < 16384; l1++)
			{
				ai[l1] &= 0xf8f8ff;
				final int k2 = ai[l1];
				if(k2 == 0) {
					Rasterizer.aBooleanArray1475[i] = true;
				}
				ai[16384 + l1] = (k2 - (k2 >>> 3)) & 0xf8f8ff;
				ai[32768 + l1] = (k2 - (k2 >>> 2)) & 0xf8f8ff;
				ai[49152 + l1] = (k2 - (k2 >>> 2) - (k2 >>> 3)) & 0xf8f8ff;
			}

		}
		return ai;
	}

	public static void method372(double d)
	{
		d += (Math.random() * 0.029999999999999999D) - 0.014999999999999999D;
		int j = 0;
		for(int k = 0; k < 512; k++)
		{
			final double d1 = ((k / 8) / 64D) + 0.0078125D;
			final double d2 = ((k & 7) / 8D) + 0.0625D;
			for(int k1 = 0; k1 < 128; k1++)
			{
				final double d3 = k1 / 128D;
				double d4 = d3;
				double d5 = d3;
				double d6 = d3;
				if(d2 != 0.0D)
				{
					double d7;
					if(d3 < 0.5D) {
						d7 = d3 * (1.0D + d2);
					} else {
						d7 = (d3 + d2) - (d3 * d2);
					}
					final double d8 = (2D * d3) - d7;
					double d9 = d1 + 0.33333333333333331D;
					if(d9 > 1.0D) {
						d9--;
					}
					final double d10 = d1;
					double d11 = d1 - 0.33333333333333331D;
					if(d11 < 0.0D) {
						d11++;
					}
					if((6D * d9) < 1.0D) {
						d4 = d8 + ((d7 - d8) * 6D * d9);
					} else
						if((2D * d9) < 1.0D) {
							d4 = d7;
						} else
							if((3D * d9) < 2D) {
								d4 = d8 + ((d7 - d8) * (0.66666666666666663D - d9) * 6D);
							} else {
								d4 = d8;
							}
					if((6D * d10) < 1.0D) {
						d5 = d8 + ((d7 - d8) * 6D * d10);
					} else
						if((2D * d10) < 1.0D) {
							d5 = d7;
						} else
							if((3D * d10) < 2D) {
								d5 = d8 + ((d7 - d8) * (0.66666666666666663D - d10) * 6D);
							} else {
								d5 = d8;
							}
					if((6D * d11) < 1.0D) {
						d6 = d8 + ((d7 - d8) * 6D * d11);
					} else
						if((2D * d11) < 1.0D) {
							d6 = d7;
						} else
							if((3D * d11) < 2D) {
								d6 = d8 + ((d7 - d8) * (0.66666666666666663D - d11) * 6D);
							} else {
								d6 = d8;
							}
				}
				final int l1 = (int)(d4 * 256D);
				final int i2 = (int)(d5 * 256D);
				final int j2 = (int)(d6 * 256D);
				int k2 = (l1 << 16) + (i2 << 8) + j2;
				k2 = Rasterizer.method373(k2, d);
				if(k2 == 0) {
					k2 = 1;
				}
				Rasterizer.hslToRgbLookupTable[j++] = k2;
			}

		}

		for(int l = 0; l < 50; l++) {
			if(Rasterizer.textureSprite[l] != null)
			{
				final int ai[] = Rasterizer.textureSprite[l].imageColors;
				Rasterizer.anIntArrayArray1483[l] = new int[ai.length];
				for(int j1 = 0; j1 < ai.length; j1++)
				{
					Rasterizer.anIntArrayArray1483[l][j1] = Rasterizer.method373(ai[j1], d);
					if(((Rasterizer.anIntArrayArray1483[l][j1] & 0xf8f8ff) == 0) && (j1 != 0)) {
						Rasterizer.anIntArrayArray1483[l][j1] = 1;
					}
				}

			}
		}

		for(int i1 = 0; i1 < 50; i1++) {
			Rasterizer.method370(i1);
		}

	}

	private static int method373(final int i, final double d)
	{
		double d1 = (i >> 16) / 256D;
		double d2 = ((i >> 8) & 0xff) / 256D;
		double d3 = (i & 0xff) / 256D;
		d1 = Math.pow(d1, d);
		d2 = Math.pow(d2, d);
		d3 = Math.pow(d3, d);
		final int j = (int)(d1 * 256D);
		final int k = (int)(d2 * 256D);
		final int l = (int)(d3 * 256D);
		return (j << 16) + (k << 8) + l;
	}

	public static void method374(int i, int j, int k, int l, int i1, int j1, int k1, int l1,
			int i2)
	{
		int j2 = 0;
		int k2 = 0;
		if(j != i)
		{
			j2 = ((i1 - l) << 16) / (j - i);
			k2 = ((l1 - k1) << 15) / (j - i);
		}
		int l2 = 0;
		int i3 = 0;
		if(k != j)
		{
			l2 = ((j1 - i1) << 16) / (k - j);
			i3 = ((i2 - l1) << 15) / (k - j);
		}
		int j3 = 0;
		int k3 = 0;
		if(k != i)
		{
			j3 = ((l - j1) << 16) / (i - k);
			k3 = ((k1 - i2) << 15) / (i - k);
		}
		if((i <= j) && (i <= k))
		{
			if(i >= DrawingArea.bottomY) {
				return;
			}
			if(j > DrawingArea.bottomY) {
				j = DrawingArea.bottomY;
			}
			if(k > DrawingArea.bottomY) {
				k = DrawingArea.bottomY;
			}
			if(j < k)
			{
				j1 = l <<= 16;
				i2 = k1 <<= 15;
				if(i < 0)
				{
					j1 -= j3 * i;
					l -= j2 * i;
					i2 -= k3 * i;
					k1 -= k2 * i;
					i = 0;
				}
				i1 <<= 16;
				l1 <<= 15;
				if(j < 0)
				{
					i1 -= l2 * j;
					l1 -= i3 * j;
					j = 0;
				}
				if(((i != j) && (j3 < j2)) || ((i == j) && (j3 > l2)))
				{
					k -= j;
					j -= i;
					for(i = Rasterizer.anIntArray1472[i]; --j >= 0; i += DrawingArea.width)
					{
						Rasterizer.method375(DrawingArea.pixels, i, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
						j1 += j3;
						l += j2;
						i2 += k3;
						k1 += k2;
					}

					while(--k >= 0)
					{
						Rasterizer.method375(DrawingArea.pixels, i, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
						j1 += j3;
						i1 += l2;
						i2 += k3;
						l1 += i3;
						i += DrawingArea.width;
					}
					return;
				}
				k -= j;
				j -= i;
				for(i = Rasterizer.anIntArray1472[i]; --j >= 0; i += DrawingArea.width)
				{
					Rasterizer.method375(DrawingArea.pixels, i, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
					j1 += j3;
					l += j2;
					i2 += k3;
					k1 += k2;
				}

				while(--k >= 0)
				{
					Rasterizer.method375(DrawingArea.pixels, i, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
					j1 += j3;
					i1 += l2;
					i2 += k3;
					l1 += i3;
					i += DrawingArea.width;
				}
				return;
			}
			i1 = l <<= 16;
			l1 = k1 <<= 15;
			if(i < 0)
			{
				i1 -= j3 * i;
				l -= j2 * i;
				l1 -= k3 * i;
				k1 -= k2 * i;
				i = 0;
			}
			j1 <<= 16;
			i2 <<= 15;
			if(k < 0)
			{
				j1 -= l2 * k;
				i2 -= i3 * k;
				k = 0;
			}
			if(((i != k) && (j3 < j2)) || ((i == k) && (l2 > j2)))
			{
				j -= k;
				k -= i;
				for(i = Rasterizer.anIntArray1472[i]; --k >= 0; i += DrawingArea.width)
				{
					Rasterizer.method375(DrawingArea.pixels, i, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
					i1 += j3;
					l += j2;
					l1 += k3;
					k1 += k2;
				}

				while(--j >= 0)
				{
					Rasterizer.method375(DrawingArea.pixels, i, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
					j1 += l2;
					l += j2;
					i2 += i3;
					k1 += k2;
					i += DrawingArea.width;
				}
				return;
			}
			j -= k;
			k -= i;
			for(i = Rasterizer.anIntArray1472[i]; --k >= 0; i += DrawingArea.width)
			{
				Rasterizer.method375(DrawingArea.pixels, i, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
				i1 += j3;
				l += j2;
				l1 += k3;
				k1 += k2;
			}

			while(--j >= 0)
			{
				Rasterizer.method375(DrawingArea.pixels, i, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
				j1 += l2;
				l += j2;
				i2 += i3;
				k1 += k2;
				i += DrawingArea.width;
			}
			return;
		}
		if(j <= k)
		{
			if(j >= DrawingArea.bottomY) {
				return;
			}
			if(k > DrawingArea.bottomY) {
				k = DrawingArea.bottomY;
			}
			if(i > DrawingArea.bottomY) {
				i = DrawingArea.bottomY;
			}
			if(k < i)
			{
				l = i1 <<= 16;
				k1 = l1 <<= 15;
				if(j < 0)
				{
					l -= j2 * j;
					i1 -= l2 * j;
					k1 -= k2 * j;
					l1 -= i3 * j;
					j = 0;
				}
				j1 <<= 16;
				i2 <<= 15;
				if(k < 0)
				{
					j1 -= j3 * k;
					i2 -= k3 * k;
					k = 0;
				}
				if(((j != k) && (j2 < l2)) || ((j == k) && (j2 > j3)))
				{
					i -= k;
					k -= j;
					for(j = Rasterizer.anIntArray1472[j]; --k >= 0; j += DrawingArea.width)
					{
						Rasterizer.method375(DrawingArea.pixels, j, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
						l += j2;
						i1 += l2;
						k1 += k2;
						l1 += i3;
					}

					while(--i >= 0)
					{
						Rasterizer.method375(DrawingArea.pixels, j, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
						l += j2;
						j1 += j3;
						k1 += k2;
						i2 += k3;
						j += DrawingArea.width;
					}
					return;
				}
				i -= k;
				k -= j;
				for(j = Rasterizer.anIntArray1472[j]; --k >= 0; j += DrawingArea.width)
				{
					Rasterizer.method375(DrawingArea.pixels, j, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
					l += j2;
					i1 += l2;
					k1 += k2;
					l1 += i3;
				}

				while(--i >= 0)
				{
					Rasterizer.method375(DrawingArea.pixels, j, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
					l += j2;
					j1 += j3;
					k1 += k2;
					i2 += k3;
					j += DrawingArea.width;
				}
				return;
			}
			j1 = i1 <<= 16;
			i2 = l1 <<= 15;
			if(j < 0)
			{
				j1 -= j2 * j;
				i1 -= l2 * j;
				i2 -= k2 * j;
				l1 -= i3 * j;
				j = 0;
			}
			l <<= 16;
			k1 <<= 15;
			if(i < 0)
			{
				l -= j3 * i;
				k1 -= k3 * i;
				i = 0;
			}
			if(j2 < l2)
			{
				k -= i;
				i -= j;
				for(j = Rasterizer.anIntArray1472[j]; --i >= 0; j += DrawingArea.width)
				{
					Rasterizer.method375(DrawingArea.pixels, j, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
					j1 += j2;
					i1 += l2;
					i2 += k2;
					l1 += i3;
				}

				while(--k >= 0)
				{
					Rasterizer.method375(DrawingArea.pixels, j, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
					l += j3;
					i1 += l2;
					k1 += k3;
					l1 += i3;
					j += DrawingArea.width;
				}
				return;
			}
			k -= i;
			i -= j;
			for(j = Rasterizer.anIntArray1472[j]; --i >= 0; j += DrawingArea.width)
			{
				Rasterizer.method375(DrawingArea.pixels, j, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
				j1 += j2;
				i1 += l2;
				i2 += k2;
				l1 += i3;
			}

			while(--k >= 0)
			{
				Rasterizer.method375(DrawingArea.pixels, j, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
				l += j3;
				i1 += l2;
				k1 += k3;
				l1 += i3;
				j += DrawingArea.width;
			}
			return;
		}
		if(k >= DrawingArea.bottomY) {
			return;
		}
		if(i > DrawingArea.bottomY) {
			i = DrawingArea.bottomY;
		}
		if(j > DrawingArea.bottomY) {
			j = DrawingArea.bottomY;
		}
		if(i < j)
		{
			i1 = j1 <<= 16;
			l1 = i2 <<= 15;
			if(k < 0)
			{
				i1 -= l2 * k;
				j1 -= j3 * k;
				l1 -= i3 * k;
				i2 -= k3 * k;
				k = 0;
			}
			l <<= 16;
			k1 <<= 15;
			if(i < 0)
			{
				l -= j2 * i;
				k1 -= k2 * i;
				i = 0;
			}
			if(l2 < j3)
			{
				j -= i;
				i -= k;
				for(k = Rasterizer.anIntArray1472[k]; --i >= 0; k += DrawingArea.width)
				{
					Rasterizer.method375(DrawingArea.pixels, k, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
					i1 += l2;
					j1 += j3;
					l1 += i3;
					i2 += k3;
				}

				while(--j >= 0)
				{
					Rasterizer.method375(DrawingArea.pixels, k, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
					i1 += l2;
					l += j2;
					l1 += i3;
					k1 += k2;
					k += DrawingArea.width;
				}
				return;
			}
			j -= i;
			i -= k;
			for(k = Rasterizer.anIntArray1472[k]; --i >= 0; k += DrawingArea.width)
			{
				Rasterizer.method375(DrawingArea.pixels, k, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
				i1 += l2;
				j1 += j3;
				l1 += i3;
				i2 += k3;
			}

			while(--j >= 0)
			{
				Rasterizer.method375(DrawingArea.pixels, k, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
				i1 += l2;
				l += j2;
				l1 += i3;
				k1 += k2;
				k += DrawingArea.width;
			}
			return;
		}
		l = j1 <<= 16;
		k1 = i2 <<= 15;
		if(k < 0)
		{
			l -= l2 * k;
			j1 -= j3 * k;
			k1 -= i3 * k;
			i2 -= k3 * k;
			k = 0;
		}
		i1 <<= 16;
		l1 <<= 15;
		if(j < 0)
		{
			i1 -= j2 * j;
			l1 -= k2 * j;
			j = 0;
		}
		if(l2 < j3)
		{
			i -= j;
			j -= k;
			for(k = Rasterizer.anIntArray1472[k]; --j >= 0; k += DrawingArea.width)
			{
				Rasterizer.method375(DrawingArea.pixels, k, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
				l += l2;
				j1 += j3;
				k1 += i3;
				i2 += k3;
			}

			while(--i >= 0)
			{
				Rasterizer.method375(DrawingArea.pixels, k, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
				i1 += j2;
				j1 += j3;
				l1 += k2;
				i2 += k3;
				k += DrawingArea.width;
			}
			return;
		}
		i -= j;
		j -= k;
		for(k = Rasterizer.anIntArray1472[k]; --j >= 0; k += DrawingArea.width)
		{
			Rasterizer.method375(DrawingArea.pixels, k, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
			l += l2;
			j1 += j3;
			k1 += i3;
			i2 += k3;
		}

		while(--i >= 0)
		{
			Rasterizer.method375(DrawingArea.pixels, k, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
			i1 += j2;
			j1 += j3;
			l1 += k2;
			i2 += k3;
			k += DrawingArea.width;
		}
	}

	private static void method375(final int ai[], int i, int l, int i1, int j1, final int k1)
	{
		int j;//was parameter
		int k;//was parameter
		if(Rasterizer.aBoolean1464)
		{
			int l1;
			if(Rasterizer.aBoolean1462)
			{
				if((i1 - l) > 3) {
					l1 = (k1 - j1) / (i1 - l);
				} else {
					l1 = 0;
				}
				if(i1 > DrawingArea.centerX) {
					i1 = DrawingArea.centerX;
				}
				if(l < 0)
				{
					j1 -= l * l1;
					l = 0;
				}
				if(l >= i1) {
					return;
				}
				i += l;
				k = (i1 - l) >> 2;
				l1 <<= 2;
			} else
			{
				if(l >= i1) {
					return;
				}
				i += l;
				k = (i1 - l) >> 2;
				if(k > 0) {
					l1 = ((k1 - j1) * Rasterizer.anIntArray1468[k]) >> 15;
				} else {
					l1 = 0;
				}
			}
			if(Rasterizer.anInt1465 == 0)
			{
				while(--k >= 0)
				{
					j = Rasterizer.hslToRgbLookupTable[j1 >> 8];
					j1 += l1;
					ai[i++] = j;
					ai[i++] = j;
					ai[i++] = j;
					ai[i++] = j;
				}
				k = (i1 - l) & 3;
				if(k > 0)
				{
					j = Rasterizer.hslToRgbLookupTable[j1 >> 8];
					do {
						ai[i++] = j;
					} while(--k > 0);
					return;
				}
			} else
			{
				final int j2 = Rasterizer.anInt1465;
				final int l2 = 256 - Rasterizer.anInt1465;
				while(--k >= 0)
				{
					j = Rasterizer.hslToRgbLookupTable[j1 >> 8];
					j1 += l1;
					j = ((((j & 0xff00ff) * l2) >> 8) & 0xff00ff) + ((((j & 0xff00) * l2) >> 8) & 0xff00);
					ai[i++] = j + ((((ai[i] & 0xff00ff) * j2) >> 8) & 0xff00ff) + ((((ai[i] & 0xff00) * j2) >> 8) & 0xff00);
					ai[i++] = j + ((((ai[i] & 0xff00ff) * j2) >> 8) & 0xff00ff) + ((((ai[i] & 0xff00) * j2) >> 8) & 0xff00);
					ai[i++] = j + ((((ai[i] & 0xff00ff) * j2) >> 8) & 0xff00ff) + ((((ai[i] & 0xff00) * j2) >> 8) & 0xff00);
					ai[i++] = j + ((((ai[i] & 0xff00ff) * j2) >> 8) & 0xff00ff) + ((((ai[i] & 0xff00) * j2) >> 8) & 0xff00);
				}
				k = (i1 - l) & 3;
				if(k > 0)
				{
					j = Rasterizer.hslToRgbLookupTable[j1 >> 8];
					j = ((((j & 0xff00ff) * l2) >> 8) & 0xff00ff) + ((((j & 0xff00) * l2) >> 8) & 0xff00);
					do {
						ai[i++] = j + ((((ai[i] & 0xff00ff) * j2) >> 8) & 0xff00ff) + ((((ai[i] & 0xff00) * j2) >> 8) & 0xff00);
					} while(--k > 0);
				}
			}
			return;
		}
		if(l >= i1) {
			return;
		}
		final int i2 = (k1 - j1) / (i1 - l);
		if(Rasterizer.aBoolean1462)
		{
			if(i1 > DrawingArea.centerX) {
				i1 = DrawingArea.centerX;
			}
			if(l < 0)
			{
				j1 -= l * i2;
				l = 0;
			}
			if(l >= i1) {
				return;
			}
		}
		i += l;
		k = i1 - l;
		if(Rasterizer.anInt1465 == 0)
		{
			do
			{
				ai[i++] = Rasterizer.hslToRgbLookupTable[j1 >> 8];
				j1 += i2;
			} while(--k > 0);
			return;
		}
		final int k2 = Rasterizer.anInt1465;
		final int i3 = 256 - Rasterizer.anInt1465;
		do
		{
			j = Rasterizer.hslToRgbLookupTable[j1 >> 8];
			j1 += i2;
			j = ((((j & 0xff00ff) * i3) >> 8) & 0xff00ff) + ((((j & 0xff00) * i3) >> 8) & 0xff00);
			ai[i++] = j + ((((ai[i] & 0xff00ff) * k2) >> 8) & 0xff00ff) + ((((ai[i] & 0xff00) * k2) >> 8) & 0xff00);
		} while(--k > 0);
	}

	public static void method376(int i, int j, int k, int l, int i1, int j1, final int k1)
	{
		int l1 = 0;
		if(j != i) {
			l1 = ((i1 - l) << 16) / (j - i);
		}
		int i2 = 0;
		if(k != j) {
			i2 = ((j1 - i1) << 16) / (k - j);
		}
		int j2 = 0;
		if(k != i) {
			j2 = ((l - j1) << 16) / (i - k);
		}
		if((i <= j) && (i <= k))
		{
			if(i >= DrawingArea.bottomY) {
				return;
			}
			if(j > DrawingArea.bottomY) {
				j = DrawingArea.bottomY;
			}
			if(k > DrawingArea.bottomY) {
				k = DrawingArea.bottomY;
			}
			if(j < k)
			{
				j1 = l <<= 16;
				if(i < 0)
				{
					j1 -= j2 * i;
					l -= l1 * i;
					i = 0;
				}
				i1 <<= 16;
				if(j < 0)
				{
					i1 -= i2 * j;
					j = 0;
				}
				if(((i != j) && (j2 < l1)) || ((i == j) && (j2 > i2)))
				{
					k -= j;
					j -= i;
					for(i = Rasterizer.anIntArray1472[i]; --j >= 0; i += DrawingArea.width)
					{
						Rasterizer.method377(DrawingArea.pixels, i, k1, j1 >> 16, l >> 16);
						j1 += j2;
						l += l1;
					}

					while(--k >= 0)
					{
						Rasterizer.method377(DrawingArea.pixels, i, k1, j1 >> 16, i1 >> 16);
						j1 += j2;
						i1 += i2;
						i += DrawingArea.width;
					}
					return;
				}
				k -= j;
				j -= i;
				for(i = Rasterizer.anIntArray1472[i]; --j >= 0; i += DrawingArea.width)
				{
					Rasterizer.method377(DrawingArea.pixels, i, k1, l >> 16, j1 >> 16);
					j1 += j2;
					l += l1;
				}

				while(--k >= 0)
				{
					Rasterizer.method377(DrawingArea.pixels, i, k1, i1 >> 16, j1 >> 16);
					j1 += j2;
					i1 += i2;
					i += DrawingArea.width;
				}
				return;
			}
			i1 = l <<= 16;
			if(i < 0)
			{
				i1 -= j2 * i;
				l -= l1 * i;
				i = 0;
			}
			j1 <<= 16;
			if(k < 0)
			{
				j1 -= i2 * k;
				k = 0;
			}
			if(((i != k) && (j2 < l1)) || ((i == k) && (i2 > l1)))
			{
				j -= k;
				k -= i;
				for(i = Rasterizer.anIntArray1472[i]; --k >= 0; i += DrawingArea.width)
				{
					Rasterizer.method377(DrawingArea.pixels, i, k1, i1 >> 16, l >> 16);
					i1 += j2;
					l += l1;
				}

				while(--j >= 0)
				{
					Rasterizer.method377(DrawingArea.pixels, i, k1, j1 >> 16, l >> 16);
					j1 += i2;
					l += l1;
					i += DrawingArea.width;
				}
				return;
			}
			j -= k;
			k -= i;
			for(i = Rasterizer.anIntArray1472[i]; --k >= 0; i += DrawingArea.width)
			{
				Rasterizer.method377(DrawingArea.pixels, i, k1, l >> 16, i1 >> 16);
				i1 += j2;
				l += l1;
			}

			while(--j >= 0)
			{
				Rasterizer.method377(DrawingArea.pixels, i, k1, l >> 16, j1 >> 16);
				j1 += i2;
				l += l1;
				i += DrawingArea.width;
			}
			return;
		}
		if(j <= k)
		{
			if(j >= DrawingArea.bottomY) {
				return;
			}
			if(k > DrawingArea.bottomY) {
				k = DrawingArea.bottomY;
			}
			if(i > DrawingArea.bottomY) {
				i = DrawingArea.bottomY;
			}
			if(k < i)
			{
				l = i1 <<= 16;
				if(j < 0)
				{
					l -= l1 * j;
					i1 -= i2 * j;
					j = 0;
				}
				j1 <<= 16;
				if(k < 0)
				{
					j1 -= j2 * k;
					k = 0;
				}
				if(((j != k) && (l1 < i2)) || ((j == k) && (l1 > j2)))
				{
					i -= k;
					k -= j;
					for(j = Rasterizer.anIntArray1472[j]; --k >= 0; j += DrawingArea.width)
					{
						Rasterizer.method377(DrawingArea.pixels, j, k1, l >> 16, i1 >> 16);
						l += l1;
						i1 += i2;
					}

					while(--i >= 0)
					{
						Rasterizer.method377(DrawingArea.pixels, j, k1, l >> 16, j1 >> 16);
						l += l1;
						j1 += j2;
						j += DrawingArea.width;
					}
					return;
				}
				i -= k;
				k -= j;
				for(j = Rasterizer.anIntArray1472[j]; --k >= 0; j += DrawingArea.width)
				{
					Rasterizer.method377(DrawingArea.pixels, j, k1, i1 >> 16, l >> 16);
					l += l1;
					i1 += i2;
				}

				while(--i >= 0)
				{
					Rasterizer.method377(DrawingArea.pixels, j, k1, j1 >> 16, l >> 16);
					l += l1;
					j1 += j2;
					j += DrawingArea.width;
				}
				return;
			}
			j1 = i1 <<= 16;
			if(j < 0)
			{
				j1 -= l1 * j;
				i1 -= i2 * j;
				j = 0;
			}
			l <<= 16;
			if(i < 0)
			{
				l -= j2 * i;
				i = 0;
			}
			if(l1 < i2)
			{
				k -= i;
				i -= j;
				for(j = Rasterizer.anIntArray1472[j]; --i >= 0; j += DrawingArea.width)
				{
					Rasterizer.method377(DrawingArea.pixels, j, k1, j1 >> 16, i1 >> 16);
					j1 += l1;
					i1 += i2;
				}

				while(--k >= 0)
				{
					Rasterizer.method377(DrawingArea.pixels, j, k1, l >> 16, i1 >> 16);
					l += j2;
					i1 += i2;
					j += DrawingArea.width;
				}
				return;
			}
			k -= i;
			i -= j;
			for(j = Rasterizer.anIntArray1472[j]; --i >= 0; j += DrawingArea.width)
			{
				Rasterizer.method377(DrawingArea.pixels, j, k1, i1 >> 16, j1 >> 16);
				j1 += l1;
				i1 += i2;
			}

			while(--k >= 0)
			{
				Rasterizer.method377(DrawingArea.pixels, j, k1, i1 >> 16, l >> 16);
				l += j2;
				i1 += i2;
				j += DrawingArea.width;
			}
			return;
		}
		if(k >= DrawingArea.bottomY) {
			return;
		}
		if(i > DrawingArea.bottomY) {
			i = DrawingArea.bottomY;
		}
		if(j > DrawingArea.bottomY) {
			j = DrawingArea.bottomY;
		}
		if(i < j)
		{
			i1 = j1 <<= 16;
			if(k < 0)
			{
				i1 -= i2 * k;
				j1 -= j2 * k;
				k = 0;
			}
			l <<= 16;
			if(i < 0)
			{
				l -= l1 * i;
				i = 0;
			}
			if(i2 < j2)
			{
				j -= i;
				i -= k;
				for(k = Rasterizer.anIntArray1472[k]; --i >= 0; k += DrawingArea.width)
				{
					Rasterizer.method377(DrawingArea.pixels, k, k1, i1 >> 16, j1 >> 16);
					i1 += i2;
					j1 += j2;
				}

				while(--j >= 0)
				{
					Rasterizer.method377(DrawingArea.pixels, k, k1, i1 >> 16, l >> 16);
					i1 += i2;
					l += l1;
					k += DrawingArea.width;
				}
				return;
			}
			j -= i;
			i -= k;
			for(k = Rasterizer.anIntArray1472[k]; --i >= 0; k += DrawingArea.width)
			{
				Rasterizer.method377(DrawingArea.pixels, k, k1, j1 >> 16, i1 >> 16);
				i1 += i2;
				j1 += j2;
			}

			while(--j >= 0)
			{
				Rasterizer.method377(DrawingArea.pixels, k, k1, l >> 16, i1 >> 16);
				i1 += i2;
				l += l1;
				k += DrawingArea.width;
			}
			return;
		}
		l = j1 <<= 16;
		if(k < 0)
		{
			l -= i2 * k;
			j1 -= j2 * k;
			k = 0;
		}
		i1 <<= 16;
		if(j < 0)
		{
			i1 -= l1 * j;
			j = 0;
		}
		if(i2 < j2)
		{
			i -= j;
			j -= k;
			for(k = Rasterizer.anIntArray1472[k]; --j >= 0; k += DrawingArea.width)
			{
				Rasterizer.method377(DrawingArea.pixels, k, k1, l >> 16, j1 >> 16);
				l += i2;
				j1 += j2;
			}

			while(--i >= 0)
			{
				Rasterizer.method377(DrawingArea.pixels, k, k1, i1 >> 16, j1 >> 16);
				i1 += l1;
				j1 += j2;
				k += DrawingArea.width;
			}
			return;
		}
		i -= j;
		j -= k;
		for(k = Rasterizer.anIntArray1472[k]; --j >= 0; k += DrawingArea.width)
		{
			Rasterizer.method377(DrawingArea.pixels, k, k1, j1 >> 16, l >> 16);
			l += i2;
			j1 += j2;
		}

		while(--i >= 0)
		{
			Rasterizer.method377(DrawingArea.pixels, k, k1, j1 >> 16, i1 >> 16);
			i1 += l1;
			j1 += j2;
			k += DrawingArea.width;
		}
	}

	private static void method377(final int ai[], int i, int j, int l, int i1)
	{
		int k;//was parameter
		if(Rasterizer.aBoolean1462)
		{
			if(i1 > DrawingArea.centerX) {
				i1 = DrawingArea.centerX;
			}
			if(l < 0) {
				l = 0;
			}
		}
		if(l >= i1) {
			return;
		}
		i += l;
		k = (i1 - l) >> 2;
		if(Rasterizer.anInt1465 == 0)
		{
			while(--k >= 0)
			{
				ai[i++] = j;
				ai[i++] = j;
				ai[i++] = j;
				ai[i++] = j;
			}
			for(k = (i1 - l) & 3; --k >= 0;) {
				ai[i++] = j;
			}

			return;
		}
		final int j1 = Rasterizer.anInt1465;
		final int k1 = 256 - Rasterizer.anInt1465;
		j = ((((j & 0xff00ff) * k1) >> 8) & 0xff00ff) + ((((j & 0xff00) * k1) >> 8) & 0xff00);
		while(--k >= 0)
		{
			ai[i++] = j + ((((ai[i] & 0xff00ff) * j1) >> 8) & 0xff00ff) + ((((ai[i] & 0xff00) * j1) >> 8) & 0xff00);
			ai[i++] = j + ((((ai[i] & 0xff00ff) * j1) >> 8) & 0xff00ff) + ((((ai[i] & 0xff00) * j1) >> 8) & 0xff00);
			ai[i++] = j + ((((ai[i] & 0xff00ff) * j1) >> 8) & 0xff00ff) + ((((ai[i] & 0xff00) * j1) >> 8) & 0xff00);
			ai[i++] = j + ((((ai[i] & 0xff00ff) * j1) >> 8) & 0xff00ff) + ((((ai[i] & 0xff00) * j1) >> 8) & 0xff00);
		}
		for(k = (i1 - l) & 3; --k >= 0;) {
			ai[i++] = j + ((((ai[i] & 0xff00ff) * j1) >> 8) & 0xff00ff) + ((((ai[i] & 0xff00) * j1) >> 8) & 0xff00);
		}

	}

	public static void method378(int i, int j, int k, int l, int i1, int j1, int k1, int l1,
			int i2, final int j2, int k2, int l2, final int i3, int j3, int k3,
			final int l3, int i4, int j4, final int k4)
	{
		final int ai[] = Rasterizer.method371(k4);
		Rasterizer.aBoolean1463 = !Rasterizer.aBooleanArray1475[k4];
		k2 = j2 - k2;
		j3 = i3 - j3;
		i4 = l3 - i4;
		l2 -= j2;
		k3 -= i3;
		j4 -= l3;
		int l4 = ((l2 * i3) - (k3 * j2)) << 14;
		final int i5 = ((k3 * l3) - (j4 * i3)) << 8;
		final int j5 = ((j4 * j2) - (l2 * l3)) << 5;
		int k5 = ((k2 * i3) - (j3 * j2)) << 14;
		final int l5 = ((j3 * l3) - (i4 * i3)) << 8;
		final int i6 = ((i4 * j2) - (k2 * l3)) << 5;
		int j6 = ((j3 * l2) - (k2 * k3)) << 14;
		final int k6 = ((i4 * k3) - (j3 * j4)) << 8;
		final int l6 = ((k2 * j4) - (i4 * l2)) << 5;
		int i7 = 0;
		int j7 = 0;
		if(j != i)
		{
			i7 = ((i1 - l) << 16) / (j - i);
			j7 = ((l1 - k1) << 16) / (j - i);
		}
		int k7 = 0;
		int l7 = 0;
		if(k != j)
		{
			k7 = ((j1 - i1) << 16) / (k - j);
			l7 = ((i2 - l1) << 16) / (k - j);
		}
		int i8 = 0;
		int j8 = 0;
		if(k != i)
		{
			i8 = ((l - j1) << 16) / (i - k);
			j8 = ((k1 - i2) << 16) / (i - k);
		}
		if((i <= j) && (i <= k))
		{
			if(i >= DrawingArea.bottomY) {
				return;
			}
			if(j > DrawingArea.bottomY) {
				j = DrawingArea.bottomY;
			}
			if(k > DrawingArea.bottomY) {
				k = DrawingArea.bottomY;
			}
			if(j < k)
			{
				j1 = l <<= 16;
				i2 = k1 <<= 16;
				if(i < 0)
				{
					j1 -= i8 * i;
					l -= i7 * i;
					i2 -= j8 * i;
					k1 -= j7 * i;
					i = 0;
				}
				i1 <<= 16;
				l1 <<= 16;
				if(j < 0)
				{
					i1 -= k7 * j;
					l1 -= l7 * j;
					j = 0;
				}
				final int k8 = i - Rasterizer.textureInt2;
				l4 += j5 * k8;
				k5 += i6 * k8;
				j6 += l6 * k8;
				if(((i != j) && (i8 < i7)) || ((i == j) && (i8 > k7)))
				{
					k -= j;
					j -= i;
					i = Rasterizer.anIntArray1472[i];
					while(--j >= 0)
					{
						Rasterizer.method379(DrawingArea.pixels, ai, i, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
						j1 += i8;
						l += i7;
						i2 += j8;
						k1 += j7;
						i += DrawingArea.width;
						l4 += j5;
						k5 += i6;
						j6 += l6;
					}
					while(--k >= 0)
					{
						Rasterizer.method379(DrawingArea.pixels, ai, i, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
						j1 += i8;
						i1 += k7;
						i2 += j8;
						l1 += l7;
						i += DrawingArea.width;
						l4 += j5;
						k5 += i6;
						j6 += l6;
					}
					return;
				}
				k -= j;
				j -= i;
				i = Rasterizer.anIntArray1472[i];
				while(--j >= 0)
				{
					Rasterizer.method379(DrawingArea.pixels, ai, i, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
					j1 += i8;
					l += i7;
					i2 += j8;
					k1 += j7;
					i += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while(--k >= 0)
				{
					Rasterizer.method379(DrawingArea.pixels, ai, i, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
					j1 += i8;
					i1 += k7;
					i2 += j8;
					l1 += l7;
					i += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				return;
			}
			i1 = l <<= 16;
			l1 = k1 <<= 16;
			if(i < 0)
			{
				i1 -= i8 * i;
				l -= i7 * i;
				l1 -= j8 * i;
				k1 -= j7 * i;
				i = 0;
			}
			j1 <<= 16;
			i2 <<= 16;
			if(k < 0)
			{
				j1 -= k7 * k;
				i2 -= l7 * k;
				k = 0;
			}
			final int l8 = i - Rasterizer.textureInt2;
			l4 += j5 * l8;
			k5 += i6 * l8;
			j6 += l6 * l8;
			if(((i != k) && (i8 < i7)) || ((i == k) && (k7 > i7)))
			{
				j -= k;
				k -= i;
				i = Rasterizer.anIntArray1472[i];
				while(--k >= 0)
				{
					Rasterizer.method379(DrawingArea.pixels, ai, i, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
					i1 += i8;
					l += i7;
					l1 += j8;
					k1 += j7;
					i += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while(--j >= 0)
				{
					Rasterizer.method379(DrawingArea.pixels, ai, i, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
					j1 += k7;
					l += i7;
					i2 += l7;
					k1 += j7;
					i += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				return;
			}
			j -= k;
			k -= i;
			i = Rasterizer.anIntArray1472[i];
			while(--k >= 0)
			{
				Rasterizer.method379(DrawingArea.pixels, ai, i, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
				i1 += i8;
				l += i7;
				l1 += j8;
				k1 += j7;
				i += DrawingArea.width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			while(--j >= 0)
			{
				Rasterizer.method379(DrawingArea.pixels, ai, i, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
				j1 += k7;
				l += i7;
				i2 += l7;
				k1 += j7;
				i += DrawingArea.width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			return;
		}
		if(j <= k)
		{
			if(j >= DrawingArea.bottomY) {
				return;
			}
			if(k > DrawingArea.bottomY) {
				k = DrawingArea.bottomY;
			}
			if(i > DrawingArea.bottomY) {
				i = DrawingArea.bottomY;
			}
			if(k < i)
			{
				l = i1 <<= 16;
				k1 = l1 <<= 16;
				if(j < 0)
				{
					l -= i7 * j;
					i1 -= k7 * j;
					k1 -= j7 * j;
					l1 -= l7 * j;
					j = 0;
				}
				j1 <<= 16;
				i2 <<= 16;
				if(k < 0)
				{
					j1 -= i8 * k;
					i2 -= j8 * k;
					k = 0;
				}
				final int i9 = j - Rasterizer.textureInt2;
				l4 += j5 * i9;
				k5 += i6 * i9;
				j6 += l6 * i9;
				if(((j != k) && (i7 < k7)) || ((j == k) && (i7 > i8)))
				{
					i -= k;
					k -= j;
					j = Rasterizer.anIntArray1472[j];
					while(--k >= 0)
					{
						Rasterizer.method379(DrawingArea.pixels, ai, j, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
						l += i7;
						i1 += k7;
						k1 += j7;
						l1 += l7;
						j += DrawingArea.width;
						l4 += j5;
						k5 += i6;
						j6 += l6;
					}
					while(--i >= 0)
					{
						Rasterizer.method379(DrawingArea.pixels, ai, j, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
						l += i7;
						j1 += i8;
						k1 += j7;
						i2 += j8;
						j += DrawingArea.width;
						l4 += j5;
						k5 += i6;
						j6 += l6;
					}
					return;
				}
				i -= k;
				k -= j;
				j = Rasterizer.anIntArray1472[j];
				while(--k >= 0)
				{
					Rasterizer.method379(DrawingArea.pixels, ai, j, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
					l += i7;
					i1 += k7;
					k1 += j7;
					l1 += l7;
					j += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while(--i >= 0)
				{
					Rasterizer.method379(DrawingArea.pixels, ai, j, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
					l += i7;
					j1 += i8;
					k1 += j7;
					i2 += j8;
					j += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				return;
			}
			j1 = i1 <<= 16;
			i2 = l1 <<= 16;
			if(j < 0)
			{
				j1 -= i7 * j;
				i1 -= k7 * j;
				i2 -= j7 * j;
				l1 -= l7 * j;
				j = 0;
			}
			l <<= 16;
			k1 <<= 16;
			if(i < 0)
			{
				l -= i8 * i;
				k1 -= j8 * i;
				i = 0;
			}
			final int j9 = j - Rasterizer.textureInt2;
			l4 += j5 * j9;
			k5 += i6 * j9;
			j6 += l6 * j9;
			if(i7 < k7)
			{
				k -= i;
				i -= j;
				j = Rasterizer.anIntArray1472[j];
				while(--i >= 0)
				{
					Rasterizer.method379(DrawingArea.pixels, ai, j, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
					j1 += i7;
					i1 += k7;
					i2 += j7;
					l1 += l7;
					j += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while(--k >= 0)
				{
					Rasterizer.method379(DrawingArea.pixels, ai, j, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
					l += i8;
					i1 += k7;
					k1 += j8;
					l1 += l7;
					j += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				return;
			}
			k -= i;
			i -= j;
			j = Rasterizer.anIntArray1472[j];
			while(--i >= 0)
			{
				Rasterizer.method379(DrawingArea.pixels, ai, j, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
				j1 += i7;
				i1 += k7;
				i2 += j7;
				l1 += l7;
				j += DrawingArea.width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			while(--k >= 0)
			{
				Rasterizer.method379(DrawingArea.pixels, ai, j, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
				l += i8;
				i1 += k7;
				k1 += j8;
				l1 += l7;
				j += DrawingArea.width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			return;
		}
		if(k >= DrawingArea.bottomY) {
			return;
		}
		if(i > DrawingArea.bottomY) {
			i = DrawingArea.bottomY;
		}
		if(j > DrawingArea.bottomY) {
			j = DrawingArea.bottomY;
		}
		if(i < j)
		{
			i1 = j1 <<= 16;
			l1 = i2 <<= 16;
			if(k < 0)
			{
				i1 -= k7 * k;
				j1 -= i8 * k;
				l1 -= l7 * k;
				i2 -= j8 * k;
				k = 0;
			}
			l <<= 16;
			k1 <<= 16;
			if(i < 0)
			{
				l -= i7 * i;
				k1 -= j7 * i;
				i = 0;
			}
			final int k9 = k - Rasterizer.textureInt2;
			l4 += j5 * k9;
			k5 += i6 * k9;
			j6 += l6 * k9;
			if(k7 < i8)
			{
				j -= i;
				i -= k;
				k = Rasterizer.anIntArray1472[k];
				while(--i >= 0)
				{
					Rasterizer.method379(DrawingArea.pixels, ai, k, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
					i1 += k7;
					j1 += i8;
					l1 += l7;
					i2 += j8;
					k += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while(--j >= 0)
				{
					Rasterizer.method379(DrawingArea.pixels, ai, k, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
					i1 += k7;
					l += i7;
					l1 += l7;
					k1 += j7;
					k += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				return;
			}
			j -= i;
			i -= k;
			k = Rasterizer.anIntArray1472[k];
			while(--i >= 0)
			{
				Rasterizer.method379(DrawingArea.pixels, ai, k, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
				i1 += k7;
				j1 += i8;
				l1 += l7;
				i2 += j8;
				k += DrawingArea.width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			while(--j >= 0)
			{
				Rasterizer.method379(DrawingArea.pixels, ai, k, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
				i1 += k7;
				l += i7;
				l1 += l7;
				k1 += j7;
				k += DrawingArea.width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			return;
		}
		l = j1 <<= 16;
		k1 = i2 <<= 16;
		if(k < 0)
		{
			l -= k7 * k;
			j1 -= i8 * k;
			k1 -= l7 * k;
			i2 -= j8 * k;
			k = 0;
		}
		i1 <<= 16;
		l1 <<= 16;
		if(j < 0)
		{
			i1 -= i7 * j;
			l1 -= j7 * j;
			j = 0;
		}
		final int l9 = k - Rasterizer.textureInt2;
		l4 += j5 * l9;
		k5 += i6 * l9;
		j6 += l6 * l9;
		if(k7 < i8)
		{
			i -= j;
			j -= k;
			k = Rasterizer.anIntArray1472[k];
			while(--j >= 0)
			{
				Rasterizer.method379(DrawingArea.pixels, ai, k, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
				l += k7;
				j1 += i8;
				k1 += l7;
				i2 += j8;
				k += DrawingArea.width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			while(--i >= 0)
			{
				Rasterizer.method379(DrawingArea.pixels, ai, k, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
				i1 += i7;
				j1 += i8;
				l1 += j7;
				i2 += j8;
				k += DrawingArea.width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			return;
		}
		i -= j;
		j -= k;
		k = Rasterizer.anIntArray1472[k];
		while(--j >= 0)
		{
			Rasterizer.method379(DrawingArea.pixels, ai, k, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
			l += k7;
			j1 += i8;
			k1 += l7;
			i2 += j8;
			k += DrawingArea.width;
			l4 += j5;
			k5 += i6;
			j6 += l6;
		}
		while(--i >= 0)
		{
			Rasterizer.method379(DrawingArea.pixels, ai, k, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
			i1 += i7;
			j1 += i8;
			l1 += j7;
			i2 += j8;
			k += DrawingArea.width;
			l4 += j5;
			k5 += i6;
			j6 += l6;
		}
	}

	private static void method379(final int ai[], final int ai1[], int k, int l, int i1, int j1,
			final int k1, int l1, int i2, int j2, final int k2, final int l2, final int i3)
	{
		int i = 0;//was parameter
		int j = 0;//was parameter
		if(l >= i1) {
			return;
		}
		int j3;
		int k3;
		if(Rasterizer.aBoolean1462)
		{
			j3 = (k1 - j1) / (i1 - l);
			if(i1 > DrawingArea.centerX) {
				i1 = DrawingArea.centerX;
			}
			if(l < 0)
			{
				j1 -= l * j3;
				l = 0;
			}
			if(l >= i1) {
				return;
			}
			k3 = (i1 - l) >> 3;
			j3 <<= 12;
			j1 <<= 9;
		} else
		{
			if((i1 - l) > 7)
			{
				k3 = (i1 - l) >> 3;
			j3 = ((k1 - j1) * Rasterizer.anIntArray1468[k3]) >> 6;
			} else
			{
				k3 = 0;
				j3 = 0;
			}
			j1 <<= 9;
		}
		k += l;
		if(Rasterizer.lowMem)
		{
			int i4 = 0;
			int k4 = 0;
			final int k6 = l - Rasterizer.textureInt1;
			l1 += (k2 >> 3) * k6;
			i2 += (l2 >> 3) * k6;
			j2 += (i3 >> 3) * k6;
			int i5 = j2 >> 12;
			if(i5 != 0)
			{
				i = l1 / i5;
				j = i2 / i5;
				if(i < 0) {
					i = 0;
				} else
					if(i > 4032) {
						i = 4032;
					}
			}
			l1 += k2;
			i2 += l2;
			j2 += i3;
			i5 = j2 >> 12;
			if(i5 != 0)
			{
				i4 = l1 / i5;
				k4 = i2 / i5;
				if(i4 < 7) {
					i4 = 7;
				} else
					if(i4 > 4032) {
						i4 = 4032;
					}
			}
			int i7 = (i4 - i) >> 3;
			int k7 = (k4 - j) >> 3;
			i += (j1 & 0x600000) >> 3;
			int i8 = j1 >> 23;
			if(Rasterizer.aBoolean1463)
			{
				while(k3-- > 0)
				{
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i += i7;
					j += k7;
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i += i7;
					j += k7;
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i += i7;
					j += k7;
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i += i7;
					j += k7;
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i += i7;
					j += k7;
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i += i7;
					j += k7;
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i += i7;
					j += k7;
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i = i4;
					j = k4;
					l1 += k2;
					i2 += l2;
					j2 += i3;
					final int j5 = j2 >> 12;
					if(j5 != 0)
					{
						i4 = l1 / j5;
						k4 = i2 / j5;
						if(i4 < 7) {
							i4 = 7;
						} else
							if(i4 > 4032) {
								i4 = 4032;
							}
					}
					i7 = (i4 - i) >> 3;
					k7 = (k4 - j) >> 3;
					j1 += j3;
					i += (j1 & 0x600000) >> 3;
					i8 = j1 >> 23;
				}
				for(k3 = (i1 - l) & 7; k3-- > 0;)
				{
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i += i7;
					j += k7;
				}

				return;
			}
			while(k3-- > 0)
			{
				int k8;
				if((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
					ai[k] = k8;
				}
				k++;
				i += i7;
				j += k7;
				if((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
					ai[k] = k8;
				}
				k++;
				i += i7;
				j += k7;
				if((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
					ai[k] = k8;
				}
				k++;
				i += i7;
				j += k7;
				if((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
					ai[k] = k8;
				}
				k++;
				i += i7;
				j += k7;
				if((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
					ai[k] = k8;
				}
				k++;
				i += i7;
				j += k7;
				if((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
					ai[k] = k8;
				}
				k++;
				i += i7;
				j += k7;
				if((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
					ai[k] = k8;
				}
				k++;
				i += i7;
				j += k7;
				if((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
					ai[k] = k8;
				}
				k++;
				i = i4;
				j = k4;
				l1 += k2;
				i2 += l2;
				j2 += i3;
				final int k5 = j2 >> 12;
				if(k5 != 0)
				{
					i4 = l1 / k5;
					k4 = i2 / k5;
					if(i4 < 7) {
						i4 = 7;
					} else
						if(i4 > 4032) {
							i4 = 4032;
						}
				}
				i7 = (i4 - i) >> 3;
				k7 = (k4 - j) >> 3;
				j1 += j3;
				i += (j1 & 0x600000) >> 3;
				i8 = j1 >> 23;
			}
			for(k3 = (i1 - l) & 7; k3-- > 0;)
			{
				int l8;
				if((l8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
					ai[k] = l8;
				}
				k++;
				i += i7;
				j += k7;
			}

			return;
		}
		int j4 = 0;
		int l4 = 0;
		final int l6 = l - Rasterizer.textureInt1;
		l1 += (k2 >> 3) * l6;
		i2 += (l2 >> 3) * l6;
		j2 += (i3 >> 3) * l6;
		int l5 = j2 >> 14;
		if(l5 != 0)
		{
			i = l1 / l5;
			j = i2 / l5;
			if(i < 0) {
				i = 0;
			} else
				if(i > 16256) {
					i = 16256;
				}
		}
		l1 += k2;
		i2 += l2;
		j2 += i3;
		l5 = j2 >> 14;
		if(l5 != 0)
		{
			j4 = l1 / l5;
			l4 = i2 / l5;
			if(j4 < 7) {
				j4 = 7;
			} else
				if(j4 > 16256) {
					j4 = 16256;
				}
		}
		int j7 = (j4 - i) >> 3;
		int l7 = (l4 - j) >> 3;
		i += j1 & 0x600000;
		int j8 = j1 >> 23;
		if(Rasterizer.aBoolean1463)
		{
			while(k3-- > 0)
			{
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i += j7;
				j += l7;
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i += j7;
				j += l7;
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i += j7;
				j += l7;
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i += j7;
				j += l7;
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i += j7;
				j += l7;
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i += j7;
				j += l7;
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i += j7;
				j += l7;
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i = j4;
				j = l4;
				l1 += k2;
				i2 += l2;
				j2 += i3;
				final int i6 = j2 >> 14;
				if(i6 != 0)
				{
					j4 = l1 / i6;
					l4 = i2 / i6;
					if(j4 < 7) {
						j4 = 7;
					} else
						if(j4 > 16256) {
							j4 = 16256;
						}
				}
				j7 = (j4 - i) >> 3;
				l7 = (l4 - j) >> 3;
				j1 += j3;
				i += j1 & 0x600000;
				j8 = j1 >> 23;
			}
			for(k3 = (i1 - l) & 7; k3-- > 0;)
			{
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i += j7;
				j += l7;
			}

			return;
		}
		while(k3-- > 0)
		{
			int i9;
			if((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
				ai[k] = i9;
			}
			k++;
			i += j7;
			j += l7;
			if((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
				ai[k] = i9;
			}
			k++;
			i += j7;
			j += l7;
			if((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
				ai[k] = i9;
			}
			k++;
			i += j7;
			j += l7;
			if((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
				ai[k] = i9;
			}
			k++;
			i += j7;
			j += l7;
			if((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
				ai[k] = i9;
			}
			k++;
			i += j7;
			j += l7;
			if((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
				ai[k] = i9;
			}
			k++;
			i += j7;
			j += l7;
			if((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
				ai[k] = i9;
			}
			k++;
			i += j7;
			j += l7;
			if((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
				ai[k] = i9;
			}
			k++;
			i = j4;
			j = l4;
			l1 += k2;
			i2 += l2;
			j2 += i3;
			final int j6 = j2 >> 14;
			if(j6 != 0)
			{
				j4 = l1 / j6;
				l4 = i2 / j6;
				if(j4 < 7) {
					j4 = 7;
				} else
					if(j4 > 16256) {
						j4 = 16256;
					}
			}
			j7 = (j4 - i) >> 3;
			l7 = (l4 - j) >> 3;
			j1 += j3;
			i += j1 & 0x600000;
			j8 = j1 >> 23;
		}
		for(int l3 = (i1 - l) & 7; l3-- > 0;)
		{
			int j9;
			if((j9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
				ai[k] = j9;
			}
			k++;
			i += j7;
			j += l7;
		}

	}

	public static final int anInt1459 = -477;
	public static boolean lowMem = true;
	public static boolean aBoolean1462;
	private static boolean aBoolean1463;
	public static boolean aBoolean1464 = true;
	public static int anInt1465;
	public static int textureInt1;
	public static int textureInt2;
	private static int[] anIntArray1468;
	public static final int[] anIntArray1469;
	public static int anIntArray1470[];
	public static int anIntArray1471[];
	public static int anIntArray1472[];
	private static int anInt1473;
	public static IndexedImage textureSprite[] = new IndexedImage[50];
	private static boolean[] aBooleanArray1475 = new boolean[50];
	private static int[] anIntArray1476 = new int[50];
	private static int anInt1477;
	private static int[][] anIntArrayArray1478;
	private static int[][] anIntArrayArray1479 = new int[50][];
	public static int textureID[] = new int[50];
	public static int anInt1481;
	public static int hslToRgbLookupTable[] = new int[0x10000];
	private static int[][] anIntArrayArray1483 = new int[50][];

	static
	{
		Rasterizer.anIntArray1468 = new int[512];
		anIntArray1469 = new int[2048];
		Rasterizer.anIntArray1470 = new int[2048];
		Rasterizer.anIntArray1471 = new int[2048];
		for(int i = 1; i < 512; i++) {
			Rasterizer.anIntArray1468[i] = 32768 / i;
		}

		for(int j = 1; j < 2048; j++) {
			Rasterizer.anIntArray1469[j] = 0x10000 / j;
		}

		for(int k = 0; k < 2048; k++)
		{
			Rasterizer.anIntArray1470[k] = (int)(65536D * Math.sin(k * 0.0030679614999999999D));
			Rasterizer.anIntArray1471[k] = (int)(65536D * Math.cos(k * 0.0030679614999999999D));
		}

	}
}
