package com.runescape.client.revised.config.definitions;

import com.runescape.client.CacheArchive;

public final class Varp {

	public static void unpackConfig(final CacheArchive streamLoader)
	{
		final RSBuffer stream = new RSBuffer(streamLoader.getDataForName("varp.dat"));
		Varp.anInt702 = 0;
		final int cacheSize = stream.readUnsignedWord();
		if(Varp.cache == null) {
			Varp.cache = new Varp[cacheSize];
		}
		if(Varp.anIntArray703 == null) {
			Varp.anIntArray703 = new int[cacheSize];
		}
		for(int j = 0; j < cacheSize; j++)
		{
			if(Varp.cache[j] == null) {
				Varp.cache[j] = new Varp();
			}
			Varp.cache[j].readValues(stream, j);
		}
		if(stream.currentOffset != stream.buffer.length) {
			System.out.println("varptype load mismatch");
		}
	}

	private void readValues(final RSBuffer stream, final int i)
	{
		do
		{
			final int j = stream.readUnsignedByte();
			if(j == 0) {
				return;
			}
			if(j == 1) {
				stream.readUnsignedByte();
			} else
				if(j == 2) {
					stream.readUnsignedByte();
				} else
					if(j == 3) {
						Varp.anIntArray703[Varp.anInt702++] = i;
					} else
						if(j == 5) {
							this.anInt709 = stream.readUnsignedWord();
						} else
							if(j == 7) {
								stream.readDWord();
							} else
								if(j == 8) {
									this.aBoolean713 = true;
								} else
									if(j == 10) {
										stream.readString();
									} else
										if(j == 11) {
											this.aBoolean713 = true;
										} else
											if(j == 12) {
												stream.readDWord();
											} else {
												System.out.println("Error unrecognised config code: " + j);
											}
		} while(true);
	}

	private Varp()
	{
		this.aBoolean713 = false;
	}

	public static Varp cache[];
	private static int anInt702;
	private static int[] anIntArray703;
	public int anInt709;
	public boolean aBoolean713;

}
