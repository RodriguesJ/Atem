package com.runescape.client.revised.media.threeduniverse;

import com.runescape.client.revised.util.Timer;

public class GarbageCollector implements Runnable {

	@Override
	public void run() {
		final Timer time = new Timer();
		final long beforeCollector = Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory();
		System.gc();
		System.runFinalization();
		final long afterCollector = Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory();
		final int memory = (int) (beforeCollector - afterCollector) / 1024;
		System.out.println("Cleaned out " + memory + " KB of memory in " + time + "...");
	}
}