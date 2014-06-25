package com.runescape.revised.util;


public final class MouseDetection implements Runnable
{

	public void run() {
		while(running) {
			synchronized(syncObject) {
				if(coordsIndex < 500) {
					coordsX[coordsIndex] = clientInstance.mouseEventX;
					coordsY[coordsIndex] = clientInstance.mouseEventY;
					coordsIndex++;
				}
			}
			try {
				Thread.sleep(50L);
			} catch(Exception _ex) { }
		}
	}

	public MouseDetection(GameClient client1)
	{
		syncObject = new Object();
		coordsY = new int[500];
		running = true;
		coordsX = new int[500];
		clientInstance = client1;
	}

	private GameClient clientInstance;
	public final Object syncObject;
	public final int[] coordsY;
	public boolean running;
	public final int[] coordsX;
	public int coordsIndex;
}
