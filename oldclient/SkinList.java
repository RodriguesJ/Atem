package com.runescape.client;

public class SkinList {

	public SkinList(final RSBuffer stream) {
		final int anInt341 = stream.readUnsignedByte();
		this.anIntArray342 = new int[anInt341];
		this.anIntArrayArray343 = new int[anInt341][];
		for(int j = 0; j < anInt341; j++) {
			this.anIntArray342[j] = stream.readUnsignedByte();
		}
		for(int k = 0; k < anInt341; k++) {
			final int l = stream.readUnsignedByte();
			this.anIntArrayArray343[k] = new int[l];
			for(int i1 = 0; i1 < l; i1++) {
				this.anIntArrayArray343[k][i1] = stream.readUnsignedByte();
			}
		}
	}

	public final int[] anIntArray342;
	public final int[][] anIntArrayArray343;
}