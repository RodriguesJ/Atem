package com.runescape.revised.client;

public class Flames {
	
	public volatile boolean currentlyDrawingFlames; //aBoolean831
	public int[] flameColorBuffer1; // anIntArray851
	public int[] flameColorBuffer2; // anIntArray852
	public int[] flameColorBuffer3; // anIntArray853
	public int flameCycle; // anInt1208
	public int[] currentFlameColors; // anIntArray850
	public volatile boolean drawFlames;
	public volatile boolean drawingFlames;
	
	public Flames() {
		drawFlames = false;
		drawingFlames = false;
		currentlyDrawingFlames = false;
	}

	public int rotateFlamesColor(int i, int j, int k) // method83
	{
		int l = 256 - k;
		return ((i & 0xff00ff) * l + (j & 0xff00ff) * k & 0xff00ff00) + ((i & 0xff00) * l + (j & 0xff00) * k & 0xff0000) >> 8;
	}
}