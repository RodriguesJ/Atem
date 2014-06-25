package com.runescape.client;


public class Class36 {

	public static void method528(final int i) {
		Class36.aClass36Array635 = new Class36[i + 1];
		Class36.aBooleanArray643 = new boolean[i + 1];
		for(int j = 0; j < (i + 1); j++) {
			Class36.aBooleanArray643[j] = true;
		}
	}

	public static void method529(final byte abyte0[]) {
		final RSBuffer stream = new RSBuffer(abyte0);
		stream.currentOffset = abyte0.length - 8;
		final int i = stream.readUnsignedWord();
		final int j = stream.readUnsignedWord();
		final int k = stream.readUnsignedWord();
		final int l = stream.readUnsignedWord();
		int i1 = 0;
		final RSBuffer stream_1 = new RSBuffer(abyte0);
		stream_1.currentOffset = i1;
		i1 += i + 2;
		final RSBuffer stream_2 = new RSBuffer(abyte0);
		stream_2.currentOffset = i1;
		i1 += j;
		final RSBuffer stream_3 = new RSBuffer(abyte0);
		stream_3.currentOffset = i1;
		i1 += k;
		final RSBuffer stream_4 = new RSBuffer(abyte0);
		stream_4.currentOffset = i1;
		i1 += l;
		final RSBuffer stream_5 = new RSBuffer(abyte0);
		stream_5.currentOffset = i1;
		final SkinList class18 = new SkinList(stream_5);
		final int k1 = stream_1.readUnsignedWord();
		final int ai[] = new int[500];
		final int ai1[] = new int[500];
		final int ai2[] = new int[500];
		final int ai3[] = new int[500];
		for(int l1 = 0; l1 < k1; l1++) {
			final int i2 = stream_1.readUnsignedWord();
			final Class36 class36 = Class36.aClass36Array635[i2] = new Class36();
			class36.anInt636 = stream_4.readUnsignedByte();
			class36.aClass18_637 = class18;
			final int j2 = stream_1.readUnsignedByte();
			int k2 = -1;
			int l2 = 0;
			for(int i3 = 0; i3 < j2; i3++) {
				final int j3 = stream_2.readUnsignedByte();
				if(j3 > 0) {
					if(class18.anIntArray342[i3] != 0) {
						for(int l3 = i3 - 1; l3 > k2; l3--) {
							if(class18.anIntArray342[l3] != 0) {
								continue;
							}
							ai[l2] = l3;
							ai1[l2] = 0;
							ai2[l2] = 0;
							ai3[l2] = 0;
							l2++;
							break;
						}
					}
					ai[l2] = i3;
					char c = '\0';
					if(class18.anIntArray342[i3] == 3) {
						c = '\200';
					}
					if((j3 & 1) != 0) {
						ai1[l2] = stream_3.method421();
					} else {
						ai1[l2] = c;
					}
					if((j3 & 2) != 0) {
						ai2[l2] = stream_3.method421();
					} else {
						ai2[l2] = c;
					}
					if((j3 & 4) != 0) {
						ai3[l2] = stream_3.method421();
					} else {
						ai3[l2] = c;
					}
					k2 = i3;
					l2++;
					if(class18.anIntArray342[i3] == 5) {
						Class36.aBooleanArray643[i2] = false;
					}
				}
			}
			class36.anInt638 = l2;
			class36.anIntArray639 = new int[l2];
			class36.anIntArray640 = new int[l2];
			class36.anIntArray641 = new int[l2];
			class36.anIntArray642 = new int[l2];
			for(int k3 = 0; k3 < l2; k3++) {
				class36.anIntArray639[k3] = ai[k3];
				class36.anIntArray640[k3] = ai1[k3];
				class36.anIntArray641[k3] = ai2[k3];
				class36.anIntArray642[k3] = ai3[k3];
			}
		}
	}

	public static void nullLoader()
	{
		Class36.aClass36Array635 = null;
	}

	public static Class36 method531(final int j)
	{
		if(Class36.aClass36Array635 == null) {
			return null;
		} else {
			return Class36.aClass36Array635[j];
		}
	}

	public static boolean method532(final int i)
	{
		return i == -1;
	}

	private static Class36[] aClass36Array635;
	public int anInt636;
	public SkinList aClass18_637;
	public int anInt638;
	public int anIntArray639[];
	public int anIntArray640[];
	public int anIntArray641[];
	public int anIntArray642[];
	private static boolean[] aBooleanArray643;
}