package com.runescape.revised.graphics.image;

import com.runescape.client.revised.util.node.QueueNode;

public class DrawingArea extends QueueNode {

	public static void initDrawingArea(int height, int width, int[] pixels)
	{
		DrawingArea.pixels = pixels;
		DrawingArea.width = width;
		DrawingArea.height = height;
		setDrawingArea(height, 0, width, 0);
	}

	public static void defaultDrawingAreaSize()
	{
			topX = 0;
			topY = 0;
			bottomX = width;
			bottomY = height;
			centerX = bottomX - 1;
			centerY = bottomX / 2;
	}

	public static void setDrawingArea(int bottom, int left, int right, int top)
	{
		if(left < 0)
			left = 0;
		if(top < 0)
			top = 0;
		if(right > width)
			right = width;
		if(bottom > height)
			bottom = height;
		topX = left;
		topY = top;
		bottomX = right;
		bottomY = bottom;
		centerX = bottomX - 1;
		centerY = bottomX / 2;
		anInt1387 = bottomY / 2;
	}

	public static void setAllPixelsToZero()
	{
		int totalPixels = width * height;
		for(int j = 0; j < totalPixels; j++)
			pixels[j] = 0;

	}

	public static void drawPixelsOpacity(int color, int yPos, int pixelWidth, int pixelHeight, int opacityLevel, int xPos)
	{ // method335
		if(xPos < topX)
		{
			pixelWidth -= topX - xPos;
			xPos = topX;
		}
		if(yPos < topY)
		{
			pixelHeight -= topY - yPos;
			yPos = topY;
		}
		if(xPos + pixelWidth > bottomX)
			pixelWidth = bottomX - xPos;
		if(yPos + pixelHeight > bottomY)
			pixelHeight = bottomY - yPos;
		int l1 = 256 - opacityLevel;
		int i2 = (color >> 16 & 0xff) * opacityLevel;
		int j2 = (color >> 8 & 0xff) * opacityLevel;
		int k2 = (color & 0xff) * opacityLevel;
		int k3 = width - pixelWidth;
		int l3 = xPos + yPos * width;
		for(int i4 = 0; i4 < pixelHeight; i4++)
		{
			for(int j4 = -pixelWidth; j4 < 0; j4++)
			{
				int l2 = (pixels[l3] >> 16 & 0xff) * l1;
				int i3 = (pixels[l3] >> 8 & 0xff) * l1;
				int j3 = (pixels[l3] & 0xff) * l1;
				int k4 = ((i2 + l2 >> 8) << 16) + ((j2 + i3 >> 8) << 8) + (k2 + j3 >> 8);
				pixels[l3++] = k4;
			}

			l3 += k3;
		}
	}

	public static void drawPixels(int i, int j, int k, int l, int i1)
	{ // method336
		if(k < topX)
		{
			i1 -= topX - k;
			k = topX;
		}
		if(j < topY)
		{
			i -= topY - j;
			j = topY;
		}
		if(k + i1 > bottomX)
			i1 = bottomX - k;
		if(j + i > bottomY)
			i = bottomY - j;
		int k1 = width - i1;
		int l1 = k + j * width;
		for(int i2 = -i; i2 < 0; i2++)
		{
			for(int j2 = -i1; j2 < 0; j2++)
				pixels[l1++] = l;

			l1 += k1;
		}

	}

	public static void drawUnfilledPixels(int i, int j, int k, int l, int i1)
	{ // fillPixels
		drawHorizontalLine(i1, l, j, i);
		drawHorizontalLine((i1 + k) - 1, l, j, i);
		drawVerticalLine(i1, l, k, i);
		drawVerticalLine(i1, l, k, (i + j) - 1);
	}

	public static void drawUnfilledPixelsWithOpacity(int i, int j, int k, int l, int i1, int j1)
	{ // method338
		drawHorizontalLineWithOpacity(l, i1, i, k, j1);
		drawHorizontalLineWithOpacity(l, i1, (i + j) - 1, k, j1);
		if(j >= 3)
		{
			drawVerticalLineWithOpacity(l, j1, k, i + 1, j - 2);
			drawVerticalLineWithOpacity(l, (j1 + i1) - 1, k, i + 1, j - 2);
		}
	}

	public static void drawHorizontalLine(int i, int j, int k, int l)
	{ // method339
		if(i < topY || i >= bottomY)
			return;
		if(l < topX)
		{
			k -= topX - l;
			l = topX;
		}
		if(l + k > bottomX)
			k = bottomX - l;
		int i1 = l + i * width;
		for(int j1 = 0; j1 < k; j1++)
			pixels[i1 + j1] = j;

	}

	private static void drawHorizontalLineWithOpacity(int i, int j, int k, int l, int i1)
	{
		if(k < topY || k >= bottomY)
			return;
		if(i1 < topX)
		{
			j -= topX - i1;
			i1 = topX;
		}
		if(i1 + j > bottomX)
			j = bottomX - i1;
		int j1 = 256 - l;
		int k1 = (i >> 16 & 0xff) * l;
		int l1 = (i >> 8 & 0xff) * l;
		int i2 = (i & 0xff) * l;
		int i3 = i1 + k * width;
		for(int j3 = 0; j3 < j; j3++)
		{
			int j2 = (pixels[i3] >> 16 & 0xff) * j1;
			int k2 = (pixels[i3] >> 8 & 0xff) * j1;
			int l2 = (pixels[i3] & 0xff) * j1;
			int k3 = ((k1 + j2 >> 8) << 16) + ((l1 + k2 >> 8) << 8) + (i2 + l2 >> 8);
			pixels[i3++] = k3;
		}

	}

	public static void drawVerticalLine(int i, int j, int k, int l)
	{
		if(l < topX || l >= bottomX)
			return;
		if(i < topY)
		{
			k -= topY - i;
			i = topY;
		}
		if(i + k > bottomY)
			k = bottomY - i;
		int j1 = l + i * width;
		for(int k1 = 0; k1 < k; k1++)
			pixels[j1 + k1 * width] = j;

	}

	private static void drawVerticalLineWithOpacity(int i, int j, int k, int l, int i1)
	{
		if(j < topX || j >= bottomX)
			return;
		if(l < topY)
		{
			i1 -= topY - l;
			l = topY;
		}
		if(l + i1 > bottomY)
			i1 = bottomY - l;
		int j1 = 256 - k;
		int k1 = (i >> 16 & 0xff) * k;
		int l1 = (i >> 8 & 0xff) * k;
		int i2 = (i & 0xff) * k;
		int i3 = j + l * width;
		for(int j3 = 0; j3 < i1; j3++)
		{
			int j2 = (pixels[i3] >> 16 & 0xff) * j1;
			int k2 = (pixels[i3] >> 8 & 0xff) * j1;
			int l2 = (pixels[i3] & 0xff) * j1;
			int k3 = ((k1 + j2 >> 8) << 16) + ((l1 + k2 >> 8) << 8) + (i2 + l2 >> 8);
			pixels[i3] = k3;
			i3 += width;
		}

	}

	public DrawingArea() {}

	public static int pixels[];
	public static int width;
	public static int height;
	public static int topY;
	public static int bottomY;
	public static int topX;
	public static int bottomX;
	public static int centerX;
	public static int centerY;
	public static int anInt1387;

}
